package database;

import domain.Account;
import domain.Transaction;
import domain.User;

import java.util.List;
import java.util.Optional;

public interface Database {

    void changeBalance(double amt, String accountNumber);

    List<Transaction>  getTransactionsByAccount(String accountNumberA);
    List<Transaction>  getAllTransactions();
    Optional<User> getUser(String username, String password);
    void addTransaction(Transaction transaction);

    Long getAccountId(Account account);
    Optional <Account>  getAccountById(Long accId);
    List<Account> getAccountsByUserId(Long id);
    Optional<Account> getAccountByAccNumber(String AccNumber);
}
