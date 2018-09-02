package java2courseIbank.domain;

import javax.persistence.*;

@Entity
@Table(name="users")
public class User {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="first_name", nullable = false)
    private String firstName;

    @Column(name="last_name", nullable = false)
    private String lastname;

    @Column(name="username", nullable = false)
    private String username;

    @Column(name="password", nullable = false)
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
