package softwaredesign;
import softwaredesign.commands.*;

import softwaredesign.repository.Repository;
import softwaredesign.ui.TerminalIO;

import java.io.IOException;

/**
 * @author Joachim, Zain
 * This class is the main class that ties the repository and the commands together.
 * <p>
 *     Represents the core framework of the application. It ties together the <a href=#@link>{@link Repository}</a>
 *     class (representing Git data)
 *     and the <a href = #@Link>{@link Command}</a> classes. It handles the initialisation of the <a href=#@link>{@link Repository}</a>
 *     class (by requesting input from the
 *     user) (see also for more details: description of the Repository). It also acts as the main command handler
 *     thread, requesting command input from the user, parsing it, selecting the right command class to execute, and
 *     preparing the arguments for this command class based on its argument parser (see Command class).
 * </p>
 * <h2>Interface</h2>
 * <p>
 *      this class has no interface aside from the run() method, which activates an infinite loop of orchestration
 * </p>
 * <h2>Dependencies: </h2>
 * <p>
 *     This class depends on 3 classes directly, which itself then depend on everything else.
 *     <li><a href=#@link>{@link Repository}</a></li>
 *     <li><a href=#@link>{@link TerminalIO}</a></li>
 *     <li><a href=#@link>{@link Command}</a></li>
 * </p>
 * */
public class Application {
    private Application() {}
    private static final Application instance = new Application();
    public static Application getInstance() {
        return instance;
    }

    private final CommandFramework commandFramework = new CommandFramework();
    private Repository repository;

    public Repository getRepository() {
        return repository;
    }

    /**
     * The run method is our main function, it's responsible for deciding what to do.
     * <p>
     *     The run method calls <a href=#@link>{@link Repository#Repository(String)}</a> when the user has not yet
     *     cloned a repository, and TODO when the user has cloned a repository.
     * </p>
     * @author Joachim, Zain
     */
    public void run(){
        this.commandFramework
                .register(new StatisticCommand())
                .register(new DeleteRepositoryCommand())
                .register(new SwitchBranchCommand());

        initialiseRepository();
        this.commandFramework.startCommandLoop();
    }

    /**
     * <p> This function keeps prompting the user until he gives a valid github url, and clones it by calling
     * <a href=#@link>{@link Repository#Repository(String)}</a> into <a href=#@link>{@link Repository#clone()}</a></p>
     * after a repository has been successfully created, it returns.
     *
     * @author Joachim
     * */
    public void initialiseRepository(){
        repository = null;
        while (repository == null) {
            try {
                repository = new Repository(TerminalIO.prompt("Please enter the github URL of the repository you would like to investigate: "));
            } catch (IOException | InterruptedException e) {
                TerminalIO.write("git repository unavailable: " + e.getMessage() + "\n");
            }
        }
    }
}
