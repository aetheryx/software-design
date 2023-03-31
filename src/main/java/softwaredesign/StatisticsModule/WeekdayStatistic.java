package softwaredesign.StatisticsModule;

import softwaredesign.UI.Table;

import java.util.Map;
import java.util.stream.Collectors;

public class WeekdayStatistic extends GitStatistic {
    public WeekdayStatistic() {
        super("weekdays");
    }

    @Override
    public Table calculate(Map<String, String> arguments) {
        Table table = new Table("Weekday", "commits", "loc");

        repository.getCommits()
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
