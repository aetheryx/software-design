package softwaredesign.statistics;

import softwaredesign.repository.Commit;
import softwaredesign.ui.Table;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Represents the "Most Active Contributors" statistic.<br />
 *
 * Fetches all commits on the current branch, groups the commits by author, and
 * adds the relevant metrics to a table instance.<br />
 *
 * The returned table has <tt>Commits</tt> and <tt>LOC</tt> column headers, which can be sorted by.
 * The sorting logic is taken care of by {@link Statistic#execute(Map)}.
 */
public class ContributorStatistic extends Statistic {
    @Override
    public String getName() {
        return "contributors";
    }

    @Override
    protected Table calculate(Map<String, String> arguments) {
        Table table = new Table("Contributor", "Commits", "LOC");

        getRepository().getCommits()
                .stream()
                .collect(Collectors.groupingBy(Commit::getAuthor))
                .forEach((contributor, commits) -> {
                    int numLoc = commits.stream()
                            .map(c -> c.getDiffAdded() + c.getDiffRemoved())
                            .reduce(0, Integer::sum);

                    table.addEntry(contributor, commits.size(), numLoc);
                });

        return table;
    }
}
