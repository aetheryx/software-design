package softwaredesign.commands;

import softwaredesign.Application;
import softwaredesign.repository.Repository;
import softwaredesign.util.Framework;

import java.util.Map;
import java.util.Set;
import java.util.function.Function;

/**
 * Provides a template for individual command classes to extend.
 * The Template Method design pattern is applied between the {@link Command#execute(String)} and {@link Command#run(Map)} methods.
 * The {@link Command#execute(String)} does the work of parsing the raw input using the protected argumentParser
 * instance, and then calling the {@link Command#run(Map)} template method implemented by the subclass.
 *
 * @author Zain
 */
public abstract class Command implements Framework.Module {
    /**
     * The argument parser used to parse arguments for this command.
     * Subclasses should configure the arguments they need by calling methods on this instance.
     */
    protected ArgumentParser argumentParser = new ArgumentParser();

    /**
     * A protected utility method for subclasses to get access to the Repository.
     *
     * @return A reference to the currently active repository
     */
    protected Repository getRepository() {
        return Application.getInstance().getRepository();
    }

    /**
     * Prepares the arguments for this command, and then executes the inner command logic
     * (in the {@link #run(Map)} method).
     *
     * @param rawArguments The raw user input for this command, as a string of command-line flag arguments, e.g. <tt>--name=zain</tt>
     */
    public final void execute(String rawArguments) throws UserFacingException {
        Map<String, String> arguments = this.argumentParser.parse(rawArguments);
        this.run(arguments);
    }

    /**
     * Contains the actual runtime logic of the command; the code that is executed when a user
     * runs the command. Subclasses need to override this template method.
     *
     * @param arguments A map of parsed and validated arguments, based on
     *                  what has been configured in this class' {@link Command#argumentParser} field.
     */
    protected abstract void run(Map<String, String> arguments) throws UserFacingException;

    /**
     * The description for this command. Displayed to the user in the help command.
     *
     * @return The description for this command
     */
    public abstract String getDescription();

    /**
     * The usage for this command. Displayed to the user in the help command.
     * Defaults to generating the usage dynamically from the {@link ArgumentParser}, but
     * can be overridden to specify a more detailed usage.
     *
     * @return The usage string, displayed in the help command.
     */
    public String getUsage() {
        Map<String, Set<String>> requiredArguments = this.argumentParser.getRequiredArguments();
        Map<String, Set<String>> optionalArguments = this.argumentParser.getOptionalArguments();
        StringBuilder sb = new StringBuilder();

        Function<Map.Entry<String, Set<String>>, Void> formatArgument = argument -> {
            sb.append("--").append(argument.getKey()).append("=");

            Set<String> argumentValues = argument.getValue();
            if (argumentValues == null) {
                sb.append("(string)");
            } else {
                sb.append(String.join("|", argumentValues));
            }
            return null;
        };

        for (Map.Entry<String, Set<String>> requiredEntry : requiredArguments.entrySet()) {
            sb.append("<");
            formatArgument.apply(requiredEntry);
            sb.append("> ");
        }

        for (Map.Entry<String, Set<String>> optionalEntry : optionalArguments.entrySet()) {
            sb.append("[");
            formatArgument.apply(optionalEntry);
            sb.append("] ");
        }

        return sb.toString();
    }

    /**
     * Examples of arguments for this command. Displayed to the user in the help command.
     *
     * @return Newline-delimited list of examples for this command
     */
    public abstract String getExamples();
}







