package java2courseIbank.web;

import java2courseIbank.businessLogic.services.User.GetUserRequest;
import java2courseIbank.businessLogic.services.User.GetUserResponse;
import java2courseIbank.businessLogic.services.User.GetUserService;
import java2courseIbank.web.DTOs.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private GetUserService getUserService;

  /*  @ResponseBody
    @RequestMapping(value = "/user/{username}", method = RequestMethod.GET)
    public UserDTO getUser(@PathVariable("username") String username) {
        GetUserRequest request = new GetUserRequest(username);
        GetUserResponse response = getUserService.get(request);
        return response.getUser();
    }
*/

}
