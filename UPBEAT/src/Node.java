import java.util.Map;

public interface Node {
    void prettyPrint(StringBuilder s);
}
interface Expr extends Node{
    int eval(Map<String,Integer> bindings);
}
