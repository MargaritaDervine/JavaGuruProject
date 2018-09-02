package java2courseIbank;

import java2courseIbank.config.SpringConfig;
import java2courseIbank.database.Database;
import java2courseIbank.database.ORMDatabaseImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import java2courseIbank.views.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class InternetBankApp {
    public static void main(String[] args) {
        // in each view ask login
        //Database database = new JDBCDatabaseImpl();
        Database database = new ORMDatabaseImpl();
        //LogInService logInService = new LogInService(database);
        //LogInView logInView = new LogInView(logInService);

        //printStartPage();

        //User currentUser;
        //currentUser = logInView.executeUser();

       /* while (currentUser == null) {
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
*/
      /*CheckAccountBalanceService checkAccountBalanceService = new CheckAccountBalanceService(database);
        CheckAccountsAndBalancesView accountsAndBalancesView = new CheckAccountsAndBalancesView(currentUser, checkAccountBalanceService);

        TransactionService transactionService = new TransactionService(database, currentUser);
        TransactionView transactionView = new TransactionView(transactionService, accountsAndBalancesView);

        TransactionHistoryService transactionHistoryService = new TransactionHistoryService(database, currentUser);
        TransactionHistoryView transactionHistoryView = new TransactionHistoryView(transactionHistoryService, accountsAndBalancesView);

          accountsAndBalancesView.execute();
*/
        ApplicationContext context
                = new AnnotationConfigApplicationContext(SpringConfig.class);
        context.getBean(CheckAccountsAndBalancesView.class).execute();

       Map<Integer, ConsoleView> menuMap = new HashMap<>();
        menuMap.put(1, context.getBean(CheckAccountsAndBalancesView.class));
        menuMap.put(2, context.getBean(TransactionView.class));
        menuMap.put(3, context.getBean(TransactionHistoryView.class));
        menuMap.put(4, new ExitView());
/*
        Map<Integer, ConsoleView> menuMap = new HashMap<>();
        menuMap.put(1, accountsAndBalancesView);
        menuMap.put(2, transactionView);
        menuMap.put(3, transactionHistoryView);
        menuMap.put(4, new ExitView());
*/
        while (true) {
            ConsoleView consoleView;
            printOptions();
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
