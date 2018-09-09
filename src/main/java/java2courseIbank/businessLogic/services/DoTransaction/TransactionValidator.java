package java2courseIbank.businessLogic.services.DoTransaction;

import java2courseIbank.AppError;
import java2courseIbank.database.AccountRepository;
import java2courseIbank.database.UserRepository;
import java2courseIbank.domain.User;

import java.util.List;

public interface TransactionValidator {
    List<AppError> validateTransaction(String accFrom, String accTo, double amount, AccountRepository accountRepository, UserRepository userRepository, String user);

    void validateAmount(String accFrom, double amount, AccountRepository accountRepository, List<AppError> errors);

    //  boolean validateBalance(double newBalance);
    void validateAccounts(String accFrom, String accTo, AccountRepository accountRepository, List<AppError> errors, User user);
}
