package Parser;

import java.util.Map;

public class Identifier implements Statement {
    protected String val;
    public VariableStorage variable;

    public Identifier(String val,VariableStorage variable) {
        System.out.println("IM "+val);
        this.val = val;
        this.variable = variable;
    }

    public String value() throws SyntaxError {
        return val;
    }

    @Override
    public long evaluate() throws SyntaxError {
        return 0;
    }

    @Override
    public StringBuilder addCommand(StringBuilder s) {
        return null;
    }
}
