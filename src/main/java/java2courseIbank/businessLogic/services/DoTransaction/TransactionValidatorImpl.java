package java2courseIbank.businessLogic.services.DoTransaction;

import java2courseIbank.AppError;
import java2courseIbank.database.AccountRepository;
import java2courseIbank.database.UserRepository;
import java2courseIbank.domain.Account;
import java2courseIbank.domain.User;
import java2courseIbank.web.UserDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class TransactionValidatorImpl implements TransactionValidator {

    @Override
    public List<AppError> validateTransaction(String accFrom, String accTo, double amount, AccountRepository accountRepository, UserRepository userRepository,String username) {
        List<AppError> errors = new ArrayList<>();
        Optional<User> userOpt = userRepository.getUser(username);
        validateUser(userOpt,errors);

        if(errors.isEmpty()){
            User user = userOpt.get();
            validateAccounts(accFrom, accTo, accountRepository, errors, user);
            validateAmount(accFrom, amount, accountRepository, errors);
        }
        return errors;
    }

    @Override
    public void validateAmount(String accFrom, double amount, AccountRepository accountRepository, List<AppError> errors) {
        Optional<Account> fromAccOp = accountRepository.getAccountByAccNumber(accFrom);
        if (fromAccOp.isPresent()) {
            Account fromAcc = fromAccOp.get();
            if (fromAcc.getBalance() < amount) {
                AppError appError = new AppError(accFrom, " has not enough money");
                errors.add(appError);
            }
        }
    }

    @Override
    public void validateAccounts(String accFrom, String accTo, AccountRepository accountRepository, List<AppError> errors, User user) {
        Optional<Account> fromAccOp = accountRepository.getAccountByAccNumber(accFrom);
        Optional<Account> toAccOp = accountRepository.getAccountByAccNumber(accTo);
        if (!fromAccOp.isPresent()) {
            AppError appError = new AppError(accFrom, " does not exist");
            errors.add(appError);
        }
        if (!toAccOp.isPresent()) {
            AppError appError = new AppError(accTo, " does not exist");
            errors.add(appError);
        }

        if(fromAccOp.isPresent()){
            Account account = fromAccOp.get();
            List<Account> userAccounts = accountRepository.getAccountsByUser(user);
            if (!userAccounts.contains(account)) {
                errors.add(new AppError("Account", " does not belong to user"));
            }
        }
    }
    private void validateUser(Optional<User> userOpt, List<AppError> validationErrors) {
        if (!userOpt.isPresent()) {
            validationErrors.add(new AppError("username", "Not found"));
        }
    }


}
