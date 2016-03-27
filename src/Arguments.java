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

        if (options == null)
            options = new ArrayList<>();

        arguments.put(argument, options);
        options.add(option);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String argument : arguments.keySet()) {
            sb.append(argument + "\n");
            for (String option : arguments.get(argument)) {
                sb.append("\t" + option + "\n");
            }
        }
        return sb.toString();
    }

    public String formatQICT() {
        StringBuilder sb = new StringBuilder();
        for (String argument : arguments.keySet()) {
            if (!argument.equals("-h")) {
                boolean firstLoop = true;
                sb.append(argument + ": ");

                for (String option : arguments.get(argument)) {
                    option = option.replace(" ", "");

                    if (option.equals("flag")) {
                        sb.append("true, false");
                    }
                    else if (firstLoop) {
                        sb.append(option);
                        firstLoop = false;
                    }
                    else {
                        sb.append(", " + option);
                    }
                }
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    public int getArgumentsNumber() {
        return arguments.keySet().size();
    }

    public Set<String> getArgumentsName() {
        return arguments.keySet();
    }
}
