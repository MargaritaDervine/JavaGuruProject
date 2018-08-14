package services;

import database.Database;
import domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
public class LogInService {
    @Autowired
    private Database database;

    public LogInService(Database database) {
        this.database = database;
    }

    public Optional<User> logIn(String username, String password){
        return database.getUser(username,password);
    }

}
