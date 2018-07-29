package services;

import database.Database;
import domain.Account;
import domain.Transaction;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class TransactionService {
    private Database database;
    private ChangeBalanceService changeBalanceService;

    public TransactionService(Database database, ChangeBalanceService changeBalanceService) {
        this.database = database;
        this.changeBalanceService = changeBalanceService;
    }

    public boolean doTransaction(String accFrom, String accTo, double amt, List<String> errors) {
        Optional<Account> fromAccOp = database.getAccountByAccNumber(accFrom);
        Optional<Account> toAccOp = database.getAccountByAccNumber(accTo);

        if (!validateAccounts(accFrom, accTo,fromAccOp, toAccOp, errors)) {
            errors.add("Account is not valid");
            return false;
        }
        if (!validateAmount(accFrom, amt)) {
            errors.add("Amount is not valid.");
            return false;
        }
        Transaction transaction = new Transaction();
        transaction.setAmount(amt);
        transaction.setDateTime(LocalDateTime.now());
        transaction.setFromAcc(fromAccOp.get());
        transaction.setToAccount(toAccOp.get());
        database.addTransaction(transaction);

        changeBalanceService.changeBalance(-amt, accFrom);
        changeBalanceService.changeBalance(amt, accTo);

        System.out.println("Transaction done");
        return true;
    }

    private boolean checkPresence(String accName, Optional<Account> accountOptional, List<String> errors) {
        if (!accountOptional.isPresent()) {
            errors.add(accName + " does not exist.");
            return false;
        }
        return true;
    }

    private boolean validateAccounts(String accFrom, String accTo, Optional<Account>  fromAccOp,
                                     Optional<Account> toAccOp, List<String> errors) {
        if (!checkPresence(accFrom, fromAccOp, errors) || !checkPresence(accTo, toAccOp, errors)) {
            return false;
        }
        return true;
    }

    private boolean validateAmount(String accFrom, double amount) {
        Optional<Account> fromAccOp = database.getAccountByAccNumber(accFrom);
        Account fromAcc = fromAccOp.get();
        return fromAcc.getBalance() >= amount;
    }

}
