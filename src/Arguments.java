import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by suiken on 21/03/16.
 */
public class Arguments {
    HashMap<String, ArrayList<String>> arguments = new HashMap<>();

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
                    if (option.equals(" flag")) {
                        s += " true, false";
                    } else if (firstLoop) {
                        s += option;
                        firstLoop = false;
                    } else {
                        s += "," + option;
                    }
                }
                s += "\n";
            }
        }
        return s;
    }
}
