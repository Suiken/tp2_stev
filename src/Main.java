import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void invalidCombination(String line, Constraints constraints) {
        if (line.matches("^\\s[a-z].+")) {
            constraints.addConstraint(line);
        }
    }

    public static void extractArgument(String line, Arguments arguments) {
        if (line.length() > 3) {
            String argument = line.substring(1, 3);
            String option = line.substring(5, line.length());

            if (argument.startsWith("-")) {
                String[] tokens = option.split(",");
                for (String t : tokens)
                    arguments.addOption(argument, t);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        try {
            Process p = Runtime.getRuntime().exec("/home/stev/tp2-app.sh -h");
            p.getErrorStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line = null;
            Arguments arguments = new Arguments();
            Constraints constraints = new Constraints();
            while ((line = reader.readLine()) != null) {
                extractArgument(line, arguments);
                invalidCombination(line, constraints);
            }

            System.out.println(arguments);
            System.out.println(constraints);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}