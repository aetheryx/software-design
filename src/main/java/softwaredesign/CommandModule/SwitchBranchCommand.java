package softwaredesign.CommandModule;

import java.util.Map;

/**
 * This command is going to switch the
 * currently active branch to the branch argument.
 *
 * @author Marko
 */

public class SwitchBranchCommand extends Command {
    @Override
    public void run(Map<String, String> arguments) {

    }

    @Override
    public String getName() {
        return "switch-branch";
    }

    @Override
    public String getDescription() {
        return "wip placeholder";
    }

    @Override
    public String getUsage() {
        return "wip placeholder";
    }

    @Override
    public String getExamples() {
        return "wip placeholder";
    }
}