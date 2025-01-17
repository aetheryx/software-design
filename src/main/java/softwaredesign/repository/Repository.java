package softwaredesign.repository;

import java.io.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.*;

import org.apache.commons.io.FileUtils;
import softwaredesign.ui.ProgressBar;
import softwaredesign.ui.TerminalIO;


/**
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
 *      <li> <a href="#@link">{@link softwaredesign.ui.TerminalIO}</a> to report errors to the user. </li>
 *
 *  </p>
 *  <p>
 *       Before destroying this class, one might want to call <a href="#@link">{@link Repository#delete()}</a> to actually
 *       delete the repository from the disk and free up space.
 *  </p>
 *  @author Joachim
 */
public class Repository {
    private static final String CLONE_PATH = "./ClonedRepository/";
    static final String GIT_GET_BRANCH_NAME_COMMAND = "git rev-parse --abbrev-ref HEAD";
    static final String GET_ALL_BRANCHES_COMMAND = "git --no-pager branch -r";
    private File repositoryRoot;

    /**
     * This class is responsible for storing the commits from a gitlog and for populating these commits.
     * <p>
     *     populating the commits in the branch happens inside <a href="#@link">{@link Repository.Branch#processGitLog(Repository)} ()}</a>. With
     *     <a href="#@link">{@link Branch#getName()} the branchname can be retrieved, and
     *     <a href="#@link">{@link Branch#getCommits()}</a></a> returns the commits inside this branch.
     * </p>
     * @author Joachim
     */
    private static class Branch {
        static final String GIT_LOG_COMMAND = "git --no-pager log --stat --word-diff=porcelain --date=raw";
        static final String GIT_LOG_N_COMMITS_COMMAND = "git rev-list --count "; // use with + branchName
        private Commit[] commits;
        private String branchName;
        public Branch(String newBranchName){
            branchName = newBranchName;
        }

        /**
         * @return a string containing the name of the branch this <a href="#@link">{@link Branch}</a> represents inside
         * the GitHub repository
         * @author Joachim
         */
        public String getName(){
            return branchName;
        }

        /**
         * <p>This function parses one raw commit from git log --stat, Commits should be split off before they are passed
         * to this function and this function should be called separately. This function is intended to work with
         * <a href=#@link> {@link Repository.Branch#processGitLog(Repository)}</a></p>
         * @param unparsedCommit the raw string of the commit in the git log format.
         * @return returns an instance of commit with their values set appropriately.
         * @author Joachim
         * */
        private Commit parseCommit(String unparsedCommit){
            String[] unparsedCommitLines = unparsedCommit.split("\n");
            String commitId = unparsedCommitLines[0];
            int commitNumberOfLineDeletions = 0;
            int commitNumberOfLineAdditions = 0;
            boolean commitIsAMerge = false;

            int j = 1; //represents the current line to be parsed.
            if (unparsedCommitLines[j].startsWith("Merge: ")){
                j++;
                commitIsAMerge = true;
            }
            String commitAuthorName = unparsedCommitLines[j].substring(8);
            j++;
            String commitDateRaw = unparsedCommitLines[j].substring(8);
            long commitDateUnix = Long.parseLong(commitDateRaw.split(" ")[0]);
            j += 2;
            String commitDescription = unparsedCommitLines[j].substring(4);

            //now for the line additions and removals
            try {
                if (!commitIsAMerge) {
                    String[] commitChanges = unparsedCommitLines[unparsedCommitLines.length - 1].split(", ");
                    for (String change : commitChanges) {
                        String number = change.split(" ")[0];
                        if (change.contains("-")) {
                            //deletion
                            commitNumberOfLineDeletions = Integer.parseInt(number);
                        } else if (change.contains("+")) {
                            //addition
                            commitNumberOfLineAdditions = Integer.parseInt(number);
                        }
                    }
                }
            }
            catch(NumberFormatException e){
                //do nothing, this is an empty commit, and therefore should leave changes at 0.
            }
            return new Commit(
                    commitId,
                    commitDescription,
                    commitAuthorName,
                    commitNumberOfLineAdditions,
                    commitNumberOfLineDeletions,
                    LocalDateTime.ofInstant(
                            Instant.ofEpochSecond(commitDateUnix),
                            TimeZone.getDefault().toZoneId()
                    ),
                    branchName
            );
        }

