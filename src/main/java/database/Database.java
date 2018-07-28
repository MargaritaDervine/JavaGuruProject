package database;

import domain.Account;
import domain.Transaction;
import domain.User;

import java.util.List;
import java.util.Optional;

public interface Database {

    void changeBalance(double amt, String accountNumber);
    //List<Account> getAccountsByUser(User user);
    Optional<Account> getAccountByAccNumber(String AccNumber);
    List<Transaction>  getTransactionsByAccount(String accountNumber);
    List<Transaction>  getAllTransactions();
    Optional<User> getUser(String username, String password);
    void addTransaction(Transaction transaction);



}
