package java2courseIbank.businessLogic.services.DoTransaction;

import java2courseIbank.AppError;
import java2courseIbank.businessLogic.services.User.GetUserService;
import java2courseIbank.database.AccountRepository;
import java2courseIbank.database.TransactionRepository;
import java2courseIbank.database.UserRepository;
import java2courseIbank.domain.Account;
import java2courseIbank.domain.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class TransactionService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private TransactionValidator transactionValidator;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    GetUserService getUserService;

    @Transactional
    public DoTransactionResponse doTransaction(DoTransactionRequest request) {
        String accFrom = request.getAccFrom();
        String accTo = request.getAccTo();
        double amount = request.getAmt();


        List<AppError> validationErrors = transactionValidator.validateTransaction(accFrom, accTo,
                amount, accountRepository, userRepository, request.getUsername());
        if (!validationErrors.isEmpty()) {
            return new DoTransactionResponse(validationErrors);
        }

        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setDateTime(LocalDateTime.now());
        Optional<Account> fromAccOp = accountRepository.getAccountByAccNumber(accFrom);
        Optional<Account> toAccOp = accountRepository.getAccountByAccNumber(accTo);
        transaction.setFromAcc(fromAccOp.get());
        transaction.setToAccount(toAccOp.get());

        changeBalance(-amount, accFrom);
        changeBalance(amount, accTo);

        transactionRepository.addTransaction(transaction);
        return new DoTransactionResponse(transaction.getId());
    }

    @Transactional
    public void changeBalance(double amt, String accountNumber) {
        Optional<Account> opAcc = accountRepository.getAccountByAccNumber(accountNumber);
        if (opAcc.isPresent()) {
            double currentBalance = opAcc.get().getBalance();
            double newBalance = currentBalance + amt;
            Account account = opAcc.get();
            accountRepository.changeBalance(newBalance, account);
        }
    }
}

