package java2courseIbank.views;

import java2courseIbank.AppError;
import java2courseIbank.Util.BankUtil;
import java2courseIbank.businessLogic.services.DoTransaction.DoTransactionRequest;
import java2courseIbank.businessLogic.services.DoTransaction.DoTransactionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java2courseIbank.businessLogic.services.DoTransaction.TransactionService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
public class TransactionView implements ConsoleView {

    @Autowired
    TransactionService transactionService;
    @Autowired
    CheckAccountsAndBalancesView checkAccountsAndBalancesView;

   /* public TransactionView(TransactionService transactionService, CheckAccountsAndBalancesView checkAccountsAndBalancesView) {
        this.transactionService = transactionService;
        this.checkAccountsAndBalancesView = checkAccountsAndBalancesView;
    }*/

    @Override
    public void execute() {
        String userName;
        userName = BankUtil.getUserName();
        checkAccountsAndBalancesView.printAccounts();
        String accFrom = getAccountFrom();
        String accTo = getAccountTo();
        double amount = 0.0;
        try {
            amount = Double.parseDouble(getAmount());
        } catch (Exception ex){
            System.out.println("Amount is not valid");
        }
        DoTransactionResponse response = transactionService.doTransaction(new DoTransactionRequest(accFrom, accTo, amount, userName));
        if(response.isSuccess()){
            System.out.println("Transaction done");
        } else {
            System.out.println("Transaction failed");
            List<AppError> errors= response.getErrors();
            System.out.println(errors);
        }
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
