import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void extractArgument(String argument) {
        String[] tokens = argument.split(",");
        for (String t : tokens)
            System.out.println(t);
    }

    public static void main(String[] args) throws IOException {
        try {
            Process p = Runtime.getRuntime().exec("/home/stev/tp2-app.sh -h");
            p.getErrorStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line = null;
            while ((line = reader.readLine()) != null) {
                extractArgument(line);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}