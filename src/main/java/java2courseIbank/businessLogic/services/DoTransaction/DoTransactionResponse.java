package java2courseIbank.businessLogic.services.DoTransaction;

import java.util.List;

public class DoTransactionResponse {
    private boolean success;
    private Long transactionId;
    private List<String> errors;

    public DoTransactionResponse(Long transactionId) {
        this.success = true;
        this.transactionId = transactionId;
    }

    public DoTransactionResponse(List<String> errors) {
        this.success = false;
        this.errors = errors;
    }
}
