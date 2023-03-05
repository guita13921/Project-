package Parser;

public class SyntaxError extends RuntimeException{
    SyntaxError(String msg){
        System.out.println(msg+"Parser.SyntaxError");
    }
    public String getMessage(){
        return "Parser.SyntaxError";
    }
}
