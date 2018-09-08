package java2courseIbank.database;

import java2courseIbank.domain.Account;
import java2courseIbank.domain.Transaction;

import java.util.List;

public interface TransactionRepository {
    List<Transaction> getTransactionsByAccount(Account account);

    void addTransaction(Transaction transaction);
}
