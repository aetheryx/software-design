package softwaredesign.CommandModule;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 *
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
    private Map<String, String[]> arguments;

    ArgumentParser addArgument(String name, String[] values){

        if(!arguments.containsKey(name)){
            arguments.put(name, values);
        }
        else {
            // TODO: It should return an error or overwrite?
        }

        return this;
    }

    Map<String, String[]> parse (String input){
        ArrayList<String> keys = new ArrayList<>();
        ArrayList<String> values = new ArrayList<>();

        return null;
    }

}
