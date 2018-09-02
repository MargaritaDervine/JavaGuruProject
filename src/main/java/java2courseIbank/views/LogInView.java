package java2courseIbank.views;

import java2courseIbank.domain.User;
import org.springframework.stereotype.Component;
import java2courseIbank.businessLogic.services.LogInService;

import java.util.Optional;
import java.util.Scanner;
@Component
public class LogInView implements ConsoleView{
    LogInService logInService;

    public LogInView(LogInService logInService) {
        this.logInService = logInService;
    }

    @Override
    public void execute() {

    }
    public User executeUser() {
        String id = getId();
        String pwd = getPwd();
        User user = null;
        Optional<User> optUser = logInService.logIn(id, pwd);

        if(optUser.isPresent()){
            user = optUser.get();
        } else System.out.println("User does not exist, try again");

        return user;
    }

    private static String getId(){
        System.out.print("ID:");
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    private static String getPwd(){
        System.out.print("Password:");
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }
}
