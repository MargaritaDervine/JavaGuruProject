package java2courseIbank.database;

import java2courseIbank.domain.Account;
import java2courseIbank.domain.Transaction;
import java2courseIbank.domain.User;

import java.util.List;
import java.util.Optional;

public interface Database {

    void changeBalance(double amt, String accountNumber);
    List<Transaction>  getTransactionsByAccount(String accountNumber);
    Optional<User> getUser(String username, String password);
    Optional<User> getUser(String username);
    void addTransaction(Transaction transaction);
    List<Account> getAccountsByUserId(Long id);
    Optional<Account> getAccountByAccNumber(String AccNumber);
    List<Account> getAccountsByUserId(User user) ;


}
