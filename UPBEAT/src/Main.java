public class Main {
    public static void main(String[] args) {
        /*
        Tokenizer token = new Tokenizer("t = t + 1");
        System.out.println(token.show_token());
        System.out.println(token.consume());
        System.out.println(token.consume());
        System.out.println(token.consume());
        System.out.println(token.consume());
        System.out.println(token.consume());

        Tokenizer token1 = new Tokenizer("while (deposit)");
        System.out.println(token1.consume());
        System.out.println(token1.consume());
        System.out.println(token1.consume());

        Tokenizer token2 = new Tokenizer("if (deposit - 100)");
        System.out.println(token2.consume());
        System.out.println(token2.consume());
        System.out.println(token2.consume());
        System.out.println(token2.consume());
        System.out.println(token2.consume());
        System.out.println(token2.consume());
        */
        Reader read = new Reader("Sample construction plan.txt");
        read.readFile();

    }
}
