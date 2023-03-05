package Parser;

public class IfStatement implements Statement{
    private Statement expression;
    private Statement thanStatement;
    private Statement elseStatement;

    public IfStatement(Statement expression, Statement thanStatement, Statement elseStatement) {
        this.thanStatement = thanStatement;
        this.expression = expression;
        this.elseStatement = elseStatement;
    }


    @Override
    public long evaluate() {
        return 0;
    }

    @Override
    public StringBuilder addCommand(StringBuilder s) {
        return null;
    }
}
