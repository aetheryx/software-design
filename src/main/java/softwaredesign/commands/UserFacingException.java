package softwaredesign.commands;

import softwaredesign.ui.TerminalIO;

/**
 * A custom exception that is caught by the command loop of the {@link CommandFramework}.<br/>
 * This exception can be thrown from any part of a command. The exception will be caught
 * and returned to the user in a human-friendly format. <br/>
 * This exception should only be used for expected user errors. Unexpected errors should be other kinds of
 * exceptions instead, and they will be handled by the command framework as "unexpected errors".
 */
public class UserFacingException extends Exception {
    private final StringBuilder userFacingMessage;

    /**
     * Creates a new user facing exception.
     *
     * @param userFacingMessage The message that should be displayed to the user.
     */
    public UserFacingException(String userFacingMessage) {
        this.userFacingMessage = new StringBuilder(userFacingMessage);
    }

    /**
     * Appends to the message that is displayed to the user.
     *
     * @param toAppend The message to append
     * @return The exception instance, so calls can be chained
     */
    public UserFacingException append(String toAppend) {
        this.userFacingMessage.append(toAppend);
        return this;
    }

    /**
     * Prints this exception to the terminal.
     */
    public void print() {
        TerminalIO.write(this.userFacingMessage.toString() + "\n");
    }
}
