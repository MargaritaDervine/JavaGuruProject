package java2courseIbank.businessLogic.services.DoTransaction;

public class DoTransactionRequest {
    private String accFrom;
    private String accTo;
    private double amt;

    public DoTransactionRequest(String accFrom, String accTo, double amt) {
        this.accFrom = accFrom;
        this.accTo = accTo;
        this.amt = amt;
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
