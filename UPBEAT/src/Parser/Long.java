package Parser;

public class Long implements Statement{
    private int val;

    public Long(int val) {
        System.out.println("IM "+val);
        this.val = val;
    }

    public int eval() {
        return val;
    }

    @Override
    public long evaluate() throws SyntaxError {
        return val;
    }

    @Override
    public StringBuilder addCommand(StringBuilder s) {
        s.append("LongIntlit ");
        return s;
    }


}
