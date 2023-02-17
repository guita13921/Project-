public class Main {
    public static void main(String[] args) {
        Tokenizer token = new Tokenizer("t = t + 1");
        System.out.println(token.show_token());
        System.out.println(token.consume());
        System.out.println(token.consume());
        System.out.println(token.consume());
        System.out.println(token.consume());
        System.out.println(token.consume());
    }
}
