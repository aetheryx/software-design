package softwaredesign.StatisticsModule;

import softwaredesign.Util.Framework;

/**
 * The statistic framework.
 *
 * Manages all of the Statistic objects that are registered by the class itself.
 * The reason the framework registers the statistics themselves is because this framework
 * is not meant to be as generic as (for example) the Command framework; we are not expecting to
 * manage multiple statistic frameworks. By definition, this framework manages all statistics that
 * are available to us.
 * For that same reason, this framework also implements the Singleton design pattern.
 *
 * @author Zain
 */
public class StatisticFramework extends Framework<Statistic> {
    private static StatisticFramework instance = new StatisticFramework().registerStatisticModules();

    private StatisticFramework() {}

    public StatisticFramework registerStatisticModules() {
        this
                .register(new BranchStatistic())
                .register(new ContributorStatistic())
                .register(new IssueStatistic())
                .register(new PullStatistic())
                .register(new ReleaseStatistic())
                .register(new WeekdayStatistic());

        return this;
    }

    public String[] getAllStatisticNames() {
        return this.modules.keySet().toArray(new String[0]);
    }

    public static StatisticFramework getInstance() {
        return StatisticFramework.instance;
    }
}
