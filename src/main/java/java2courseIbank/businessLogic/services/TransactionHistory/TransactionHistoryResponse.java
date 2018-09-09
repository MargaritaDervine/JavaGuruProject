package java2courseIbank.businessLogic.services.TransactionHistory;

import java2courseIbank.AppError;
import java2courseIbank.domain.Transaction;
import java2courseIbank.web.DTOs.TransactionDTO;

import java.util.List;

public class TransactionHistoryResponse {
    List<TransactionDTO> tranasctionList;
    private boolean success;
    private List<AppError>  errors;

    public TransactionHistoryResponse(List<TransactionDTO> tranasctionList, List<AppError> errors) {
        this.tranasctionList = tranasctionList;
        this.success = true;
        this.errors = null;
    }

    public TransactionHistoryResponse(List<AppError> errors) {
        this.tranasctionList = null;
        this.success = false;
        this.errors = errors;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setErrors(List<AppError> errors) {
        this.errors = errors;
    }

    public boolean isSuccess() {

        return success;
    }

    public List<AppError>  getErrors() {
        return errors;
    }

    public void setTranasctionList(List<TransactionDTO> tranasctionList) {
        this.tranasctionList = tranasctionList;

    }

    public List<TransactionDTO> getTranasctionList() {
        return tranasctionList;
    }


}
