package softwaredesign.statistics;

import softwaredesign.commands.UserFacingException;
import softwaredesign.repository.Commit;
import softwaredesign.ui.ProgressBar;
import softwaredesign.ui.Table;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents the "Most Active Branch" statistic.
 * @author Joachim
 */
public class BranchStatistic extends Statistic {
    @Override
    public String getName() {
        return "branches";
    }

    @Override
    public Table calculate(Map<String, String> arguments) throws UserFacingException {
        Map<String, Integer> locsInBranches = new HashMap<>();
        Map<String, Integer> commitsInBranches = new HashMap<>();
        Table tableResult = new Table("Branch", "Commits", "LOC");
        List<String> branchNames;
        ProgressBar progressBar = new ProgressBar("Calculating most active branch");
        progressBar.start();

        try {
            branchNames = getRepository().getBranchNames();
            for (int i = 0; i < branchNames.size(); i++) {
                getRepository().switchActiveBranch(branchNames.get(i));
                locsInBranches.putIfAbsent(branchNames.get(i), 0);
                commitsInBranches.putIfAbsent(branchNames.get(i), 0);
                for (Commit commit : getRepository().getCommits()) {
                    locsInBranches.compute(commit.getBranch(), (k, v) -> v + commit.getDiffAdded() + commit.getDiffRemoved());
                    commitsInBranches.compute(commit.getBranch(), (k, v) -> v + 1);
                }

                progressBar.setProgress((i * 100) / branchNames.size());
            }

            progressBar.finish();

            for (String branch: branchNames) {
                tableResult.addEntry(branch, commitsInBranches.get(branch), locsInBranches.get(branch));
            }
            return tableResult;
        } catch (IOException e) {
            throw new UserFacingException("git repository not available: " +e.getMessage());
        } catch (InterruptedException e) {
            throw new UserFacingException("something or someone interrupted git: " +e.getMessage());
        }
    }
}
