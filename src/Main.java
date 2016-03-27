import java.io.*;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) throws IOException {
        try {
            BufferedReader reader = getBufferFromExecution("/home/stev/tp2-app.sh -h");
            Arguments arguments = new Arguments();
            Constraints constraints = new Constraints();
            getArgumentsAndConstraints(reader, arguments, constraints);
            generateDataFile(arguments);
            reader = getBufferFromExecution("qict dataArguments.txt");
            executeAppWithCombinedTests(reader, arguments, constraints);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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

    public static void generateDataFile(Arguments arguments) throws FileNotFoundException, UnsupportedEncodingException {
        String dataArguments = arguments.formatQICT();
        PrintWriter writer = new PrintWriter("dataArguments.txt", "UTF-8");
        writer.print(dataArguments);
        writer.close();
    }

    public static BufferedReader getBufferFromExecution(String app) throws Exception {
        Process p = Runtime.getRuntime().exec(app);
        p.getErrorStream();
        return new BufferedReader(new InputStreamReader(p.getInputStream()));
    }

    public static void getArgumentsAndConstraints(BufferedReader reader, Arguments arguments, Constraints constraints) throws Exception {
        String line;
        while ((line = reader.readLine()) != null) {
            extractArgument(line, arguments);
            invalidCombination(line, constraints);
        }
    }

    public static void printBuffer(BufferedReader reader) throws Exception {
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
    }

    public static boolean isTestValid(HashMap<String, Object> test, HashMap<String, Object> constraint) {
        int numberOfConstraintArguments = constraint.size();
        int countNumberOfArgumentsNotValid = 0;
        for (String constraintArgument : constraint.keySet()) {
            for (String testArgument : test.keySet()) {
                if (constraintArgument.equals(testArgument) && constraint.get(constraintArgument).equals(test.get(testArgument)))
                    countNumberOfArgumentsNotValid++;
            }
        }
        return (countNumberOfArgumentsNotValid == numberOfConstraintArguments) ? false : true;
    }

    public static String generateExecutionStringWithConstraints(HashMap<String, Object> tests, Constraints constraints) {
        boolean isValid = true;
        principalLoop:
        for (HashMap<String, Object> constraint : constraints.getConstraints()) {
            if (!isTestValid(tests, constraint)) {
                isValid = false;
                break principalLoop;
            }
        }

        if (isValid) {
            StringBuilder execution = new StringBuilder("/home/stev/tp2-app.sh");
            for (String argumentName : tests.keySet()) {
                execution.append(" -" + argumentName);
                if (!tests.get(argumentName).equals(""))
                    execution.append(" " + tests.get(argumentName));
            }
            return execution.toString();
        }
        return null;
    }

    public static void executeAppWithCombinedTests(BufferedReader reader, Arguments arguments, Constraints constraints) throws Exception {
        String line;
        while ((line = reader.readLine()) != null) {
            HashMap<String, Object> tests = new HashMap<>();
            String execution;
            String combinedArgs[] = line.split("\\t");

            if (combinedArgs.length == arguments.getArgumentsNumber()) {
                int i = 1;
                for (String argumentName : arguments.getArgumentsName()) {
                    if (!argumentName.equals("-h")) {
                        argumentName = argumentName.replace("-", "");

                        if (combinedArgs[i].equals("true"))
                            tests.put(argumentName, "");
                        else if (!combinedArgs[i].equals("false"))
                            tests.put(argumentName, combinedArgs[i]);

                        i++;
                    }
                }
                execution = generateExecutionStringWithConstraints(tests, constraints);
                if (execution != null) {
                    System.out.println(execution);
                    printBuffer(getBufferFromExecution(execution));
                }
            }
        }
    }
}