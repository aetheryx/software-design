package softwaredesign.StatisticsModule;

import softwaredesign.Application;
import softwaredesign.CommandModule.StatisticCommand;
import softwaredesign.CommandModule.UserFacingException;
import softwaredesign.RepositoryModule.Repository;
import softwaredesign.UI.Table;
import softwaredesign.UI.TerminalIO;
import softwaredesign.Util.Framework;

import java.util.Map;

/**
 * Represents a statistic that can be calculated on a GitHub repository.
 * Contains a name property, which is the name of the statistic (e.g. “most-active-contributors”), which the
 * {@link StatisticCommand} uses to select this statistic based on the user’s input string.
 * The calculation of the statistic happens in the {@link Statistic#calculate(Map)} method.
 *
 * Implements the {@link Framework.Module} interface, because instances of the Statistic class are
 * modules in the {@link StatisticFramework} class used by the {@link Statistic} command.
 * @author Zain
 */
public abstract class Statistic implements Framework.Module {
    private String name;
    protected Repository repository = Application.getInstance().getRepository();

    public Statistic(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public final void execute(Map<String, String> arguments) throws UserFacingException {
        Table table = this.calculate(arguments);

        String sortArgument = arguments.get("sort-by");
        if (sortArgument != null) {
            try {
                table.sort(sortArgument);
            } catch (IllegalArgumentException e) {
                throw new UserFacingException("The `--sort-by` argument must take a valid table column name, and the values in this column must be integers.");
            }
        }

        TerminalIO.write(table.toString());
    }

    protected abstract Table calculate(Map<String, String> arguments) throws UserFacingException;
}
