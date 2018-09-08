package java2courseIbank.businessLogic.services.User;

public class GetUserRequest {
    private String username;

    public GetUserRequest(String username) {
        this.username = username;
    }

    public String getUserName() {
        return username;
    }
}
