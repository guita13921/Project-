package Parser;

public class AssignStatement implements Statement {
    private Identifier identifier;
    private String op;
    private Statement expression;
    public VariableStorage variableStorage;

    public AssignStatement(Identifier identifier, String op, Statement expression,VariableStorage variableStorage) {
        this.identifier = identifier;
        this.op = op;
        this.expression = expression;
        this.variableStorage = variableStorage;
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
