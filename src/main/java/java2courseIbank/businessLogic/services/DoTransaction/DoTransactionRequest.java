package java2courseIbank.businessLogic.services.DoTransaction;

public class DoTransactionRequest {
    private String accFrom;
    private String accTo;
    private double amt;
    String username;

    public String getUsername() {
        return username;
    }

    public DoTransactionRequest(String accFrom, String accTo, double amt, String username) {
        this.accFrom = accFrom;
        this.accTo = accTo;
        this.amt = amt;
        this.username = username;
    }

    public String getAccFrom() {
        return accFrom;
    }

    public String getAccTo() {
        return accTo;
    }

    public double getAmt() {
        return amt;
    }
}
