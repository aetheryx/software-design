package softwaredesign.CommandModule;

import softwaredesign.UI.TerminalIO;
import softwaredesign.Util.Framework;

import java.util.List;
import java.util.Map;

/**
 * The command framework.
 *
 * Manages a number of Command objects that are externally registered, and contains
 * the methods that handle selecting the appropriate registered command module based on
 * raw user input (by parsing it), and preparing the arguments for the command by using it's
 * {@link ArgumentParser} instance.
 *
 * @author Zain
 */
public class CommandFramework extends Framework<Command> {
    private boolean commandLoopActive = false;
    /**
     * Starts the main command loop, which will infinitely prompt the user for
     * the next command to run.
     */
    public void startCommandLoop() {
        this.commandLoopActive = true;
        while (this.commandLoopActive) {
            this.commandLoop();
        }
    }

    private void commandLoop() {
        try {
            this.promptForCommand();
        } catch (UserFacingException userFacingException) {
            userFacingException.print();
        }
    }

    /**
     * Prompts the user for a command, and then parses this raw input into
     * two parts: the command name, and the raw command arguments.
     * The command name is used to select the appropriate {@link Command} framework module.
     * Once the appropriate {@link Command} has been selected, the
     * raw command arguments are parsed using the {@link Command}'s {@link ArgumentParser}.
     * Once the arguments have been parsed, the {@link Command#run(Map)} method is called, running the command.
     */
    private void promptForCommand() throws UserFacingException {
        String rawCommand = TerminalIO.prompt("$ ");
        List<String> parts = List.of(rawCommand.split(" "));
        String commandName = parts.get(0);
        Command command = this.getModule(commandName);
        if (command == null) {
            throw new UserFacingException("No such command! Try again.");
        }

        Map<String, String> arguments;
        try {
            String rawArguments = String.join(" ", parts.subList(1, parts.size()));
            arguments = command.argumentParser.parse(rawArguments);
        } catch (Exception err) {
            throw new UserFacingException("Incorrect arguments for command \"" + commandName + "\". Try again.");
        }

        try {
            command.run(arguments);
        } catch (Exception err) {
            TerminalIO.write("Failed to execute command:\n");
            err.printStackTrace(System.out);
        }
    }
}
