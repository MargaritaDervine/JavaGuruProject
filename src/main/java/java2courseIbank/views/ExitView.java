package java2courseIbank.views;

import org.springframework.stereotype.Component;

@Component
public class ExitView implements ConsoleView{
    @Override
    public void execute() {
        System.out.println("Exiting");
        System.exit(0);
    }

}
