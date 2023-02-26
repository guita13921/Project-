
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String input = "t = t * 1";

        Tokenizer lexer = new Tokenizer(input);
        System.out.println(lexer.consume());
        System.out.println(lexer.consume());
        System.out.println(lexer.consume());
        System.out.println(lexer.consume());
    }
    /*
            Reader read = new Reader("Sample construction plan.txt");
            read.readFile();
            Configuration_Reader conRead = new Configuration_Reader("");
    */
}
