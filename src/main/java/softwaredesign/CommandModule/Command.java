package softwaredesign.CommandModule;

import java.util.Map;

/**
 * @author Marko
 *
 * This abstract class provides a template for the actual
 * command classes (StatisticCommand, DeleteRepositoryCommand, SwitchBranchCommand)
 *
 */
public abstract class Command {

    private String name;
    private ArgumentParser argumentParser;
    abstract void run(Map<String, String> arguments);
}







