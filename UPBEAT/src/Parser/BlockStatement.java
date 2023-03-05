package Parser;

import java.util.LinkedList;

public class BlockStatement implements Statement{
    private LinkedList<Statement> Blocklist = new LinkedList<>();

    @Override
    public long evaluate() {
        return 0;
    }

    @Override
    public StringBuilder addCommand(StringBuilder s) {
        return null;
    }

    public BlockStatement(LinkedList<Statement> list) {
        this.Blocklist = list;
    }

    public LinkedList<Statement> getList() {
        return Blocklist;
    }
}
