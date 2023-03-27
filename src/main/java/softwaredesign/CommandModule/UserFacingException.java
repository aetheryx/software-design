package softwaredesign.CommandModule;

import softwaredesign.UI.TerminalIO;

public class UserFacingException extends Exception {
    private StringBuffer userFacingMessage;
    public UserFacingException(String userFacingMessage) {
        this.userFacingMessage = new StringBuffer(userFacingMessage);
    }

    public UserFacingException append(String toAppend) {
        this.userFacingMessage.append(toAppend);
        return this;
    }

    public void print() {
        TerminalIO.write(this.userFacingMessage.toString() + "\n");
    }
}
