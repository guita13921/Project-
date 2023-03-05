import java.util.LinkedList;

public class BlockStatement implements Statement{
    private LinkedList<Statement> statelist;
    @Override
    public long eval() {
        return 0;
    }
    @Override
    public StringBuilder addCommand(StringBuilder s) {
        return null;
    }
}
