package softwaredesign.StatisticsModule;

import softwaredesign.Util.Framework;

public class StatisticFramework extends Framework<Statistic> {
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
        return (String[]) this.items.keySet().toArray();
    }
}
