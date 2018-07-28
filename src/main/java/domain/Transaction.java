package domain;

import java.util.Date;

public class Transaction {
    Account fromAcc;

    public Account getFromAcc() {
        return fromAcc;
    }

    public Account getToAccount() {
        return toAccount;
    }

    public Date getDate() {
        return date;
    }

    public double getAmount() {
        return amount;
    }

    Account toAccount;
    Date date;
    double amount;

    public Transaction(Account fromAcc, Account toAccount, double amount) {
        this.fromAcc = fromAcc;
        this.toAccount = toAccount;
        this.date = new Date();
        this.amount = amount;
    }

   // public boolean isValid(Account fromAcc, double amount){
    //    return fromAcc.getBalance() > amount;
  //  }


}
