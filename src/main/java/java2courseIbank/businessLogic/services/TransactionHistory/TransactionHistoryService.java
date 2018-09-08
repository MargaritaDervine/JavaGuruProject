package java2courseIbank.businessLogic.services.TransactionHistory;

import java2courseIbank.AppError;
import java2courseIbank.database.AccountRepository;
import java2courseIbank.database.TransactionRepository;
import java2courseIbank.database.UserRepository;
import java2courseIbank.domain.Account;
import java2courseIbank.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
        List<AppError> validationErrors = transactionHistoryValidator.validate(
                request.getAccountNumber(), accountRepository, userRepository, request.getUsername());
        if (!validationErrors.isEmpty()) {
            return new TransactionHistoryResponse(validationErrors);
        }
        Optional<Account> accOp = accountRepository.getAccountByAccNumber(request.getAccountNumber());
        Account acc = accOp.get();
        return new TransactionHistoryResponse(transactionRepository.getTransactionsByAccount(acc), validationErrors);
    }

    public List<Account> getAvailabeAccounts(User user) {
        return accountRepository.getAccountsByUser(user);
    }


}
