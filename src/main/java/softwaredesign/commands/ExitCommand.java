package softwaredesign.commands;

import softwaredesign.ui.TerminalIO;

import java.util.Map;

public class ExitCommand extends Command {
    @Override
    public String getName() {
        return "exit";
    }

    private CommandFramework commandFramework;
    public ExitCommand(CommandFramework commandFramework) {
        this.commandFramework = commandFramework;
    }

    @Override
    protected void run(Map<String, String> arguments) throws UserFacingException {
        try {
            getRepository().delete();
        } catch (Exception err) {
            TerminalIO.write("Something went wrong while cleaning up the repository files. You may need to remove the repository from your dick manually.\n\n");
        }

        this.commandFramework.stopCommandLoop();
        TerminalIO.write("Goodbye!");
    }

    @Override
    public String getDescription() {
        return "Allows you to view various statistics of the Git repository.";
    }

    @Override
    public String getExamples() {
        return "statistic --name=contributors\n" +
                "statistic --name=contributors --sort-by=loc\n" +
                "statistic --name=weekdays --sort-by=commits\n" +
                "statistic --name=issues";
    }
}
