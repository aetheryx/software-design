package softwaredesign.CommandModule;

import softwaredesign.Util.Framework;

import java.util.Map;

/**
 * @author Marko
 *
 * This abstract class provides a template for the actual
 * command classes (StatisticCommand, DeleteRepositoryCommand, SwitchBranchCommand)
 *
 */
public abstract class Command implements Framework.Module {
    public ArgumentParser argumentParser = new ArgumentParser();
    public abstract void run(Map<String, String> arguments);

    public abstract String getDescription();
    public abstract String getUsage();
    public abstract String getExamples();
}







