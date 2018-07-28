package services;

import database.Database;
import domain.Account;
import domain.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CheckAccountBalanceService {
    private Database database;

    public CheckAccountBalanceService(Database database) {
        this.database = database;
    }

    public List<Account> getAccountsByUser(User user) {
        return  user.getAccounts();
    }
}
