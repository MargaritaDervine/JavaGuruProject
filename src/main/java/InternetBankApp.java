import database.Database;
import database.InMemoryDatabase;
import database.JDBCDatabaseImpl;
import domain.User;
import services.*;
import views.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class InternetBankApp {
    public static void main(String[] args) {
        //Database database = new InMemoryDatabase();
        Database database = new JDBCDatabaseImpl();
        LogInService logInService = new LogInService(database);
        LogInView logInView = new LogInView(logInService);
        printStartPage();
        User currentUser;
        currentUser = logInView.executeUser();

        while (currentUser == null) {
            printExitorContinue();
            try {
                int response = getFromUserMenuItemToExecute();
                if (response == 1) {
                    currentUser = logInView.executeUser();
                } else if (response == 2) {
                    new ExitView().execute();
                }
            } catch (Exception e) {
                printNotValidChoice();
            }
        }

        ChangeBalanceService changeBalanceService = new ChangeBalanceService(database);

        CheckAccountBalanceService checkAccountBalanceService = new CheckAccountBalanceService(database);
        CheckAccountsAndBalancesView accountsAndBalancesView = new CheckAccountsAndBalancesView(currentUser, checkAccountBalanceService);

        TransactionService transactionService = new TransactionService(database, changeBalanceService);
        TransactionView transactionView = new TransactionView(currentUser, transactionService);

        TransactionHistoryService transactionHistoryService = new TransactionHistoryService(database);
        TransactionHistoryView transactionHistoryView = new TransactionHistoryView(transactionHistoryService);

        accountsAndBalancesView.execute();

        Map<Integer, ConsoleView> menuMap = new HashMap<>();
        menuMap.put(1, accountsAndBalancesView);
        menuMap.put(2, transactionView);
        menuMap.put(3, transactionHistoryView);
        menuMap.put(4, new ExitView());

        printOptions();
        while (true) {
            ConsoleView consoleView;
            try {
                int menuItem = getFromUserMenuItemToExecute();
                consoleView = menuMap.get(menuItem);
            } catch (Exception e) {
                printNotValidChoice();
                continue;
            }
            consoleView.execute();
        }
    }

    private static void printNotValidChoice() {
        System.out.println("Choice is not valid, try again");
    }

    private static void printExitorContinue() {
        System.out.println("To continue press 1");
        System.out.println("To exit press 2");
    }

    private static int getFromUserMenuItemToExecute() throws NumberFormatException {
        Scanner sc = new Scanner(System.in);
        return Integer.parseInt(sc.nextLine());
    }

    private static void printOptions() {
        System.out.println();
        System.out.println("To refresh page press 1");
        System.out.println("To do transaction press 2");
        System.out.println("To view transactions press 3");
        System.out.println("To exit press 4");
    }

    private static void printStartPage() {
        System.out.println("Welcome");
    }

}
