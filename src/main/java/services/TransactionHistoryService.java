package services;

import database.Database;
import domain.Account;
import domain.Transaction;
import domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TransactionHistoryService  {
    @Autowired
   private Database database;
    @Autowired
    private User user;

    public TransactionHistoryService(Database database, User currentUser) {
        this.database = database;
        this.user = currentUser;
    }

    public List<Transaction> getTransactionsByAccount(String accNumber){
        return database.getTransactionsByAccount(accNumber);
    }

    public List <Account> getAvailabeAccounts(){
        return database.getAccountsByUserId(user.getId());
    }


}
