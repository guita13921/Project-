package Parser;

public class InfoExpr implements Statement{
    private String arg;
    private Direction direction;

    public InfoExpr(String command) {
        this.arg = command;
    }

    public InfoExpr(String command, Direction direction) {
        this.arg = command;
        this.direction = direction;
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
