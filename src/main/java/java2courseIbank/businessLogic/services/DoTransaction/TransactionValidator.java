package java2courseIbank.businessLogic.services.DoTransaction;

import java2courseIbank.database.AccountRepository;

import java.util.List;

public interface TransactionValidator {
    List<String> validateErrors(String accFrom, String accTo, double amount, AccountRepository accountRepository);
    String validateAmount(String accFrom, double amount, AccountRepository accountRepository);
    //  boolean validateBalance(double newBalance);
    List<String> validateAccounts(String accFrom, String accTo, AccountRepository accountRepository);
}
