package java2courseIbank.Util;

import java2courseIbank.domain.Account;

import java.util.List;
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
