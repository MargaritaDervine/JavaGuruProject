package java2courseIbank.views;

import java2courseIbank.Util.BankUtil;
import java2courseIbank.database.UserRepository;
import java2courseIbank.domain.Account;
import java2courseIbank.domain.User;
import java2courseIbank.businessLogic.services.CheckAccountBalances.CheckAccountBalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.ast.OpAnd;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CheckAccountsAndBalancesView implements ConsoleView {
    User currentUser;

    @Autowired
    private CheckAccountBalanceService checkAccountBalanceService;
    @Autowired
    private  UserRepository userRepository;


    /*   public CheckAccountsAndBalancesView(CheckAccountBalanceService checkAccountBalanceService) {
           this.checkAccountBalanceService = checkAccountBalanceService;
       }
   */
    @Override
    public void execute() {
        //currentUser = BankUtil.getUser();
        do {
            String userName = BankUtil.getUserName();
            Optional<User> userOptional = userRepository.getUser(userName);
            if(userOptional.isPresent()){
                currentUser = userOptional.get();
            }
        } while (currentUser == null);

        printClientPage();
        printAccounts();
    }

    public void printAccounts() {
        System.out.println("Available accounts:");
        List<Account> accountList = checkAccountBalanceService.getAccountsByUser(currentUser);
        BankUtil.printAccounts(accountList);
    }

    private void printClientPage() {
        System.out.println("client page: " + currentUser.getFirstName() + " " + currentUser.getLastname());
    }

}
