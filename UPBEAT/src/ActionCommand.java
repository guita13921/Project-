import java.util.Map;

public class ActionCommand implements Expr{
    private final String name;
    public ActionCommand(String name){
        this.name = name;
        System.out.println("IM "+name);
    }

    @Override
    public void prettyPrint(StringBuilder s) {
        s.append(name);
    }

    @Override
    public int eval(Map<String, Integer> bindings) {
        if(bindings.containsKey(name)){
            return bindings.get(name);
        }
        throw new EvalError("undefined variable: "+name);
    }
}
