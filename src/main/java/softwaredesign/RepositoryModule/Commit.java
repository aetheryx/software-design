package softwaredesign.RepositoryModule;

/**
 * This class is a datastructure to store commit data, which is its only responsibility
 * <p>
 *     This class has no methods other than getters setters and constructors, this means that getX will getX and do nothing else.
 * </p>
 */
public class Commit {

    private String id;
    private String description;
    private String author;
    private int diffAdded;
    private int diffRemoved;
    private String date;

    private String branch;

    /**
     * <p>
     *  this method constructs a commit from the parameters given.
     * </p>
     * @returns the commit created
     */
    public Commit(String newID, String newDescription, String newAuthor, int newDiffAdded, int newDiffRemoved, String newDate, String newBranch){
        id = newID;
        description = newDescription;
        author = newAuthor;
        diffAdded = newDiffAdded;
        diffRemoved = newDiffRemoved;
        date = newDate;
        branch = newBranch;
    }

    /**
     * <p>
     *  gets the git id of the commit.
     * </p>
     * @returns the identifier of the commit in GitHub in string format.
     */
    public String getId() {
        return id;
    }

    /**
     * <p>
     *  gets the description the developer added to the commit
     * </p>
     * @returns the description the developer added to the commit when they made this commit in string form
     * @author Joachim
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * <p>
     *  gets the name of the author of the commit account
     * </p>
     * @returns the author GitHub account name who made the commit in string form
     * @author Joachim
     */
    public String getAuthor() {
        return author;
    }

    /**
     * <p>
     *  gets the amount of lines added in the changes in this commit.
     * </p>
     * @returns the amounts of lines added with this commit in integer form
     * @author Joachim
     */
    public int getDiffAdded() {
        return diffAdded;
    }

    /**
     * <p>
     *  gets the amount of lines removed in this commit in integer form.
     * </p>
     * @returns the amount of lines removed in integer form
     * @author Joachim
     */
    public int getDiffRemoved() {
        return diffRemoved;
    }
    /**
     * <p>
     *  gets the name of the branch this commit was fetched from in string form.
     * </p>
     * @returns the branch name as String
     * @author Joachim
     */
    public String getBranch(){ return branch; }

    /**
     * <p>
     *  gets the unparsed date in git log's date format.
     * </p>
     * @returns a String containing date.
     * @author Joachim
     */
    public String getDate(){ return date; }

    public String toString(){
        String toReturn = "";
        toReturn += String.format("    CommitID          = %s\n", getId());
        toReturn += String.format("    CommitAuthor      = %s\n", getAuthor());
        toReturn += String.format("    CommitDescription = %s\n", getDescription());
        toReturn += String.format("    CommitDiffAdded   = %d\n", getDiffAdded());
        toReturn += String.format("    CommitDiffRemoved = %d\n", getDiffRemoved());
        return toReturn;
    }
}
