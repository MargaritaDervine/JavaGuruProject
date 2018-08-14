package domain;

public class User {
    private Long id;
    private String firstName;
    private String lastname;
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

    public Long getId() {
        return id;
    }

    public String getLastname() {
        return lastname;
    }

    public User(Long id, String name, String lastname, String username, String password) {
        this.firstName = name;
        this.lastname = lastname;
        //this.accounts = accounts;
        this.username = username;
        this.password = password;
        this.id = id;
    }
}
