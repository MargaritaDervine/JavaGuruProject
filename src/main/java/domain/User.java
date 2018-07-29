package domain;

import java.util.ArrayList;
import java.util.List;

public class User {
    Long id;
    private String firstName;
    private String lastname;
    private List<Account> accounts;
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public String getFirstName() {
        return firstName;
    }

    public String getLastname() {
        return lastname;
    }

    public User(Long id, String name, String lastname, List<Account> accounts,String username,String password) {
        this.firstName = name;
        this.lastname = lastname;
        this.accounts = accounts;
        this.username = username;
        this.password = password;
        this.id = id;
    }
    public List<Account> getAccounts() {
        return accounts;
    }
}
