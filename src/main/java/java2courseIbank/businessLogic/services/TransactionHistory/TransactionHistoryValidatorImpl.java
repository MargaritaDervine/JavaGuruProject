package java2courseIbank.businessLogic.services.TransactionHistory;

import java2courseIbank.AppError;
import java2courseIbank.database.AccountRepository;
import java2courseIbank.database.UserRepository;
import java2courseIbank.domain.Account;
import java2courseIbank.domain.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class TransactionHistoryValidatorImpl implements TransactionHistoryValidator {
    @Override
    public List<AppError> validate(String accNumber, AccountRepository accountRepository, UserRepository userRepository, String username) {
        List<AppError> validationErrors = new ArrayList<>();
        Optional<User> userOpt = userRepository.getUser(username);
        validateUser(userOpt, validationErrors);

        if (validationErrors.isEmpty()) {
            User user = userOpt.get();
            validateAccount(accNumber, accountRepository, validationErrors, user);
        }
        return validationErrors;
    }

    public void validateAccount(String accNumber, AccountRepository accountRepository, List<AppError> validationErrors, User user) {
        Optional<Account> accOp = accountRepository.getAccountByAccNumber(accNumber);
        if (!accountExists(accOp)) {
            validationErrors.add(new AppError("Account", " does not exist"));
        } else {
            Account account = accOp.get();
            List<Account> userAccounts = accountRepository.getAccountsByUser(user);
            if (!userAccounts.contains(account)) {
                validationErrors.add(new AppError("Account", " does not belong to user"));
            }
        }
    }

    private void validateUser(Optional<User> userOpt, List<AppError> validationErrors) {
        if (!userOpt.isPresent()) {
            validationErrors.add(new AppError("username", "Not found"));
        }
    }

    public boolean accountExists(Optional<Account> accOp) {
        if (!accOp.isPresent()) {
            return false;
        }
        return true;
    }
}
