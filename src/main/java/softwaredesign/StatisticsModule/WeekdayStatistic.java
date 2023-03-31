package softwaredesign.StatisticsModule;

import softwaredesign.UI.Table;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * Represents the "Most Popular Weekday" statistic.<br />
 *
 * Fetches all commits on the current branch, groups the commits by weekday, and
 * adds the relevant metrics to a table instance.<br />
 *
 * The returned table has <tt>Commits</tt> and <tt>LOC</tt> column headers, which can be sorted by.
 * The sorting logic is taken care of by {@link Statistic#execute(Map)}.
 */
public class WeekdayStatistic extends GitStatistic {
    @Override
    public String getName() {
        return "weekdays";
    }

    @Override
    public Table calculate(Map<String, String> arguments) {
        Table table = new Table("Weekday", "Commits", "LOC");

        getRepository().getCommits()
                .stream()
                .collect(Collectors.groupingBy(c -> c.getDate().getDayOfWeek()))
                .forEach((weekday, commits) -> {
                    int numLoc = commits.stream()
                            .map(c -> c.getDiffAdded() + c.getDiffRemoved())
                            .reduce(0, Integer::sum);

                    table.addEntry(weekday.name(), commits.size(), numLoc);
                });

        return table;
    }
}
