package domain;

import java.sql.Timestamp;
import java.util.Date;
import java.time.LocalDateTime;

public class Transaction {
    Long id;
    Account fromAcc;
    Account toAccount;
    LocalDateTime dateTime;
    double amount;

    public void setId(Long id) {
        this.id = id;
    }

    public void setFromAcc(Account fromAcc) {
        this.fromAcc = fromAcc;
    }

    public void setToAccount(Account toAccount) {
        this.toAccount = toAccount;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
    public void setDateTime(Timestamp timestamp) {
        LocalDateTime dateTime = timestamp.toLocalDateTime();
        this.dateTime = dateTime;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Account getFromAccount() {
        return fromAcc;
    }

    public Account getToAccount() {
        return toAccount;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public double getAmount() {
        return amount;
    }

    public Transaction() {
    }


   // public boolean isValid(Account fromAcc, double amount){
    //    return fromAcc.getBalance() > amount;
  //  }


}
