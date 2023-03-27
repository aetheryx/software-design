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
    private String name;
    public ArgumentParser argumentParser = new ArgumentParser();
    public abstract void run(Map<String, String> arguments);

    @Override
    public String getName() {
        return name;
    }
}







