package java2courseIbank.database.ORM;

import java2courseIbank.database.AccountRepository;
import java2courseIbank.database.ORMRepository;
import java2courseIbank.domain.Account;
import java2courseIbank.domain.User;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class AccountRepositoryImpl extends ORMRepository implements AccountRepository {
    @Override
    public void changeBalance(double amt, Account account) {
            account.setBalance(amt);
            session().update(account);
    }

    @Override
    public List<Account> getAccountsByUserId(Long id) {
        return session().createCriteria(Account.class)
                .add(Restrictions.eq("user_id", id))
                .list();

    }

    @Override
    public List<Account> getAccountsByUserId(User user) {
        return session().createCriteria(Account.class)
                .add(Restrictions.eq("user_id", user))
                .list();
    }

   /* String query = "from ShoppingList sl where sl.user = :user";
        return session().createQuery(query)
                .setParameter("user", user)
                .list();
*/
        /*return session().createCriteria(ShoppingList.class)
                .add(Restrictions.eq("user", user))
                .list();*/

    @Override
    public Optional<Account> getAccountByAccNumber(String accNumber) {
        Account account = (Account) session().createCriteria(Account.class)
                .add(Restrictions.eq("account_number", accNumber))
                .uniqueResult();
        return Optional.ofNullable(account);
    }
}
