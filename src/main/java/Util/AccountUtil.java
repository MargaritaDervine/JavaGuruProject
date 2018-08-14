package Util;

import domain.Account;

import java.util.List;

public class AccountUtil {

    private static String EMPTY = "   ";

    public static void printAccounts(List<Account> accountList) {
        for (Account a : accountList){
            System.out.printf(a.getNumber() + EMPTY +a.getBalance() + a.getCurrency());
            System.out.println();
        }
    }
}
