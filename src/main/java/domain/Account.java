package domain;

import java.util.ArrayList;
import java.util.List;

public class Account {
    Long id;

    public Long getId() {
        return id;
    }

    public Account(Long id, String number, double balance, String currency, List<Transaction> transactions) {
        this.id = id;
        this.number = number;
        this.balance = balance;
        this.currency = currency;
        this.transactions = transactions;
    }

    private String number;
    private double balance;
    private String currency;
    private List<Transaction> transactions;

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Account(String number, double balance, String currency) {
        this.number = number;
        this.balance = balance;
        this.currency = currency;
    }

    public Account(String number) {
        this.number = number;
        this.balance = 0.0d;
        this.currency = "EUR";
        this.transactions = new ArrayList<>();
    }

    public String getNumber() {
        return number;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public String getCurrency() {
        return currency;
    }

    public double getBalance() {
        return balance;
    }


}
