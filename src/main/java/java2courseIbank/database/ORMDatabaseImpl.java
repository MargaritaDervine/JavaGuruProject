package java2courseIbank.database;

import java2courseIbank.domain.Account;
import java2courseIbank.domain.Transaction;
import java2courseIbank.domain.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

//Component
//@Transactional
public class ORMDatabaseImpl implements Database {
    @Autowired
    private SessionFactory sessionFactory;

    private Session session() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void changeBalance(double amt, String accountNumber) {
        Optional<Account> opAccount = getAccountByAccNumber(accountNumber);
        if (opAccount.isPresent()) {
            Account account = opAccount.get();
            account.setBalance(amt);
            session().update(account);
        }
    }

    @Override
    public List<Transaction> getTransactionsByAccount(String accountNumber) {
        return session().createCriteria(Transaction.class)
                .add(Restrictions.eq("from_account_number", accountNumber))
                .add(Restrictions.eq("to_account_number", accountNumber))
                .list();
    }

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
    public void addTransaction(Transaction transaction) {
        session().save(transaction);
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
