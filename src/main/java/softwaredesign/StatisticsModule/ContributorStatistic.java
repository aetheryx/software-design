package softwaredesign.StatisticsModule;

import softwaredesign.CommandModule.UserFacingException;
import softwaredesign.RepositoryModule.Commit;
import softwaredesign.UI.Table;

import java.util.*;
import java.util.stream.Collectors;


/**
 * <p>
 *     This class is responsible for calculating a ranking for every contributor based on contribution. This ranking
 *     should have 2 sorting options: 1, ranked on lines added, 2, ranked by amount of commits. The class achieves this
 *     by interfacing with the <a href=#@link>{@link softwaredesign.RepositoryModule.Repository}</a> class to retrieve
 *     raw data about the github repository to use in calculations.
 * </p>
 * <p>
 *     The <a href=#@link>{@link ContributorStatistic#calculate(Map)}</a> method actually starts calculation of this
 *     statistic and calls on the <a href=#@link>{@link softwaredesign.UI.ProgressBar}</a>
 *     and the <a href=#@link>{@link softwaredesign.UI.TerminalIO}</a> to report on progress and results.
 * </p>
 * */
public class ContributorStatistic extends GitStatistic {
    @Override
    protected Table calculate(Map<String, String> arguments) {
        Table table = new Table("Contributor", "commits", "loc");

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

    public ContributorStatistic() {
        super("contributors");
    }
}
