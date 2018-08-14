package views;

import domain.Account;
import Util.AccountUtil;
import domain.User;
import services.CheckAccountBalanceService;

import java.util.List;

public class CheckAccountsAndBalancesView implements ConsoleView {
    User currentUser;
    private CheckAccountBalanceService checkAccountBalanceService;


    public CheckAccountsAndBalancesView(User currentUser, CheckAccountBalanceService checkAccountBalanceService) {
        this.currentUser = currentUser;
        this.checkAccountBalanceService = checkAccountBalanceService;
    }

    @Override
    public void execute() {
        printClientPage();
        printAccounts();
    }

    public void printAccounts() {
        System.out.println("Available accounts:");
        List<Account> accountList = checkAccountBalanceService.getAccountsByUser(currentUser);
        AccountUtil.printAccounts(accountList);
    }

    private void printClientPage() {
        System.out.println("client page: " + currentUser.getFirstName() + " " + currentUser.getLastname());
    }
}
