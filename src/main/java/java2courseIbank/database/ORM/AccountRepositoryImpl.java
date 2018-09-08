package java2courseIbank.database.ORM;

import java2courseIbank.database.AccountRepository;
import java2courseIbank.database.ORMRepository;
import java2courseIbank.domain.Account;
import java2courseIbank.domain.User;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
@Transactional
public class AccountRepositoryImpl extends ORMRepository implements AccountRepository {

    @Override
    public void changeBalance(double amt, Account account) {
            account.setBalance(amt);
            session().update(account);
    }

   @Override
    public List<Account> getAccountsByUser(User user) {
       return session().createCriteria(Account.class)
               .add(Restrictions.eq("user", user))
               .list();
    }
    /*
            String query = "from Accounts a where a.user_id = :id";
            return session().createQuery(query)
                    .setParameter("user", user)
                    .list();
    */
    @Override
    public Optional<Account> getAccountByAccNumber(String accNumber) {
        Account account = (Account) session().createCriteria(Account.class)
                .add(Restrictions.eq("number", accNumber))
                .uniqueResult();
        return Optional.ofNullable(account);
    }
}
