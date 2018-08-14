package services;

import database.Database;
import domain.Account;
import domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class CheckAccountBalanceService {
    @Autowired
    private Database database;

    public CheckAccountBalanceService(Database database) {
        this.database = database;
    }

    public List<Account> getAccountsByUser(User user) {
        return  database.getAccountsByUserId(user.getId());
    }
}