        /**
         * This method populates this branch with commits returned by gitLog
         * <p>
         *  This method might take some time due to the git log command taking a long time. Secondly, this method
         *  <strong>CAN FAIL</strong> if the branch or the internet is not available for example.
         * </p>
         * @author Joachim
         */
        public void processGitLog(Repository repository) throws IOException, InterruptedException {
            //executing git log
            String gitLogOutput;
            String nCommitsCommandOutput = Repository.getGitCommandOutput(GIT_LOG_N_COMMITS_COMMAND + branchName, repository.repositoryRoot);

            int nCommits = Integer.parseInt(nCommitsCommandOutput.substring(0, nCommitsCommandOutput.length() - 1));
            gitLogOutput = Repository.getGitCommandOutput(GIT_LOG_COMMAND, repository.repositoryRoot);

            //parsing git log
            String[] unparsedCommits;
            commits = new Commit[nCommits];
            unparsedCommits = gitLogOutput.split("\ncommit ");

            for (int i = 0; i < nCommits; i++){
                commits[i] = parseCommit(unparsedCommits[i]);
            }
        }

        /**
         * gets the list of commits in this branch.
         * @return an arraylist of <a href="#@link">{@link Commit}</a> inside this <a href="#@link">{@link Branch}</a>
         *  returns an emptyArrayList() if there are no available Commits.
         *  @author Joachim
         */
        public List<Commit> getCommits(){
            return new ArrayList<>();
        }
    }

    private Map<String, Branch> branches = new HashMap<>();
    private String gitHubURL;
    private String activeBranch;
    private String name;

    /**
     * <p>
     *     Asks git for current branch with
     * </p>
     * @author Joachim
     * */
    private String getCurrentBranchName() throws IOException, InterruptedException {
        //this method is not very deep, but it removes some duplicate code
        return getGitCommandOutput(GIT_GET_BRANCH_NAME_COMMAND, repositoryRoot).replace("\n","");
    }

    /**
     * creates git process, and returns the stdout of the process.
     * @param command the git command you could enter in the terminal.
     * @param workingDir the directory the command should be executed in File format.
     * */
    private static String getGitCommandOutput(String command, File workingDir) throws IOException, InterruptedException {
        ProcessBuilder gitCommandBuilder = new ProcessBuilder(command.split(" "));
        gitCommandBuilder.directory(workingDir);
        gitCommandBuilder.redirectErrorStream(true);
        Process gitCommandProcess = gitCommandBuilder.start();
        String result = new String(gitCommandProcess.getInputStream().readAllBytes());
        gitCommandProcess.waitFor();
        return result;
    }

    /**
     * This method creates an instance of Repository, clones it, and initiates the default branch (main or master) after which the
     * repository may be used as normal.
     * <p>
     *     This method needs to clone the repository, which takes quite some time (in case of the lunux repo some 17
     *     minutes). Therefore this method also takes quite some time. This method does however keep the user up to date
     *     on progress using a progress bar, and is therefore also dependant on the <a href="#@link">{@link softwaredesign.ui.ProgressBar}
     *     </a> class.
     * </p>
     * <p>
     *     This method <strong>CAN FAIL</strong> however. When no internet is available for example, or the repository
     *     does not exist. In this case, the Repository should be destroyed
     * </p>
     *
     * @param newGitHubURL the link to the GitHub repository to be investigated by the user.
     *
     *   @author Joachim
     */
    public Repository(String newGitHubURL) throws IOException, InterruptedException {
        delete(); //make sure the folder is empty.
        gitHubURL = newGitHubURL;

        try {
            this.cloneRepository();
            File cloneDir = new File(CLONE_PATH);
            if (cloneDir.list() != null && cloneDir.list().length > 0) {
                name = cloneDir.list()[0];
            } else {
                throw new IOException("Clone dir is empty");
            }
            repositoryRoot = new File(CLONE_PATH, name);
        } catch (IOException err) {
            throw new IOException("clone failed, check the url for spelling mistakes, and internet connection and try " +
                    "again \n"); //retry, cloning must have failed, because no actual cloned repo exists in the dir
        }


        activeBranch = getCurrentBranchName();
        switchActiveBranch(activeBranch);
        TerminalIO.writeInPlace("Successfully cloned repository. Use \"help\" to get started.");
        TerminalIO.write("\n\n");
    }

