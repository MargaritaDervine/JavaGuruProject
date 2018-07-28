package domain;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String firstName;
    private String lastname;

    public String getFirstName() {
        return firstName;
    }

    public String getLastname() {
        return lastname;
    }

    private List<Account> accounts;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    private String username;
    private String password;

    public User(String name, String lastname, List<Account> accounts,String username,String password) {
        this.firstName = name;
        this.lastname = lastname;
        this.accounts = accounts;
        this.username = username;
        this.password = password;
    }
    public List<Account> getAccounts() {
        return accounts;
    }
}
