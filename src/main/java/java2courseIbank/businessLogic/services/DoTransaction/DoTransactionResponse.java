package java2courseIbank.businessLogic.services.DoTransaction;

import java2courseIbank.AppError;

import java.util.List;

public class DoTransactionResponse {
    public boolean isSuccess() {
        return success;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public List<AppError> getErrors() {
        return errors;
    }

    private boolean success;
    private Long transactionId;
    private List<AppError> errors;

    public DoTransactionResponse(Long transactionId) {
        this.success = true;
        this.transactionId = transactionId;
    }

    public DoTransactionResponse(List<AppError> errors) {
        this.success = false;
        this.errors = errors;
    }
}
