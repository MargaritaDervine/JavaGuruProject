package database;

import domain.Account;
import domain.Transaction;
import domain.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class InMemoryDatabase implements Database {
    Account acc1 = new Account("123");
    Account acc2 = new Account("456");

    private ArrayList<Transaction> initTransactions(){
        ArrayList<Transaction> trs = new ArrayList<>();
        Transaction tr1 = new Transaction(acc1,acc2,-100);
        Transaction tr2 = new Transaction(acc2,acc1,100);
        trs.add(tr1);
        trs.add(tr2);
        return trs;
    }

    private ArrayList<Account> initAccounts(){
        ArrayList<Account> accs = new ArrayList<>();
        acc1.setBalance(100);
        accs.add(acc1);
        accs.add(acc2);
        return accs;
    }

    User user1 = new User("A", "A",  initAccounts(), "a", "123");
  //  User user2 = new User("B", "B",  accounts, "AB", "zxc");
    ArrayList<User> users= new ArrayList<>(Arrays.asList(user1));
    ArrayList<Transaction> transactions = initTransactions();
    ArrayList<Account> accounts = initAccounts();

    @Override
    public void changeBalance(double newAmt, String accountNumber) {
        Optional<Account> accountOp = getAccountByAccNumber(accountNumber);
        if(accountOp.isPresent()){
            Account acc = accountOp.get();
            acc.setBalance(newAmt);
        }
    }


   /* @Override
    public List<Account> getAccountsByUser(User user) {
        return  user.getAccounts();
    }
*/

   @Override
   public void addTransaction(Transaction transaction){
       transactions.add(transaction);
   }

    @Override
    public Optional<Account> getAccountByAccNumber(String accN) {
        Account acc = null;
        for (Account account:accounts) {
            if(account.getNumber().equals(accN)){
                acc = account;
                break;
            }
        }
        return  Optional.ofNullable(acc);
    }

    @Override
    public List<Transaction> getTransactionsByAccount(String accountNumber) {
        Optional<Account> accountOp = getAccountByAccNumber(accountNumber);
        if(accountOp.isPresent()){
          //  Account acc = accountOp.get();
            //return acc.getTransactions();
            return new ArrayList<>(transactions);
        }
        return null;
    }

    @Override
    public List<Transaction>  getAllTransactions(){
       return new ArrayList<>(transactions);
    }

    @Override
    public Optional<User> getUser(String username, String password) {
        User resultUser = null;
        for (User u: users){
            if(u.getUsername().equals(username) && u.getPassword().equals(password)){
                resultUser = u;
            }
        }
        return Optional.ofNullable(resultUser);
    }
}
