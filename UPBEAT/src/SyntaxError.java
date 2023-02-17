public class SyntaxError extends RuntimeException{
    SyntaxError(String msg){
        System.out.println("SyntaxError");
    }
    public String getMessage(){
        return "SyntaxError";
    }
}
