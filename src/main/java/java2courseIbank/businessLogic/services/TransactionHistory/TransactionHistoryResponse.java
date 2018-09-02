package java2courseIbank.businessLogic.services.TransactionHistory;

import java2courseIbank.AppError;
import java2courseIbank.domain.Transaction;

import java.util.List;

public class TransactionHistoryResponse {
    List<Transaction> tranasctionList;
    private boolean success;
    private AppError error;

    public TransactionHistoryResponse(List<Transaction> tranasctionList) {
        this.tranasctionList = tranasctionList;
        this.success = true;
        this.error = null;
    }

    public TransactionHistoryResponse(AppError error) {
        this.tranasctionList = null;
        this.success = false;
        this.error = error;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setError(AppError error) {
        this.error = error;
    }

    public boolean isSuccess() {

        return success;
    }

    public AppError getError() {
        return error;
    }

    public void setTranasctionList(List<Transaction> tranasctionList) {
        this.tranasctionList = tranasctionList;

    }

    public List<Transaction> getTranasctionList() {
        return tranasctionList;
    }


}
