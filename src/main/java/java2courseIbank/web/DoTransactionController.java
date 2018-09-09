package java2courseIbank.web;

import java2courseIbank.businessLogic.services.DoTransaction.DoTransactionRequest;
import java2courseIbank.businessLogic.services.DoTransaction.DoTransactionResponse;
import java2courseIbank.businessLogic.services.DoTransaction.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class DoTransactionController {
    @Autowired
    TransactionService transactionService;

    @ResponseBody
    @RequestMapping(value = "/user/{username}/doTransaction/{account}", method = RequestMethod.GET)
    public Long doTransaction(@PathVariable("username") String username, @PathVariable("account") String account){
        String accountTo = "";
        double amount = 0.0d;
        DoTransactionRequest request = new DoTransactionRequest(account, accountTo, amount, username);
        DoTransactionResponse response = transactionService.doTransaction(request);

        return response.getTransactionId();
    }
    /*private String accFrom;
    private String accTo;
    private double amt;
    String username;*/

}
