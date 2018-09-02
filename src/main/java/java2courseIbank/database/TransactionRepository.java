package java2courseIbank.database;

import java2courseIbank.domain.Transaction;

import java.util.List;

public interface TransactionRepository {
    List<Transaction> getTransactionsByAccount(String accountNumber);

    void addTransaction(Transaction transaction);
}
