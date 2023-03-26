package softwaredesign.StatisticsModule;

import java.util.Map;

/**
 * This interface represents a statistic that can be calculated on a GitHub repository.
 * It contains a name property, which is the name of the statistic (e.g. “most-active-contributors”), which the
 * StatisticCommand uses to select this statistic based on the user’s input string.
 * The calculation of the statistic happens in the <a href="#@link">{@link Statistic#calculate(Map)}</a> method.
 *
 * @author Zain
 */
public abstract class Statistic {
    private String name;

    public Statistic(String name) {
        this.name = name;
        StatisticsManager.registerStatistic(this);
    }

    public String getName() {
        return this.name;
    }

    public abstract void calculate(Map<String, String> arguments);
}
