package softwaredesign;
import java.util.Scanner;

public class TerminalIO {
    private static Scanner scanner = new Scanner(System.in);

    public static String read() {
        return scanner.nextLine();
    }

    public static void write(String message) {
        System.out.print(message);
    }

    public static String prompt(String message) {
        System.out.print(message);
        return scanner.nextLine();
    }
}
