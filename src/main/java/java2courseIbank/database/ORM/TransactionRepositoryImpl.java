package java2courseIbank.database.ORM;

import java2courseIbank.database.ORMRepository;
import java2courseIbank.database.TransactionRepository;
import java2courseIbank.domain.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class TransactionRepositoryImpl extends ORMRepository implements TransactionRepository {
    @Override
    public List<Transaction> getTransactionsByAccount(String accountNumber) {
        return session().createCriteria(Transaction.class)
                .add(Restrictions.eq("from_account_number", accountNumber))
                .add(Restrictions.eq("to_account_number", accountNumber))
                .list();
    }

    @Override
    public void addTransaction(Transaction transaction) {
        session().save(transaction);
    }
}
