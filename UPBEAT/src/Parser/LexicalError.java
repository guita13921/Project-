package Parser;

public class LexicalError extends RuntimeException{
    public String getMessage(){
        return "Parser.Parser.LexicalError";
    }
}
