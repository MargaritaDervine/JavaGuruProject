package services;

import database.Database;
import domain.User;

import java.util.Optional;

public class LogInService {
    private Database database;

    public LogInService(Database database) {
        this.database = database;
    }

    public Optional<User> logIn(String username, String password){
        return database.getUser(username,password);
    }

}
