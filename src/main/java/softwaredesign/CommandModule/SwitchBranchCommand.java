package softwaredesign.CommandModule;

import softwaredesign.UI.TerminalIO;

import java.io.IOException;
import java.util.Map;

/**
 * <h3>This Command will switch the active branch for the git repository.</h3>
 * <p>
 *     This class inherits from <a href=#@link>{@link Command}</a> and as it suggests,
 *     check Command for the usage of this command.
 * </p>
 * <p>
 *     Because switching the active branch requires action from  <a href=#@link>{@link softwaredesign.RepositoryModule.Repository}</a>
 *     This class is dependant on the repository class.
 * </p>
 *
 * @author joachim, zain
 */

public class SwitchBranchCommand extends Command {

    /**
     * This method switches the active branch from the repository module. If the branch is unavailable
     * then don't switch.
     * @author Joachim
     * */
    @Override
    public void run(Map<String, String> arguments) {
        try {
            getRepository().switchActiveBranch(arguments.get("branch"));
        } catch (IOException | InterruptedException e) {
            TerminalIO.write("branch unavailable: " + e.getMessage() + "\n");
        }
    }

    @Override
    public String getName() {
        return "switch-branch";
    }

    @Override
    public String getDescription() {
        return "switches the active branch to calculate statistics on a different branch";
    }

    @Override
    public String getUsage() {
        return "usage: switch-branch --branch=<INSERT BRANCHNAME>";
    }

    @Override
    public String getExamples() {
        return "switch-branch --branch=main";
    }
}