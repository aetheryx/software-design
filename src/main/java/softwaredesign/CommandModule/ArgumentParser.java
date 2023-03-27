package softwaredesign.CommandModule;

import java.util.HashMap;
import java.util.Map;

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
    private Map<String, String[]> arguments = new HashMap<>();

    public ArgumentParser addArgument(String name, String[] values){

        if(!arguments.containsKey(name)){
            arguments.put(name, values);
        }
        else {
            // TODO: It should overwrite or return an error?
        }

        return this;
    }

    public Map<String, String> parse (String input){

        Map<String, String> parsedArguments = new HashMap<>();

        String[] inputArgs = input.trim().split(" ");

        for(String arg : inputArgs){
            String temp = arg.substring(2);

            for(int i=0; i<temp.length(); i++){
                if(temp.charAt(i) == '='){
                    parsedArguments.put(temp.substring(0,i), temp.substring(i+1));
                }
            }
        }

        return parsedArguments;
    }

}
