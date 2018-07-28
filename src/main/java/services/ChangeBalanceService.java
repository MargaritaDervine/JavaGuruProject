package services;

import database.Database;
import domain.Account;

import java.util.Optional;

public class ChangeBalanceService {

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
