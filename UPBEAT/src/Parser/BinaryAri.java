package Parser;

public class BinaryAri implements Statement{
    private final Statement left, right;
    private final String op;

    public BinaryAri(Statement left, String op,Statement right ){
        this.left = left;
        this.op = op;
        this.right = right;
    }
    @Override
    public long evaluate() {
        long lv = left.evaluate();
        long rv = right.evaluate();

        if(op.equals("+")){
            return lv + rv;
        }
        if(op.equals("-")){
            return lv - rv;
        }
        if(op.equals("*")){
            return lv * rv;
        }
        if(op.equals("/")){
            return lv / rv;
        }
        if(op.equals("%")){
            return lv % rv;
        }
        if(op.equals("^")){
            return (long) Math.pow(lv,rv);
        }
        throw new EvalError("unknow op: "+op);
    }


    @Override
    public StringBuilder addCommand(StringBuilder s) {
        s.append("Expression ");
        return s;
    }
}