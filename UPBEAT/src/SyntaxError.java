public class SyntaxError extends RuntimeException{
    SyntaxError(String msg){
        System.out.println(msg+"SyntaxError");
    }
    public String getMessage(){
        return "SyntaxError";
    }
}
