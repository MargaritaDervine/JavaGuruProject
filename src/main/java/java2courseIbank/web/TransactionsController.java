package java2courseIbank.web;

import java2courseIbank.businessLogic.services.TransactionHistory.TransactionHistoryRequest;
import java2courseIbank.businessLogic.services.TransactionHistory.TransactionHistoryResponse;
import java2courseIbank.businessLogic.services.TransactionHistory.TransactionHistoryService;
import java2courseIbank.web.DTOs.TransactionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TransactionsController {
    @Autowired
    TransactionHistoryService transactionHistoryService;

    @ResponseBody
    @RequestMapping(value = "/user/{username}/{account}/transactions", method = RequestMethod.GET)
    public List<TransactionDTO> getTransactions(@PathVariable("username") String username, @PathVariable("account") String account){
        TransactionHistoryRequest request = new TransactionHistoryRequest(account, username);
        TransactionHistoryResponse response = transactionHistoryService.getTransactionsByAccount(request);
        return response.getTranasctionList();
    }

}