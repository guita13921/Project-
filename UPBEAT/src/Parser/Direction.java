package Parser;

import java.util.HashMap;
import java.util.Map;

public class Direction implements Expr {
    private final String dir;

    public Direction(String name){
        this.dir = name;
        System.out.println("IM "+name);
    }

    @Override
    public void prettyPrint(StringBuilder s) {
        s.append(dir);
    }

    @Override
    public int eval(Map<String, Integer> bindings) {
        if(bindings.containsKey(dir)){
            return bindings.get(dir);
        }
        throw new EvalError("undefined variable: "+dir);
    }
}