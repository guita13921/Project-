
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String input = "2 + 3 * 5";

        Lexer lexer = new Lexer(input);
        List<Token> tokens = lexer.tokenize();

        for (Token token : tokens) {
            System.out.println(token.getType() + " " + token.getText());
        }
    }
    /*
            Reader read = new Reader("Sample construction plan.txt");
            read.readFile();
            Configuration_Reader conRead = new Configuration_Reader("");
    */
}
