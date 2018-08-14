package views;

import Util.AccountUtil;
import domain.User;
import services.TransactionService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TransactionView implements ConsoleView {
    TransactionService transactionService;
    CheckAccountsAndBalancesView checkAccountsAndBalancesView;

    public TransactionView(TransactionService transactionService, CheckAccountsAndBalancesView checkAccountsAndBalancesView) {
        this.transactionService = transactionService;
        this.checkAccountsAndBalancesView = checkAccountsAndBalancesView;
    }

    @Override
    public void execute() {
        checkAccountsAndBalancesView.printAccounts();
        String accFrom = getAccountFrom();
        String accTo = getAccountTo();
        double amount = 0.0;
        try {
            amount = Double.parseDouble(getAmount());
        } catch (Exception ex){
            System.out.println("Amount is not valid");
        }
        List<String> errors = new ArrayList<>();
        transactionService.doTransaction(accFrom, accTo, amount, errors);
    }

    private static String getAccountFrom() {
        System.out.print("Select an account you want to do transaction from:");
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    private static String getAccountTo() {
        System.out.print("Enter an account you want to do transaction to:");
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    private static String getAmount() {
        System.out.print("Enter an amount:");
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }
}
