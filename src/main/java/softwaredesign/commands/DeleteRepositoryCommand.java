package softwaredesign.commands;

import softwaredesign.Application;
import softwaredesign.ui.TerminalIO;

import java.io.IOException;
import java.util.Map;

/**
 * This class is going to initiate the deletion of the locally copied repository, and prompt the user for a new URL.
 * This class follows the structure of the <a href="#@link">{@link Command}</a> fitting
 * inside the <a href="#@link">{@link CommandFramework}</a>.
 * @author Marko, Joachim
 */
public class DeleteRepositoryCommand extends Command {
    /**
     * <p>check {@link Command#run(Map)} for more info</p>
     * <p>
     *     This method actually deletes the repository from the disk, and handles the errors associated with deletion,
     *     and throws them back as UserFacingExceptions to the command framework
     * </p>
     * @author Joachim
     */
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
        return "Deletes the repository files from disk, and returns you to the repository cloning phase";
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
