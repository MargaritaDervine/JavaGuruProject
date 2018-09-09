package java2courseIbank.web.DTOs;

import java2courseIbank.domain.Account;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class TransactionDTO {

    public TransactionDTO(Long id, Account fromAcc, Account toAccount, LocalDateTime dateTime, double amount) {
        this.id = id;
        this.fromAcc = fromAcc;
        this.toAccount = toAccount;
        this.dateTime = dateTime;
        this.amount = amount;
    }

    Long id;
    Account fromAcc;
    Account toAccount;
    LocalDateTime dateTime;
    double amount;

    public TransactionDTO() {
    }

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


    public Long getId() {
        return id;
    }

    public Account getFromAcc() {
        return fromAcc;
    }


}
