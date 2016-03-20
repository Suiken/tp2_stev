import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) {
        try {
            ProcessBuilder pb = new ProcessBuilder("tp2-app.sh", "h");
            Process p = pb.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line = null;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
