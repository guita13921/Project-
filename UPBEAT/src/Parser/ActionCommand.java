package Parser;

public class ActionCommand implements Statement{
    private String action;
    private Direction direction;
    private Statement expression;

    public ActionCommand(String action) {
        this.action = action;
    }

    public ActionCommand(String action, Direction direction) {
        this.action = action;
        this.direction = direction;
    }

    public ActionCommand(String action, Statement expression) {
        this.action = action;
        this.expression = expression;
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
