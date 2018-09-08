package java2courseIbank.businessLogic.services.TransactionHistory;

import java2courseIbank.AppError;
import java2courseIbank.database.AccountRepository;
import java2courseIbank.database.UserRepository;
import java2courseIbank.domain.Account;
import java2courseIbank.domain.User;

import java.util.List;
import java.util.Optional;

public interface TransactionHistoryValidator {
    List<AppError> validate(String accNumber, AccountRepository accountRepository, UserRepository userRepository, String username);
}
