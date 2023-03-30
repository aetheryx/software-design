package softwaredesign.StatisticsModule;

import softwaredesign.CommandModule.StatisticCommand;
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

    public Statistic(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public abstract void calculate(Map<String, String> arguments);
}
