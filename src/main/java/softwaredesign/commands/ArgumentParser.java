package softwaredesign.commands;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * This class is going to parse the input arguments, in a Command object.
 *
 * <p>
 *     It is going to parse the input string, separate the key string(s) and
 *     compare their values to the previously set valid key-value pair, in
 *     which it is going to parse the parsed key's value (String[]) and
 *     search if the argument key's value is found in it or not.
 * </p>
 *
 * @author Marko
 */
public class ArgumentParser {
    private final Map<String, Set<String>> requiredArguments = new HashMap<>();
    private final Map<String, Set<String>> optionalArguments = new HashMap<>();

    public ArgumentParser addRequiredArgument(String name, Set<String> values) {
        this.requiredArguments.put(name, values);
        return this;
    }

    public ArgumentParser addOptionalArgument(String name, Set<String> values) {
        this.optionalArguments.put(name, values);
        return this;
    }

    private void assertValid(Map.Entry<String, Set<String>> entry, String value) throws UserFacingException {
        String argumentName = entry.getKey();
        Set<String> argumentValues = entry.getValue();
        if (argumentValues != null && !argumentValues.contains(value)) {
            throw new UserFacingException("Argument \"")
                    .append(argumentName)
                    .append("\" must be one of the following values: ")
                    .append(String.join(", ", argumentValues));
        }
    }

    public Map<String, String> parse(String input) throws UserFacingException {
        Map<String, String> rawArguments = this.parseRaw(input);
        Map<String, String> outputArguments = new HashMap<>();

        for (Map.Entry<String, Set<String >> entry : requiredArguments.entrySet()) {
            String argumentName = entry.getKey();
            if (!rawArguments.containsKey(argumentName)) {
                throw new UserFacingException("The required \"")
                        .append(argumentName)
                        .append("\" argument is missing.");
            }

            this.assertValid(entry, rawArguments.get(argumentName));
            outputArguments.put(argumentName, rawArguments.get(argumentName));
        }

        for (Map.Entry<String, Set<String >> entry : optionalArguments.entrySet()) {
            String argumentName = entry.getKey();
            String argumentValue = rawArguments.get(argumentName);
            if (argumentValue != null) {
                this.assertValid(entry, argumentValue);
                outputArguments.put(argumentName, rawArguments.get(argumentName));
            }
        }

        return outputArguments;
    }

    private Map<String, String> parseRaw (String input){
        Map<String, String> parsedArguments = new HashMap<>();

        String[] inputArgs = input.trim().split(" ");

        for(String arg : inputArgs){
            if (arg.length() < 2) continue;
            String temp = arg.substring(2);

            for(int i=0; i<temp.length(); i++){
                if(temp.charAt(i) == '='){
                    parsedArguments.put(temp.substring(0,i), temp.substring(i+1));
                }
            }
        }

        return parsedArguments;
    }

    public Map<String, Set<String>> getOptionalArguments() {
        return optionalArguments;
    }

    public Map<String, Set<String>> getRequiredArguments() {
        return requiredArguments;
    }
}
