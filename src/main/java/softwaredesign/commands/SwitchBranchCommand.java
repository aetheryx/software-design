package softwaredesign.commands;

import java.io.IOException;
import java.util.Map;

/**
 * This command is going to switch the
 * currently active branch to the branch argument.
 *
 * @author Marko
 */

public class SwitchBranchCommand extends Command {
    @Override
    public void run(Map<String, String> arguments) throws UserFacingException {
        try {
            getRepository().switchActiveBranch(arguments.get("branch"));
        } catch (IOException | InterruptedException e) {
            throw new UserFacingException("branch unavailable: " + e.getMessage() + "\n");
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