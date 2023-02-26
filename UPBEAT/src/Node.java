import java.util.Map;

public interface Node {
    void prettyPrint(StringBuilder s);
}
interface Expr extends Node{
    int eval(Map<String,Integer> bindings);
}

class IntLit implements Expr{
    private final int val;
    public  IntLit(int val){
        this.val = val;
    }
    public int eval(Map<String,Integer> bindings){
        return val;
    }
    public void prettyPrint(StringBuilder s){
        s.append(val);
    }

}