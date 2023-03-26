package softwaredesign.CommandModule;

import java.util.Map;

/**
 * This class is going to initiate the deletion of the locally copied repository.
 * @author Marko
 */

public class DeleteRepositoryCommand extends Command {

    String name = "remove-repo";
    @Override
    void run(Map<String, String> arguments) {

    }
}
