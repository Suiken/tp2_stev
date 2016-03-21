import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void invalidCombination(String line) {
        if (line.matches("^\\s[a-z].+")) {
            System.out.println(line);
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
            while ((line = reader.readLine()) != null) {
                //extractArgument(line, arguments);
                invalidCombination(line);
            }

            System.out.println(arguments);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}