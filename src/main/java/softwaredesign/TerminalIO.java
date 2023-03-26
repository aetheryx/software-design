package softwaredesign;
import java.util.Scanner;

    /**
    This class is a utility class with static methods that allows basic I/O with the terminal
    (reading, writing, and a utility prompt() method that performs a write and then a read).
    This class hides all the information having to do with terminals in java, the methods are not
    insanely deep, but the java equivalents are very verbose. This is why we chose to create this
    class.

    @author Ammar
     */

public class TerminalIO {
    private static Scanner scanner = new Scanner(System.in);

    public static String read() {
        return scanner.nextLine();
    }

    public static void write(String message) {
        System.out.print(message);
    }

    public static String prompt(String message) {
        write(message);
        return read();
    }
}
