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
            if(argumentAndValue.length > 1)
                subConstraints.put(argumentAndValue[0], argumentAndValue[1]);
            else
                subConstraints.put(argumentAndValue[0], "");
        }
        constraints.add(subConstraints);
    }

    public ArrayList<HashMap<String, Object>> getConstraints(){
        return constraints;
    }

    public String toString(){
        String s = new String();
        for(HashMap<String, Object> subConstraints : constraints){
            for(String argument : subConstraints.keySet()) {
                s += "arg : " + argument + ", value : ";
                if (subConstraints.get(argument).equals(""))
                    s += "no value";
                else
                    s += subConstraints.get(argument);
                s += "\n";
            }
            s += "\n";
        }
        return s;
    }
}
