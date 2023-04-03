package softwaredesign.commands;

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
    public SwitchBranchCommand() {
        this.argumentParser.addRequiredArgument("branch", null);    // Add the required "branch" argument, which can be any string
    }

    /**
     * This method switches the active branch from the repository module. If the branch is unavailable
     * then don't switch.
     * @author Joachim
     * */
    @Override
    public void run(Map<String, String> arguments) throws UserFacingException {
        String branchArgument = arguments.get("branch");
        try {
            getRepository().switchActiveBranch(branchArgument);
            softwaredesign.ui.TerminalIO.write("switched active branch to: " + branchArgument + "\n");
        } catch (IOException e) {
            throw new UserFacingException("branch unavailable: " + e.getMessage() + "\n");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new UserFacingException("interrupted" + e.getMessage());
        } catch (IllegalArgumentException e) {
            throw new UserFacingException(e.getMessage()); // switchActiveBranch throws for invalid branch names
        }
    }
    @Override
    public String getName() {
        return "switch-branch";
    }

    @Override
    public String getDescription() {
        return "Switches the active branch to calculate statistics on a different branch";
    }

    @Override
    public String getExamples() {
        return "switch-branch --branch=main";
    }
}