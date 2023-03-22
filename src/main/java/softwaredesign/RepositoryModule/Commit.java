package softwaredesign.RepositoryModule;

/**
 * @author Joachim
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

    /**
     * @author Joachim
     * <p>
     *  this method constructs a commit from the parameters given.
     * </p>
     * @returns the commit created
     */
    public Commit(String newID, String newDescription, String newAuthor, int newDiffAdded, int newDiffRemoved, String newDate){
        id = newID;
        description = newDescription;
        author = newAuthor;
        diffAdded = newDiffAdded;
        diffRemoved = newDiffRemoved;
        date = newDate;
    }

    /**
     * @author Joachim
     * <p>
     *  gets the git id of the commit.
     * </p>
     * @returns the identifier of the commit in GitHub in string format.
     */
    public String getId() {
        return id;
    }

    /**
     * @author Joachim
     * <p>
     *  gets the description the developer added to the commit
     * </p>
     * @returns the description the developer added to the commit when they made this commit in string form
     */
    public String getDescription() {
        return description;
    }

    /**
     * @author Joachim
     * <p>
     *  gets the name of the author of the commit account
     * </p>
     * @returns the author GitHub account name who made the commit in string form
     */
    public String getAuthor() {
        return author;
    }

    /**
     * @author Joachim
     * <p>
     *  gets the amount of lines added in the changes in this commit.
     * </p>
     * @returns the amounts of lines added with this commit in integer form
     */
    public int getDiffAdded() {
        return diffAdded;
    }

    /**
     * @author Joachim
     * <p>
     *  gets the amount of lines removed in this commit in integer form.
     * </p>
     * @returns the amount of lines removed in integer form
     */
    public int getDiffRemoved() {
        return diffRemoved;
    }

}
