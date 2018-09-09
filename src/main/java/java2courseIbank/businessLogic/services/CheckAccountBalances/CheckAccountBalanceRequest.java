package java2courseIbank.businessLogic.services.CheckAccountBalances;

import java2courseIbank.domain.User;

public class CheckAccountBalanceRequest {
    String username;

    public CheckAccountBalanceRequest(String user) {
        this.username = user;
    }

    public CheckAccountBalanceRequest() {
    }

    public void setUser(String user) {
        this.username = user;
    }

    public String getUser() {

        return username;
    }
}
