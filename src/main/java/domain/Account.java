package domain;

public class Account {
    private String number;
    private double balance;
    private String currency;

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
    }

    public String getNumber() {
        return number;
    }

    public String getCurrency() {
        return currency;
    }

    public double getBalance() {
        return balance;
    }


}
