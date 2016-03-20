import java.io.IOException;
import java.io.InputStream;

public class Main {
    public static void main(String[] args) throws IOException {

        Process process = Runtime.getRuntime().exec("/home/stev/tp2-app.sh -h");
        InputStream stderr = process.getErrorStream();
        System.out.println(stderr.toString());
    }
}