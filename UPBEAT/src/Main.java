
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String input = "m = 0";

        Tokenizer lexer = new Tokenizer(input);
        Parser parser = new Parser(lexer);
        Expr parse_a = parser.parse();
        StringBuilder sb = new StringBuilder();
        //parse_a.prettyPrint(sb);
    }
    /*
            Reader read = new Reader("Sample construction plan.txt");
            read.readFile();
            Configuration_Reader conRead = new Configuration_Reader("");
    */
}
