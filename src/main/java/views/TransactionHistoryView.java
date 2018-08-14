package views;

import domain.Account;
import domain.Transaction;
import services.TransactionHistoryService;

import java.util.List;
import java.util.Scanner;

public class TransactionHistoryView implements ConsoleView {
    TransactionHistoryService transactionHistService;
    CheckAccountsAndBalancesView checkAccountsAndBalancesView;

    public TransactionHistoryView(TransactionHistoryService transactionHistService, CheckAccountsAndBalancesView checkAccountsAndBalancesView) {
        this.transactionHistService = transactionHistService;
        this.checkAccountsAndBalancesView = checkAccountsAndBalancesView;
    }

    @Override
    public void execute() {
        checkAccountsAndBalancesView.printAccounts();
        printQuery();
        String accNumber = getAccNumber();

        List<Transaction> transactionList = transactionHistService.getTransactionsByAccount(accNumber);
        if (!transactionList.isEmpty()) {
            System.out.println("Transaction List:");
            for (Transaction tr : transactionList) {
                System.out.println(tr.getFromAccount().getNumber() + "  " + tr.getToAccount().getNumber() + "  " +
                        tr.getAmount() + tr.getToAccount().getCurrency() + "  " + tr.getDateTime());
            }
        } else System.out.println("No transactions for selected account");
    }

    private void printQuery() {
        System.out.println("Enter account Number:");
    }

    private  String getAccNumber() {
        Scanner sc = new Scanner(System.in);
        String account = sc.nextLine();

        return isValidAccount(account) ? account : "";
    }

    private boolean isValidAccount(String accNumber) {
        List<Account> accs = transactionHistService.getAvailabeAccounts();
        for (Account account:accs){
             if(account.getNumber().equals(accNumber)){
                return true;
             }
        }
        return false;
    }

}
