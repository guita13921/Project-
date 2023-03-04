


public class Main {
    public static void main(String[] args) {
        String input = "if (opponentLoc / 10 - 1)" +
                "then " +
                "if (opponentLoc % 10 - 5) then move downleft "+
                "else if (opponentLoc % 10 - 4) then move down " +
                "else if (opponentLoc % 10 - 3) then move downright " +
                "else if (opponentLoc % 10 - 2) then move right " +
                "else if (opponentLoc % 10 - 1) then move upright " +
                "else move up " +
                "else if (opponentLoc) " +
                "then " +
                "if (opponentLoc % 10 - 5) then { " +
                "cost = 10 ^ (nearby upleft % 100 + 1) " +
                "if (budget - cost) then shoot upleft cost else {} " +
                "} " +
                "else if (opponentLoc % 10 - 4) then { " +
                "cost = 10 ^ (nearby downleft % 100 + 1) " +
                "if (budget - cost) then shoot downleft cost else {} " +
                "} " +
                "else if (opponentLoc % 10 - 3) then { " +
                "cost = 10 ^ (nearby down % 100 + 1) " +
                "if (budget - cost) then shoot down cost else {} " +
                "} " +
                "else if (opponentLoc % 10 - 2) then { " +
                "cost = 10 ^ (nearby downright % 100 + 1) " +
                "if (budget - cost) then shoot downright cost else {} " +
                "} " +
                "else if (opponentLoc % 10 - 1) then { " +
                "cost = 10 ^ (nearby upright % 100 + 1) " +
                "if (budget - cost) then shoot upright cost else {} " +
                "} " +
                "else { " +
                "cost = 10 ^ (nearby up % 100 + 1) " +
                "if (budget - cost) then shoot up cost else {} " +
                "} " +
                "else {" +
                "dir = random % 6 " +
                "if (dir - 4) then move upleft " +
                "else if (dir - 3) then move downleft " +
                "else if (dir - 2) then move down " +
                "else if (dir - 1) then move downright " +
                "else if (dir) then move upright " +
                "else move up " +
                "m = m + 1 " +"} ";
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
