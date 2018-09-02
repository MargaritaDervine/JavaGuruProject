package java2courseIbank.businessLogic.services.TransactionHistory;

import java2courseIbank.AppError;
import java2courseIbank.database.AccountRepository;
import java2courseIbank.domain.Account;
import java2courseIbank.domain.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
@Component
public class TransactionHistoryValidatorImpl implements TransactionHistoryValidator {
    @Override
    public AppError validateAccount(User user, String accNumber, AccountRepository accountRepository) {
        Optional<Account> accOp = accountRepository.getAccountByAccNumber(accNumber);
        if (!accountExists(accOp)) {
            return new AppError("Account", " does not exist");
        } else {
            Account account = accOp.get();
            List<Account> userAccounts = accountRepository.getAccountsByUserId(user.getId());
            if (!userAccounts.contains(account)) {
                return new AppError("Account", " does not belong to user");
            }
            return null;
        }
    }

    @Override
    public boolean accountExists(Optional<Account> accOp) {
        if (!accOp.isPresent()) {
            return false;
        }
        return true;
    }
}
