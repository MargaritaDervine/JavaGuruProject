package views;

import domain.User;
import services.LogInService;

import java.util.Optional;
import java.util.Scanner;

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
