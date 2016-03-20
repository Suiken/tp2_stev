import java.io.IOException;
import java.io.InputStream;

public class Main {
    public static void main(String[] args) throws IOException {

        try {
            ProcessBuilder pb = new ProcessBuilder("myscript.sh");
            Process p = pb.start();     // Start the process.
            p.waitFor();                // Wait for the process to finish.
            InputStream stderr = p.getErrorStream();
            System.out.println(stderr);
            System.out.println("Script executed successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}