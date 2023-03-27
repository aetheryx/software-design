package softwaredesign.CommandModule;
import softwaredesign.StatisticsModule.*;
import softwaredesign.UI.TerminalIO;

import java.util.Map;

/**
 * This class is going to initiate the Statistic calculation.
 *
 * <p>
 *     Before calculation it is going to set
 *     the "arguments" variable in it's own ArgumentParser instance.
 * </p>
 * @author Zain
 */
public class StatisticCommand extends Command {
    private final StatisticFramework statisticFramework = StatisticFramework.getInstance();

    public StatisticCommand() {
        this.argumentParser
                .addArgument("name", statisticFramework.getAllStatisticNames())
                .addArgument("sort-by", new String[] {
                    "commits",
                    "loc"
                });
    }

    @Override
    public void run(Map<String, String> arguments) {
        String statisticName = arguments.get("name");
        this.statisticFramework.runStatistic(statisticName, arguments);
    }

    @Override
    public String getName() {
        return "statistic";
    }
}
