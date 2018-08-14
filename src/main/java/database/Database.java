package database;

import domain.Account;
import domain.Transaction;
import domain.User;

import java.util.List;
import java.util.Optional;

public interface Database {

    void changeBalance(double amt, String accountNumber);

    List<Transaction>  getTransactionsByAccount(String accountNumber);
    Optional<User> getUser(String username, String password);
    void addTransaction(Transaction transaction);
    List<Account> getAccountsByUserId(Long id);
    Optional<Account> getAccountByAccNumber(String AccNumber);


}
