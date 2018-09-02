package java2courseIbank.businessLogic.services.DoTransaction;

import java2courseIbank.database.AccountRepository;
import java2courseIbank.domain.Account;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Component
public class TransactionValidatorImpl implements TransactionValidator {

    @Override
    public List<String> validateErrors(String accFrom, String accTo, double amount, AccountRepository accountRepository) {
        // List<String> errors = validateAmount(accFrom, amount, accountRepository);
        List<String> errors = validateAccounts(accFrom, accTo, accountRepository);
        errors.add(validateAmount(accFrom, amount, accountRepository));
        return errors;
    }

    @Override
    public String validateAmount(String accFrom, double amount, AccountRepository accountRepository) {
        Optional<Account> fromAccOp = accountRepository.getAccountByAccNumber(accFrom);
        Account fromAcc = fromAccOp.get();
        String error = null;
        if (fromAcc.getBalance() < amount) {
            error = ("Not enough money");
        }
        return error;
    }

    @Override
    public List<String> validateAccounts(String accFrom, String accTo, AccountRepository accountRepository) {
        Optional<Account> fromAccOp = accountRepository.getAccountByAccNumber(accFrom);
        Optional<Account> toAccOp = accountRepository.getAccountByAccNumber(accTo);
        List<String> errors = new ArrayList<>();
        if (!fromAccOp.isPresent()) {
            errors.add("Account " + accFrom + " does not exist");
        }
        if (!toAccOp.isPresent()) {
            errors.add("Account " + accTo + " does not exist");
        }
        return errors;
    }
}
