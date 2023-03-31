package softwaredesign.commands;

import softwaredesign.Application;
import softwaredesign.ui.TerminalIO;

import java.io.IOException;
import java.util.Map;

/**
 * This class is going to initiate the deletion of the locally copied repository.
 * @author Marko
 */

public class DeleteRepositoryCommand extends Command {
    @Override
    public void run(Map<String, String> arguments) throws UserFacingException {
        try {
            getRepository().delete();
            Application.getInstance().initialiseRepository();
            TerminalIO.write("deletion successful \n");
        } catch (IOException e) {
            throw new UserFacingException("the repository cannot be deleted at this time, check if the file is open " +
                    "in another program");
        }
    }

    @Override
    public String getDescription() {
        return "deletes the repository files from disk, and returns you to the repository cloning phase";
    }

    @Override
    public String getUsage() {
        return "delete";
    }

    @Override
    public String getExamples() {
        return "delete";
    }

    @Override
    public String getName() {
        return "delete";
    }
}
