package Parser;

public class WhileStatement implements Statement{
    private  Statement expression;
    private  Statement statement;

    public WhileStatement(Statement expression, Statement statement) {
        this.expression = expression;
        this.statement = statement;
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
