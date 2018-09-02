package java2courseIbank.businessLogic.services.TransactionHistory;

import java2courseIbank.AppError;
import java2courseIbank.database.AccountRepository;
import java2courseIbank.domain.Account;
import java2courseIbank.domain.User;

import java.util.Optional;

public interface TransactionHistoryValidator {
    AppError validateAccount(User user, String accNumber, AccountRepository accountRepository);
    boolean accountExists(Optional<Account> accOp);
}
