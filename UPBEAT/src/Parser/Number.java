package Parser;

import java.util.Map;


public class Number implements Expr {
    private final String number;
    public Number(String name){
        System.out.println("IM "+name);
        this.number = name;
    }

    public int eval(Map<String,Integer> bindings){
        if(bindings.containsKey(number)){
            return bindings.get(number);
        }
        throw new EvalError("undefined variable: "+number);
    }
    public void prettyPrint(StringBuilder s){
        s.append(number);
    }
}
