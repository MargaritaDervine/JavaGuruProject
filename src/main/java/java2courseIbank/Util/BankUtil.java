package java2courseIbank.Util;

import java2courseIbank.database.Database;
import java2courseIbank.database.JDBC.JDBCDatabaseImpl;
import java2courseIbank.database.ORM.UserRepositoryImpl;
import java2courseIbank.database.UserRepository;
import java2courseIbank.domain.Account;
import java2courseIbank.domain.User;
import java2courseIbank.businessLogic.services.LogInService;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class BankUtil {

    private static String EMPTY = "   ";

    public static void printAccounts(List<Account> accountList) {
        for (Account a : accountList) {
            System.out.printf(a.getNumber() + EMPTY + a.getBalance() + a.getCurrency());
            System.out.println();
        }
    }

    public static String getUserName() {
        System.out.print("USERNAME:");
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }
}
