package softwaredesign.CommandModule;

import softwaredesign.UI.TerminalIO;

import java.util.Map;

/**
 * The help command. Registered by default by the command framework.<br />
 * Allows users to receive a list of commands, or details on a specific command.
 */
public class HelpCommand extends Command {
    @Override
    public String getName() {
        return "help";
    }

    @Override
    public String getDescription() {
        return "Displays the list of available commands and information regarding them.";
    }

    @Override
    public String getUsage() {
        return "[--command=statistic,switch-branch,...]";
    }

    @Override
    public String getExamples() {
        return "help\n" +
                "help --command=statistic\n" +
                "help --command=delete-repository";
    }

    private CommandFramework commandFramework;

    public HelpCommand(CommandFramework commandFramework) {
        this.commandFramework = commandFramework;
        this.argumentParser
                .addArgument("command", commandFramework.getModuleNames());
    }

    @Override
    public void run(Map<String, String> arguments) {
        String commandName = arguments.get("command");
        Command command = commandFramework.getModule(commandName);

        String output = command == null
                ? this.listCommands()
                : this.describeCommand(command);

        TerminalIO.write(output);
    }

    private String listCommands() {
        StringBuilder sb = new StringBuilder("List of commands:\n");
        for (String commandName : commandFramework.getModuleNames()) {
            Command command = commandFramework.getModule(commandName);
            sb.append(String.format("  - %s - %s\n", command.getName(), command.getDescription()));
        }

        return sb.toString();
    }

    private String describeCommand(Command command) {
        return String.format(
                "Help for command \"%s\":\nUsage: \t%s\nDescription: \t%s\nExamples: \t%s",
                command.getName(),
                command.getUsage(),
                command.getDescription(),
                command.getExamples()
        );
    }
}
