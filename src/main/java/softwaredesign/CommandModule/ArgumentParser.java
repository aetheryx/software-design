package softwaredesign.CommandModule;

import java.util.Map;

/**
 * @author Marko
 *
 * This class is going to parse the input arguments, in a Command object.
 *
 * <p>
 *     It is going to parse the input string, separate the key string(s) and
 *     compare their values to the previously set valid key-value pair, in
 *     which it is going to parse the parsed key's value (String[]) and
 *     search if the argument key's value is found in it or not.
 * </p>
 */
public class ArgumentParser {
    private Map<String, String[]> arguments;

    ArgumentParser addArgument(String name, String[] values){
        return this;
    }

    Map<String, String[]> parse (String input){
        return null;
    }

}
