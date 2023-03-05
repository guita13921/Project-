package Parser;

import java.util.Map;

public class Identifier implements Expr {
    protected String val;
    public VariableStorage variable;

    public Identifier(String val,VariableStorage variable) {
        this.val = val;
        this.variable = variable;
    }

    @Override
    public void prettyPrint(StringBuilder s) {
        s.append(val);
    }

    @Override
    public int eval(Map<String, Integer> bindings) {
        if(bindings.containsKey(val)){
            return bindings.get(val);
        }
        throw new EvalError("undefined variable: "+val);
    }
}
