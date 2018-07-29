package services;

import database.Database;
import domain.Account;
import domain.Transaction;
import java.util.List;

public class TransactionHistoryService  {
   private Database database;

    public TransactionHistoryService(Database database) {
        this.database = database;
    }
    public List<Transaction> getAllTransactions(){
        return database.getAllTransactions();
    }
    public List<Transaction> getTransactionsByAccount(String accNumber){
        return database.getTransactionsByAccount(accNumber);
    }

}
