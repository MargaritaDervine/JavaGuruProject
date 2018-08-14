package services;

import database.Database;
import domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
public class ChangeBalanceService {

    @Autowired
   private Database database;

    public ChangeBalanceService(Database database) {
        this.database = database;
    }

    public void changeBalance(double amt, String accountNumber){
        Optional<Account> opAcc = database.getAccountByAccNumber(accountNumber);
        if(opAcc.isPresent()){
            double currentBalance = opAcc.get().getBalance();
            double newBalance = currentBalance + amt;
            if(validateBalance(newBalance)){
                database.changeBalance(newBalance, accountNumber);
            }
        }
    }

    private boolean validateBalance(double newBalance){
        return newBalance >= 0.0d;
    }
}
