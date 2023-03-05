import java.util.Map;

public class BinaryAri implements Expr{
    private final Expr left;
    private Expr mid;
    private final Expr right;
    private String op;

    public BinaryAri(Expr left,Expr mid,Expr right){
        this.left = left;
        this.mid = mid;
        this.right = right;
    }

    public BinaryAri(Expr left,String op, Expr right){
        this.left = left;
        this.op = op;
        this.right = right;
    }



    public void prettyPrint(StringBuilder s){
        s.append("(");
        left.prettyPrint(s);
        s.append(op);
        right.prettyPrint(s);
        s.append(")");
    }

    @Override
    public int eval(Map<String, Integer> bindings) {
        return 0;
    }
}
