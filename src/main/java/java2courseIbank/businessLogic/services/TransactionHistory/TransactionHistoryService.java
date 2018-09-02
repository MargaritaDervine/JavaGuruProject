package java2courseIbank.businessLogic.services.TransactionHistory;

import java2courseIbank.AppError;
import java2courseIbank.database.AccountRepository;
import java2courseIbank.database.TransactionRepository;
import java2courseIbank.database.UserRepository;
import java2courseIbank.domain.Account;
import java2courseIbank.domain.Transaction;
import java2courseIbank.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class TransactionHistoryService {
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private TransactionHistoryValidator transactionHistoryValidator;
    @Autowired
    private UserRepository userRepository;

    public TransactionHistoryResponse getTransactionsByAccount(TransactionHistoryRequest request) {
        Optional<User> userOpt = userRepository.getUser(request.getUsername());
        if (!userOpt.isPresent()) {
            return new TransactionHistoryResponse(errorUserNotFound());
        }
        User user = userOpt.get();
        AppError validationError = transactionHistoryValidator.validateAccount(user,
                request.getAccountNumber(), accountRepository);
        if (validationError != null) {
            return new TransactionHistoryResponse(validationError);
        }
        return new TransactionHistoryResponse(transactionRepository.getTransactionsByAccount(request.accountNumber));
    }

    public List<Account> getAvailabeAccounts(User user) {
        return accountRepository.getAccountsByUserId(user.getId());
    }

    private AppError errorUserNotFound() {
        AppError error = new AppError("username", "Not found");
        return error;
    }


}
