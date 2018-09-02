package java2courseIbank.domain;

import org.springframework.stereotype.Component;

import javax.persistence.*;

@Entity
@Table(name="accounts")
public class Account {

    @Id
    @Column(name="account_number")
    private String number;

    @Column(name="balance", nullable = false)
    private Double balance;

    @Column(name="currency", nullable = false)
    private String currency;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

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
