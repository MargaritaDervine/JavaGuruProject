package java2courseIbank.database.ORM;

import java2courseIbank.database.ORMRepository;
import java2courseIbank.database.TransactionRepository;
import java2courseIbank.domain.Account;
import java2courseIbank.domain.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class TransactionRepositoryImpl extends ORMRepository implements TransactionRepository {
    @Override
    public List<Transaction> getTransactionsByAccount(Account account) {
        Criterion rest1 = Restrictions.eq("fromAcc", account);
        Criterion rest2 = Restrictions.eq("toAccount", account);
        return session().createCriteria(Transaction.class)
                .add(Restrictions.or(rest1, rest2))
                .list();
    }

    @Override
    public void addTransaction(Transaction transaction) {
        session().save(transaction);
    }
}
