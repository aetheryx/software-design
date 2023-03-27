package softwaredesign.CommandModule;

import softwaredesign.Application;
import softwaredesign.UI.TerminalIO;

import java.util.Map;

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

        TerminalIO.write(command.getDescription());
    }
}
