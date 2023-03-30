package softwaredesign.StatisticsModule;

import java.util.Comparator;
import java.util.Map;
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
    public ContributorStatistic() {
        super("contributors");
    }


    private class Contributor{
        private int linesOfCodeChanged;
        private int nCommits;

        public class LinesOfCodeComparator implements Comparator<Contributor>{
            @Override
            public int compare(Contributor contributor1, Contributor secondContributor) {
                return contributor1.linesOfCodeChanged - secondContributor.linesOfCodeChanged;
            }
        }
        private class CommitsComparator implements Comparator<Contributor>{
            @Override
            public int compare(Contributor contributor1, Contributor secondContributor) {
                return contributor1.nCommits - secondContributor.nCommits;
            }
        }
        public Contributor(int newLinesOfCodeChanged, int newNCommits){
            nCommits = newNCommits;
            linesOfCodeChanged = newLinesOfCodeChanged;
        }
        public int getLinesOfCodeChanged() {
            return linesOfCodeChanged;
        }

        public int getnCommits() {
            return nCommits;
        }

    }
    @Override
    public void calculate(Map<String, String> arguments) {
        // todo
    }
}
