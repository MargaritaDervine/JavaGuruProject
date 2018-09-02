package java2courseIbank.businessLogic.services;

import java2courseIbank.database.UserRepository;
import java2courseIbank.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
public class LogInService {
    @Autowired
    private UserRepository userRepository;

  /* public LogInService(Database database) {
        this.userRepository = database;
    }*/

    public Optional<User> logIn(String username, String password){
        return userRepository.getUser(username,password);
    }
    public Optional<User> logIn(String username){
        return userRepository.getUser(username);
    }

}
