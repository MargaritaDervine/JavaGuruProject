package java2courseIbank.businessLogic.services.CheckAccountBalances;

import java2courseIbank.database.AccountRepository;
import java2courseIbank.domain.Account;
import java2courseIbank.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CheckAccountBalanceService {
    @Autowired
    private AccountRepository repository;

    public List<Account> getAccountsByUser(User user) {
        return  repository.getAccountsByUser(user);
       // return  repository.getAccountsByUser(user);
    }
}
