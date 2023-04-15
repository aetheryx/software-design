package softwaredesign.statistics;

import softwaredesign.Application;
import softwaredesign.commands.UserFacingException;
import softwaredesign.repository.Repository;
import softwaredesign.ui.Table;
import softwaredesign.ui.TerminalIO;
import softwaredesign.util.Framework;

import java.util.Map;

/**
 * Represents a statistic that can be calculated on a GitHub repository.<br /><br />
 * <p>
 * Implements the {@link Framework.Module} interface, because instances of the
 * Statistic class are modules in the {@link StatisticFramework} class.
 * Statistic modules are identified by their {@link #getName()} method. Subclasses must override this method.</p>
 * <p>
 * This class applies the Template Method design pattern. The public {@link #execute(Map)} method
 * calls the {@link #calculate(Map)} abstract (template) method, which can very simply return a {@link Table} instance.
 * The outer {@link #execute(Map)} method takes care of sorting the table returned by {@link #calculate(Map)}, and
 * printing it to the terminal.</p>
 *
 * @author Zain
 */
public abstract class Statistic implements Framework.Module {
    /**
     * A protected utility method for subclasses to get access to the Repository.
     *
     * @return A reference to the currently active repository
     */
    protected Repository getRepository() {
        return Application.getInstance().getRepository();
    }

    /**
     * Calculates a statistic against the currently active repository and returns the
     * result to the user.<br />
     * The Template Method design pattern is applied here. This method receives the
     * calculated tabular data from the {@link #calculate(Map)} method,
     * sorts this data according to the <tt>sort-by</tt> argument, and then prints it.
     *
     * @param arguments The command arguments from the StatisticCommand.
     */
    public final void execute(Map<String, String> arguments) throws UserFacingException {
        Table table = this.calculate(arguments);

        String sortArgument = arguments.get("sort-by");
        if (sortArgument != null) {
            try {
                table.sort(sortArgument);
            } catch (IllegalArgumentException e) {
                throw new UserFacingException("The `--sort-by` argument must take a valid column name, and the values in this column must be integers.");
            }
        } else {
            // sort by "commits" column by default
            try {
                table.sort("commits");
            } catch (IllegalArgumentException e) {
                // ignore, table doesn't have a "commits" column
            }
        }

        TerminalIO.write(table.toString());
    }

    /**
     * Where the actual calculation of statistics happens. Subclasses should override this method.
     *
     * @param arguments The command arguments from the StatisticCommand.
     * @return An instance of the {@link Table} class, populated with data.
     */
    protected abstract Table calculate(Map<String, String> arguments) throws UserFacingException;
}
