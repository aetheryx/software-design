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
 */
public class CommandFramework extends Framework<Command> {
    /**
     * Starts the main command loop, which will infinitely prompt the user for
     * the next command to run.
     */
    public void startCommandLoop() {
        while (true) { // todo @Zain: add exit condition
            this.promptForCommand();
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
    private void promptForCommand() {
        String rawCommand = TerminalIO.prompt("Enter a command: ");
        List<String> parts = List.of(rawCommand.split(" "));
        String commandName = parts.get(0);
        Command command = this.getModule(commandName);
        if (command == null) {
            TerminalIO.write("No such command!");
            return;
        }

        if (command.argumentParser == null) {
            command.run(null);
            return;
        }

        String rawArguments = String.join(" ", parts.subList(1, parts.size()));
        Map<String, String> arguments = command.argumentParser.parse(rawArguments);
        command.run(arguments);
    }
}
