import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Set;

/**
 * Created by suiken on 21/03/16.
 */
public class Arguments {
    LinkedHashMap<String, ArrayList<String>> arguments = new LinkedHashMap<>();

    public void addOption(String argument, String option) {
        ArrayList<String> options = arguments.get(argument);
        if (options == null) {
            options = new ArrayList<>();
            options.add(option);
            arguments.put(argument, options);
        } else
            options.add(option);
    }

    public String toString() {
        String s = new String();
        for (String argument : arguments.keySet()) {
            s += argument + "\n";
            for (String option : arguments.get(argument)) {
                s += "\t" + option + "\n";
            }
        }
        return s;
    }

    public String formatQICT() {
        String s = new String();
        for (String argument : arguments.keySet()) {
            if (!argument.equals("-h")) {
                boolean firstLoop = true;
                s += argument + ": ";
                for (String option : arguments.get(argument)) {
                    option = option.replace(" ", "");
                    if (option.equals("flag")) {
                        s += "true, false";
                    } else if (firstLoop) {

                        s += option;
                        firstLoop = false;
                    } else {
                        s += ", " + option;
                    }
                }
                s += "\n";
            }
        }
        //System.out.println(s);
        return s;
    }

    public int getArgumentsNumber() {
        return arguments.keySet().size();
    }

    public Set<String> getArgumentsName() {
        return arguments.keySet();
    }
}
