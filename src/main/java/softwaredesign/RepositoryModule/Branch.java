package softwaredesign.RepositoryModule;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Joachim
 * This class is responsible for storing the commits from a gitlog and for populating these commits.
 * <p>
 *     populating the commits in the branch happens inside <a href="#@link">{@link Branch#processGitLog()}</a>. With
 *     <a href="#@link">{@link Branch#getName()} the branchname can be retrieved, and
 *     <a href="#@link">{@link Branch#getCommits()}</a></a> returns the commits inside this branch.
 * </p>
 */
public class Branch {
    /**
     * @author Joachim
     * @return a string containing the name of the branch this <a href="#@link">{@link Branch}</a> represents inside
     * the GitHub repository
     */
    public String getName(){
        return "";
    }

    /**
     * @author Joachim
     * This method populates this branch with commits returned by gitLog
     * <p>
     *  This method might take some time due to the git log command taking a long time. Secondly, this method
     *  <strong>CAN FAIL</strong> if the branch or the internet is not available for example.
     * </p>
     */
    public void processGitLog(){

    }

    /**
     * @author Joachim
     * gets the list of commits in this branch.
     * @return an arraylist of <a href="#@link">{@link Commit}</a> inside this <a href="#@link">{@link Branch}</a>
     *  returns an emptyArrayList() if there are no available Commits.
     */
    public List<Commit> getCommits(){
        return new ArrayList<>();
    }
}
