import java.io.IOException;
import java.io.InputStream;

public class Main {
    public static void main(String[] args) throws IOException {

        String[] cmd = { "bash", "-c", "/home/stev/tp2-app.sh -h" };
        Process p = Runtime.getRuntime().exec(cmd);
        InputStream stderr = p.getErrorStream();
        System.out.println(stderr);
    }
}