package java2courseIbank.database.ORM;

import java2courseIbank.database.ORMRepository;
import java2courseIbank.database.UserRepository;
import java2courseIbank.domain.User;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserRepositoryImpl extends ORMRepository implements UserRepository {
    @Override
    public Optional<User> getUser(String username, String password) {
        User user = (User) session().createCriteria(User.class)
                .add(Restrictions.eq("username", username))
                .add(Restrictions.eq("password", password))
                .uniqueResult();
        return Optional.ofNullable(user);
    }

    @Override
    public Optional<User> getUser(String username) {
        User user = (User) session().createCriteria(User.class)
                .add(Restrictions.eq("username", username))
                .uniqueResult();
        return Optional.ofNullable(user);
    }
    @Override
    public Optional<User> getUser(Long id) {
        User user = (User) session().createCriteria(User.class)
                .add(Restrictions.eq("id", id))
                .uniqueResult();
        return Optional.ofNullable(user);
    }
}
