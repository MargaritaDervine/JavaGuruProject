package java2courseIbank.businessLogic.services.User;

import java2courseIbank.web.UserDTO;

public class GetUserResponse {
    private UserDTO user;

    public GetUserResponse(UserDTO user) {
        this.user = user;
    }

    public UserDTO getUser() {
        return user;
    }
}
