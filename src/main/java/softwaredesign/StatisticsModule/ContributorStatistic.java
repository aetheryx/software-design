package softwaredesign.StatisticsModule;

import softwaredesign.RepositoryModule.Commit;
import softwaredesign.UI.ProgressBar;
import softwaredesign.UI.Table;

import javax.swing.plaf.IconUIResource;
import java.util.*;

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
    private static final String STATISTIC_CALCULATING_TASK_NAME = "(Step 1/2): calculating most active contributors...";
    private static final String RESULT_GENERATING_TASK_NAME = "(Step 2/2): generating results...";

    private class Contributor{
        private int linesOfCodeChanged;
        private int nCommits;

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

    private class LinesOfCodeComparator implements Comparator<Contributor>{
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

    @Override
    public void calculate(Map<String, String> arguments) {
        List<Commit> commits = repository.getCommits();
        ProgressBar progressBar = new ProgressBar(STATISTIC_CALCULATING_TASK_NAME, 20); //TODO: how long should this bar be?
        progressBar.start();
        Map<String, Contributor> contributors = new HashMap<>();
        Table table = new Table(new String[]{"Author name", "number of lines changed", "number of commits"});

        for (int i = 0; i < commits.size(); i++) {
            Commit commitToProcess = commits.get(i);
            progressBar.setProgress((float) i / (float)commits.size()); //this updates the progressbar on screen
            if (!contributors.containsKey(commitToProcess.getAuthor())){
                contributors.put(commitToProcess.getAuthor(), new Contributor(commitToProcess.getDiffAdded(), 1));
            }
            else {
                contributors.get(commitToProcess.getAuthor()).nCommits ++;
                contributors.get(commitToProcess.getAuthor()).linesOfCodeChanged += commitToProcess.getDiffAdded();
            }
        }
        progressBar.finish("success");

        progressBar = new ProgressBar(RESULT_GENERATING_TASK_NAME, 20); //TODO: how long should this bar be?
        List<Contributor> contributorList = new ArrayList<>(contributors.values());
        contributorList.sort(new CommitsComparator());
        for (int i = 0; i < contributors.size(); i++) {
            progressBar.setProgress((float) i / (float)contributors.size());
            table.addEntry(); //TODO: add arguments
        }
        progressBar.finish(table.toString());
    }

    public ContributorStatistic() {
        super("contributors");
    }
}
