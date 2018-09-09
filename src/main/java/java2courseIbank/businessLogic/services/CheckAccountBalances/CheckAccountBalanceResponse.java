package java2courseIbank.businessLogic.services.CheckAccountBalances;

import java2courseIbank.domain.Account;

import java.util.List;

public class CheckAccountBalanceResponse {
    List<Account> accList;
    String error;

    public CheckAccountBalanceResponse(String error) {
        this.error = error;
    }

    public CheckAccountBalanceResponse() {
    }

    public CheckAccountBalanceResponse(List<Account> accList) {
        this.accList = accList;
    }

    public void setAccList(List<Account> accList) {
        this.accList = accList;
    }

    public List<Account> getAccList() {

        return accList;
    }

}
