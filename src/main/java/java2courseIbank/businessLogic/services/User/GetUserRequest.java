package java2courseIbank.businessLogic.services.User;

public class GetUserRequest {
    private Long userId;

    public GetUserRequest(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }
}
