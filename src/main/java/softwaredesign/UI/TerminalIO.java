package softwaredesign.ui;
import java.util.Scanner;

/**
 * This class is a utility class with static methods that allows basic I/O with the terminal
 * (reading, writing, and a utility prompt() method that performs a write and then a read).
 * This class hides all the information having to do with terminals in java, the methods are not
 * insanely deep, but the java equivalents are very verbose. This is why we chose to create this
 * class.
 *
 * @author Ammar
 */
@SuppressWarnings("java:S106")
public class TerminalIO {
    private TerminalIO() {}

    private static final Scanner scanner = new Scanner(System.in);

    public static String read() {
        return scanner.nextLine();
    }

    public static void write(String message) {
        System.out.print(message);
    }

    public static void writeInPlace(String message) {
        write("\r" + " ".repeat(80) + "\r" + message);
    }

    public static String prompt(String message) {
        write(message);
        return read();
    }

    public static void printException(Exception exception) {
        System.err.println("The application encountered an unexpected error: \n");
        exception.printStackTrace(System.err);
    }
}
