package services;

import database.Database;
import domain.Account;
import domain.Transaction;

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

        if (!validateAccounts(accFrom, accTo, errors)) {
            errors.add("Account is not valid");
            return false;
        }
        if (!validateAmount(accFrom, amt)) {
            errors.add("Amount is not valid.");
            return false;
        }
        Transaction transaction = new Transaction(fromAccOp.get(), toAccOp.get(), amt);
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

    private boolean validateAccounts(String accFrom, String accTo, List<String> errors) {
        Optional<Account> fromAccOp = database.getAccountByAccNumber(accFrom);
        Optional<Account> toAccOp = database.getAccountByAccNumber(accTo);
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
