package softwaredesign.commands;
import softwaredesign.statistics.*;

import java.util.Map;
import java.util.Set;

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
    @Override
    public String getName() {
        return "statistic";
    }

    private final StatisticFramework statisticFramework = StatisticFramework.getInstance();

    public StatisticCommand() {
        this.argumentParser
                .addRequiredArgument("name", statisticFramework.getModuleNames())
                .addOptionalArgument("sort-by", Set.of(
                        "commits",
                        "loc"
                ));
    }

    @Override
    public void run(Map<String, String> arguments) throws UserFacingException {
        String statisticName = arguments.get("name");
        this.statisticFramework.runStatistic(statisticName, arguments);
    }

    @Override
    public String getDescription() {
        return "Allows you to view various statistics of the Git repository.";
    }

    @Override
    public String getExamples() {
        return "statistic --name=contributors, " +
                "statistic --name=weekdays --sort-by=loc";
    }
}
