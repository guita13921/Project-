package Parser;

public interface Statement {
    long evaluate() throws SyntaxError;
    StringBuilder addCommand(StringBuilder s);
}