    private void cloneRepository() throws IOException, InterruptedException {
        ProgressBar progressBar = new ProgressBar("Cloning repository");
        progressBar.start();

        ProcessBuilder processBuilder = new ProcessBuilder(("git clone --progress " + gitHubURL).split(" "));
        processBuilder.directory(new File(CLONE_PATH));
        processBuilder.redirectErrorStream(true);

        processBuilder.redirectErrorStream(false);
        Process process = processBuilder.start();

        InputStream errorStream = process.getErrorStream();
        InputStreamReader errorStreamReader = new InputStreamReader(errorStream);
        BufferedReader bufferedReader = new BufferedReader(errorStreamReader);

        String line;
        while ((line = bufferedReader.readLine()) != null) {
            if (!line.contains("Receiving objects")) continue;

            String percentRaw = line.split(":")[1].split("%")[0].trim();
            int percent = Integer.parseInt(percentRaw);
            progressBar.setProgress(percent);
        }

        process.waitFor();
        if (process.exitValue() == 0) {
            progressBar.finish("Cloned repository successfully! Parsing git log...");
        } else {
            progressBar.finish();
        }
    }

    /**
     * This method gets the link to the repository
     *
     * @return the link to the repository
     * @author Joachim
     */
    public String getURL(){
        return gitHubURL;
    }
    /**
     * This method switches the branch and changes the output of<a href="#@link">{@link Repository#getCommits()}</a>.
     * <p>
     *     This method might take some time due to the git log command taking a long time. Secondly, this method
     *     <strong>CAN FAIL</strong> if the branch or the internet is not available for example. If this is the case,
     *     the user is notified through the <a href="#@link">{@link softwaredesign.ui.TerminalIO}</a> class, after which the method
     *     tries to switch back to the default branch, or destroys the repository, if we failed to switch to the
     *     default branch.
     * </p>
     *
     * @param branch the name of the branch the user intends to switch to. The branch must be a valid branch name, or
     *               the default branch will be switched to.
     * @author Joachim
     * */
    @SuppressWarnings("java:S3824") //check line 301
    public void switchActiveBranch(String branch) throws IOException, InterruptedException, IllegalArgumentException {
        //if the branch has not already been examined by the user before, make a new one, else switch to the stored branch
        String checkoutCommand = "git checkout " + branch;
        List<String> possibleBranches = getBranchNames();
        if (possibleBranches.contains(branch)) {
            getGitCommandOutput(checkoutCommand, repositoryRoot);

            //Sonarlint complains about the following line telling me that I should use computeIfAbsent, we cannot use
            //this due to anonymous functions not being allowed to throw errors that processGitLog() throws.
            if (!branches.containsKey(branch)) {
                branches.put(branch, new Branch(branch));
                branches.get(branch).processGitLog(this);
            }
            activeBranch = getCurrentBranchName();
        } else {
            throw new IllegalArgumentException("No branch exists with this name.");
        }
    }

    /**
     * <p>This method gets the link to the repository</p>
     * @return a list of <a href="#@link">{@link Commit}</a>
     * @author Joachim
     */
    public List<Commit> getCommits() {
        return List.of(branches.get(activeBranch).commits);
    }

    /**
     * removes the repository from disk, by cleaning out <strong>THE ENTIRE FOLDER</strong>
     * <p>
     *     After calling delete, the repository will be unusable, and a new repository should be instantiated to resume
     *     functionality of the <a href="#@link">{@link Repository}</a> class.
     * </p>
     * @author Joachim
     */
    public void delete() throws IOException {
        //method is not very deep, but it does hide the information of the repository file structure to the other classes.
        File cloneFolder = new File(CLONE_PATH);
        if (!cloneFolder.exists()){
            cloneFolder.mkdir();
        }
        FileUtils.cleanDirectory(new File(CLONE_PATH));
    }

    /**
     * <p>
     *     This method returns a list of all the branches one can switch to using <a href=#@link> {@link Repository#switchActiveBranch(String)}</a>
     * </p>
     * @author Joachim
     * @return an arraylist of strings containing all branches.
     * @throws IOException if the git repository is not available
     * @throws InterruptedException if the interfacing with git is interrupted.
     * */
    public List<String> getBranchNames() throws IOException, InterruptedException {
        List<String> branchNames = new ArrayList<>();
        String allBranches = getGitCommandOutput(GET_ALL_BRANCHES_COMMAND, repositoryRoot);

        //parse branchnames
        String[] unparsedBranches = allBranches.split("\n {2}origin/");

        //remove \n at the last branchname
        if(unparsedBranches.length > 0) unparsedBranches[unparsedBranches.length - 1] = unparsedBranches[unparsedBranches.length - 1].replace("\n", "");
        for (String branch : unparsedBranches) { //add the branchnames one by one.
            if (!branch.contains("HEAD")){ //make sure this branch is removed, I dont know what this branch is exactly.
                branchNames.add(branch.replace("  origin/", "").replace("\n",""));
            }
        }
        return branchNames;
    }
}