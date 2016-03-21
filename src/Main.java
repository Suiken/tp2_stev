import java.io.*;

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

    public static void generateDataFile(Arguments arguments) throws FileNotFoundException, UnsupportedEncodingException {
        String dataArguments = arguments.formatQICT();
        PrintWriter writer = new PrintWriter("dataArguments.txt", "UTF-8");
        writer.println(dataArguments);
        writer.close();
    }

    public static BufferedReader getBufferFromExecution(String app) throws Exception {
        Process p = Runtime.getRuntime().exec(app);
        p.getErrorStream();
        return new BufferedReader(new InputStreamReader(p.getInputStream()));
    }

    public static void generateQICTFileFromBuffer(BufferedReader reader) throws Exception{
        String line = null;
        Arguments arguments = new Arguments();
        Constraints constraints = new Constraints();
        while ((line = reader.readLine()) != null) {
            extractArgument(line, arguments);
            invalidCombination(line, constraints);
        }
        generateDataFile(arguments);
        System.out.println(arguments.formatQICT());
        System.out.println(constraints);
    }

    public static void main(String[] args) throws IOException {
        try {
            BufferedReader reader = getBufferFromExecution("/home/stev/tp2-app.sh -h");
            generateQICTFileFromBuffer(reader);
            reader = getBufferFromExecution("qict /home/kevin.suy1/tp2_stev/src/dataArguments.txt");
            String line = new String();
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}