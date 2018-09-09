package java2courseIbank.businessLogic.services.TransactionHistory;

public class TransactionHistoryRequest {
    String accountNumber;
    String username;

    public TransactionHistoryRequest(String accountNumber, String username) {
        this.accountNumber = accountNumber;
        this.username = username;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getUsername() {
        return username;
    }
}
