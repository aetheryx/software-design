package softwaredesign.CommandModule;

import java.util.Map;

/**
 * This class is going to initiate the deletion of the locally copied repository.
 * @author Marko
 */

public class DeleteRepositoryCommand extends Command {
    @Override
    public void run(Map<String, String> arguments) {

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

    @Override
    public String getName() {
        return "delete-repository";
    }
}
