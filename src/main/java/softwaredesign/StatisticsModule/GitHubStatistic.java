package softwaredesign.StatisticsModule;

import java.util.Map;

/**
 * An abstract class that implements the Statistic interface.
 *
 * This class adds the protected callAPI() method, which can be called by statistics classes extending this abstract class,
 * to make it easier for them to make requests to the GitHub API.
 *
 * @author Zain
 */
public abstract class GitHubStatistic extends Statistic {
    public GitHubStatistic(String name) {
        super(name);
    }

    /**
     * This method
     * @param path
     */
    protected void callAPI(String path) {

    }
}
