package softwaredesign;

public class Main {
    public static void main (String[] args){
        System.out.println("Welcome to Software Design");
        Application application = Application.getInstance();
        application.run();
    }
}
