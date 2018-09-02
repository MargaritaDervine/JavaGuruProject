package java2courseIbank.database;

import java2courseIbank.domain.Account;
import java2courseIbank.domain.User;

import java.util.List;
import java.util.Optional;

public interface AccountRepository {
    void changeBalance(double amt, Account account);

    Optional<Account> getAccountByAccNumber(String AccNumber);

    List<Account> getAccountsByUserId(User user);

    List<Account> getAccountsByUserId(Long id);
}
