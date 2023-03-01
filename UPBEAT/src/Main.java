


public class Main {
    public static void main(String[] args) {
        String input = " while (deposit) { if (deposit - 100) then collect (deposit / 4) else {}\n }";
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
