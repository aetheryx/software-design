package softwaredesign.CommandModule;

import softwaredesign.Application;
import softwaredesign.RepositoryModule.Repository;
import softwaredesign.UI.TerminalIO;

import java.io.IOException;
import java.util.Map;

/**
 * <h3>This Command will delete the repository and call initialise repository again.</h3>
 * <p>
 *     This class inherits from <a href=#@link>{@link Command}</a> and as it suggests,
 *     check Command for the usage of this command.
 * </p>
 * <p>
 *      deleting the files from disk, requires dependencies from
 *      <a href=#@link>{@link Repository()}</a> and  <a href=#@link>{@link Application()}</a>
 * </p>

 * @author Joachim, Zain
 */
public class DeleteRepositoryCommand extends Command {
    /**
     * this method calls  <a href=#@link>{@link Repository#delete()}</a> and  <a href=#@link>{@link Application#initialiseRepository()}</a>
     * after successful deletion.
     *
     * */
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
