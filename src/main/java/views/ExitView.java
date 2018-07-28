package views;

public class ExitView implements ConsoleView{
    @Override
    public void execute() {
        System.out.println("Exiting");
        System.exit(0);
    }

}
