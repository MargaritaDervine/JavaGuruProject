package java2courseIbank.views;

import java2courseIbank.Util.BankUtil;
import java2courseIbank.businessLogic.services.TransactionHistory.TransactionHistoryRequest;
import java2courseIbank.businessLogic.services.TransactionHistory.TransactionHistoryResponse;
import java2courseIbank.domain.Account;
import java2courseIbank.domain.Transaction;
import java2courseIbank.domain.User;
import java2courseIbank.businessLogic.services.CheckAccountBalances.CheckAccountBalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java2courseIbank.businessLogic.services.TransactionHistory.TransactionHistoryService;

import java.util.List;
import java.util.Scanner;

@Component
public class TransactionHistoryView implements ConsoleView {
    String userName;

    @Autowired
    TransactionHistoryService transactionHistService;
    @Autowired
    CheckAccountsAndBalancesView checkAccountsAndBalancesView;

   /* public TransactionHistoryView(TransactionHistoryService transactionHistService, CheckAccountsAndBalancesView checkAccountsAndBalancesView) {
        this.transactionHistService = transactionHistService;
        this.checkAccountsAndBalancesView = checkAccountsAndBalancesView;
    }*/

    @Override
    public void execute() {
        userName = BankUtil.getUserName();
        checkAccountsAndBalancesView.printAccounts();
        printQuery();
        String accNumber = getAccNumber();
        TransactionHistoryResponse response = transactionHistService.getTransactionsByAccount
                (new TransactionHistoryRequest(accNumber, userName));
        if (!response.isSuccess()) {
            System.out.println(response.getError().getField()+response.getError().getDescription());
        } else {
            List<Transaction> transactionList = transactionHistService.getTransactionsByAccount
                    (new TransactionHistoryRequest(accNumber, userName)).getTranasctionList();
            if (!transactionList.isEmpty()) {
                printTransactionList(transactionList);
            } else System.out.println("No transactions for selected account");
        }

    }

    public void printTransactionList(List<Transaction> transactionList) {
        System.out.println("Transaction List (From account, To account, amount, date):");
        for (Transaction tr : transactionList) {
            System.out.println(tr.getFromAccount().getNumber() + "  " + tr.getToAccount().getNumber() + "  " +
                    tr.getAmount() + tr.getToAccount().getCurrency() + "  " + tr.getDateTime());
        }
    }

    private void printQuery() {
        System.out.println("Enter account Number:");
    }

    private String getAccNumber() {
        Scanner sc = new Scanner(System.in);
        String account = sc.nextLine();
        return account;
    }

}
