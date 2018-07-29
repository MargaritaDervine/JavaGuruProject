package views;

import domain.Transaction;
import services.TransactionHistoryService;

import java.util.List;

public class TransactionHistoryView implements ConsoleView {
    TransactionHistoryService transactionHistService;

    public TransactionHistoryView(TransactionHistoryService transactionHistService) {
        this.transactionHistService = transactionHistService;
    }

    @Override
    public void execute() {
        System.out.println("Transaction List:");
        List<Transaction> transactionList = transactionHistService.getAllTransactions();
        for (Transaction tr: transactionList){
            System.out.println(tr.getFromAccount().getNumber() + "  " + tr.getToAccount().getNumber()+"  "+
                    tr.getAmount() + tr.getToAccount().getCurrency() +"  "+ tr.getDateTime());
        }

    }
}
