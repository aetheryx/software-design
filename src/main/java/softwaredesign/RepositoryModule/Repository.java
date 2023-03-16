package softwaredesign.RepositoryModule;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.io.File;
import java.util.Map;

/**
 * @author Joachim
 *  This a class that can clone a repository, and store all of its contents in underlying classes.
 *  <p>
 *      The creation of a Repository using the constructor <a href=#@link>{@link Repository()}</a> using an url,
 *      this url can then be recalled with <a href=#@link>{@link Repository#getURL()}</a>
 *  </p>
 *  <p>
 *      The repository branch can be specified with <a href="#@link">{@link Repository#switchActiveBranch(String)} ()}
 *      </a> which is comparable to git Checkout. The commits posted in the repository can be viewed with
 *      <a href="#@link">{@link Repository#getCommits()} ()}</a>.
 *  </p>
 *  <p>
 *      This class is dependant on the following:
 *      <li> <a href="#@link">{@link TerminalIO}</a> to report errors to the user. </li>
 *
 *  </p>
 *  <p>
 *       Before destroying this class, one might want to call <a href="#@link">{@link Repository#delete()}</a> to actually
 *       delete the repository from the disk and free up space.
 *  </p>
 */
public class Repository {
    private static final String filePath = "./Clonedrepository/";
    private Map<String, Branch> branches = new HashMap<>();
    private String gitHubURL;
    private String name;
    /**
     * @author Joachim
     * This method creates an instance of Repository, clones it, and initiates the default branch (main or master) after which the
     * repository may be used as normal.
     * <p>
     *     This method needs to clone the repository, which takes quite some time (in case of the lunux repo some 17
     *     minutes). Therefore this method also takes quite some time. This method does however keep the user up to date
     *     on progress using a progress bar, and is therefore also dependant on the <a href="#@link">{@link ProgressBar}
     *     </a> class.
     * </p>
     * <p>
     *     This method <strong>CAN FAIL</strong> however. When no internet is available for example, or the repository
     *     does not exist. In this case, the Repository should be destroyed
     * </p>
     * <P>
     *     @param gitHubURL the link to the GitHub repository to be investigated by the user.
     * </P>
     */
    private void runGitCommand(String command, String workingDir) throws IOException, InterruptedException {
        //build the process and redirect the error and input stream additional to running
        ProcessBuilder gitCloneProcessBuilder = new ProcessBuilder(command.split(" "));
        gitCloneProcessBuilder.directory(new File(workingDir));
        gitCloneProcessBuilder.redirectErrorStream(true);
        gitCloneProcessBuilder.redirectOutput(ProcessBuilder.Redirect.INHERIT);

        Process gitCloneProcess = gitCloneProcessBuilder.start();
        gitCloneProcess.waitFor(); //waits for the process to complete
    }
    public Repository(String newGitHubURL) throws IOException, InterruptedException {
        delete(); //make sure the folder is empty.
        gitHubURL = newGitHubURL;

        String cloneCommand = "git clone --progress " + gitHubURL;
        runGitCommand(cloneCommand, filePath);
        File cloneDir = new File(filePath);
        name = cloneDir.list()[0];
        return;

    }

    /**
     * @author Joachim
     * This method gets the link to the repository
     * <p>
     * @return the link to the repository
     * </p>
     */
    public String getURL(){
        return gitHubURL;
    }
    /**
     * @author Joachim
     * This method switches the branch and changes the output of<a href="#@link">{@link Repository#getCommits()}</a>.
     * <p>
     *     This method might take some time due to the git log command taking a long time. Secondly, this method
     *     <strong>CAN FAIL</strong> if the branch or the internet is not available for example. If this is the case,
     *     the user is notified through the <a href="#@link">{@link TerminalIO}</a> class, after which the method
     *     tries to switch back to the default branch, or destroys the repository, if we failed to switch to the
     *     default branch.
     * </p>
     * <p>
     * @param branch the name of the branch the user intends to switch to. The branch must be a valid branch name, or
     *               the default branch will be switched to.
     * </p>
     * */
    public void switchActiveBranch(String branch) throws IOException, InterruptedException {
        String checkoutCommand = "git checkout " + branch;
        try {
            runGitCommand(checkoutCommand, filePath + "/" + name);
        }catch ()
    }

    /**
     * @author Joachim
     * <p>This method gets the link to the repository</p>
     * @return a list of <a href="#@link">{@link Commit}</a>
     *
     */
    public List<Commit> getCommits(){
        return new ArrayList<>();
    }
    /**
     * @author Joachim
     * removes the repository from disk, by cleaning out <strong>THE ENTIRE FOLDER</strong>
     * <p>
     *     After calling delete, the repository will be unusable, and a new repository should be instantiated to resume
     *     functionality of the <a href="#@link">{@link Repository}</a> class.
     * </p>
     */
    public void delete(){

    }
}
