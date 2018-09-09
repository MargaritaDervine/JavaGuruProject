package java2courseIbank.web;

import java2courseIbank.businessLogic.services.CheckAccountBalances.CheckAccountBalanceRequest;
import java2courseIbank.businessLogic.services.CheckAccountBalances.CheckAccountBalanceResponse;
import java2courseIbank.businessLogic.services.CheckAccountBalances.CheckAccountBalanceService;
import java2courseIbank.database.AccountRepository;
import java2courseIbank.database.UserRepository;
import java2courseIbank.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class MainPageController {
    @Autowired
    CheckAccountBalanceService checkAccountBalanceService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AccountRepository repository;

    @ResponseBody
    @RequestMapping(value = "/user/{username}", method = RequestMethod.GET)
    public CheckAccountBalanceResponse getAccountsByUser(@PathVariable("username") String username) {
        CheckAccountBalanceRequest request = new CheckAccountBalanceRequest(username);
        Optional<User> userOpt = userRepository.getUser(request.getUser());
        if(userOpt.isPresent()){
            User user = userOpt.get();
            return  new CheckAccountBalanceResponse(repository.getAccountsByUser(user));
        }
        return  new CheckAccountBalanceResponse("User des not exist");
    }
}
