package java2courseIbank.database;

import java2courseIbank.domain.User;

import java.util.Optional;

public interface UserRepository {
    Optional<User> getUser(String username, String password);
    Optional<User> getUser(String username);
    Optional<User> getUser(Long id);
}
