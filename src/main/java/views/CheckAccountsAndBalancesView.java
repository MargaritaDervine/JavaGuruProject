package views;

import domain.Account;
import domain.User;
import services.CheckAccountBalanceService;

import java.util.List;

public class CheckAccountsAndBalancesView implements ConsoleView{
    User currentUser;
    private CheckAccountBalanceService checkAccountBalanceService;
    private String EMPTY = "   ";

    public CheckAccountsAndBalancesView(User currentUser, CheckAccountBalanceService checkAccountBalanceService) {
        this.currentUser = currentUser;
        this.checkAccountBalanceService = checkAccountBalanceService;
    }

    @Override
    public void execute() {
        printClientPage();
        System.out.println("Available accounts:");
        List<Account> accountList = checkAccountBalanceService.getAccountsByUser(currentUser);
        for (Account a : accountList){
            System.out.printf(a.getNumber() + EMPTY +a.getBalance() + a.getCurrency());
            System.out.println();
        }
    }
    private void printClientPage() {
        System.out.println("client page: "+ currentUser.getFirstName() + " "+currentUser.getLastname());
    }
}
