public class Token {
    private Lexer.TokenType type;
    private String value;

    public Token(Lexer.TokenType type, String value) {
        this.type = type;
        this.value = value;
    }

    public Lexer.TokenType getType() {
        return type;
    }

    public String getText() {
        return value;
    }

    @Override
    public String toString() {
        return "Token(" + type + ", " + value + ")";
    }
}
