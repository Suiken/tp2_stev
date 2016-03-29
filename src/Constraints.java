import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by suiken on 21/03/16.
 */
public class Constraints {
    ArrayList<HashMap<String, Object>> constraints = new ArrayList<>();

    public void addConstraint(String constraint){
        constraint = constraint.replaceAll(" ", "");
        HashMap<String, Object> subConstraints = new HashMap<>();

        String subConstraintsTab[] = constraint.split("&");
        for(int i = 0; i < subConstraintsTab.length; i++){
            String argumentAndValue[] = subConstraintsTab[i].split("=");
            subConstraints.put(argumentAndValue[0], argumentAndValue.length > 1 ? argumentAndValue[1] : "");
        }
        constraints.add(subConstraints);
    }

    public ArrayList<HashMap<String, Object>> getConstraints(){
        return constraints;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(HashMap<String, Object> subConstraints : constraints){
            for(String argument : subConstraints.keySet()) {
                sb.append("arg : " + argument + ", value : ");
                sb.append(subConstraints.get(argument).equals("") ? "no value\n" : "\n");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
