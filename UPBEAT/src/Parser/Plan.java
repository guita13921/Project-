package Parser;

import java.util.Iterator;
import java.util.LinkedList;

public class Plan {
    private final LinkedList<Statement> statement_list = new LinkedList<>();
    private Iterator<Statement> iterator = statement_list.iterator();

    protected void addState(Statement statement) {
        statement_list.add(statement);
    }

    public Statement nextState() {
        return hasNext() ? iterator.next() : null;
    }

    public boolean hasNext() {
        return iterator.hasNext();
    }

    public void setIterator() {
        iterator = statement_list.iterator();
    }

    public String evaluate() throws SyntaxError {
        StringBuilder commandList = new StringBuilder();
        setIterator();
        while (hasNext()) {
            Statement state = nextState();
            state.evaluate();
            commandList = state.addCommand(commandList);
        }
        if(commandList == null){
            return null;
        }else{
            return commandList.toString();
        }
    }
}
