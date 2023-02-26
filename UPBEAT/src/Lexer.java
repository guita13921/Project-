import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lexer {
    private ArrayList<Token> tokens;
    private String input;
    private int currentPos;

    private static final Pattern WHITESPACE = Pattern.compile("\\s+");
    private static final Pattern IDENTIFIER = Pattern.compile("[a-zA-Z][a-zA-Z0-9]*");
    private static final Pattern NUMBER = Pattern.compile("\\d+");
    private static final Pattern RESERVED = Pattern.compile("collect|done|down|downleft|downright|else|if|invest|move|nearby|opponent|relocate|shoot|then|up|upleft|upright|while");
    private static final Pattern ASSIGN = Pattern.compile("=");
    private static final Pattern OP = Pattern.compile("[+\\-*/%]");
    private static final Pattern COMP_OP = Pattern.compile("==|!=|<=|>=|<|>");
    private static final Pattern LPAREN = Pattern.compile("\\(");
    private static final Pattern RPAREN = Pattern.compile("\\)");
    private static final Pattern LBRACE = Pattern.compile("\\{");
    private static final Pattern RBRACE = Pattern.compile("\\}");
    private static final Pattern SEMICOLON = Pattern.compile(";");
    private static final Pattern COMMA = Pattern.compile(",");
    private static final Pattern DIRECTION = Pattern.compile("up|down|upleft|upright|downleft|downright");
    private static final Pattern KEYWORD_IF = Pattern.compile("if");
    private static final Pattern KEYWORD_THEN = Pattern.compile("then");
    private static final Pattern KEYWORD_ELSE = Pattern.compile("else");
    private static final Pattern KEYWORD_WHILE = Pattern.compile("while");
    private static final String OPERATOR_CHARS = String.valueOf(Pattern.compile("\\+-*/%()^"));

    private final Map<Pattern, TokenType> tokenPatterns = new LinkedHashMap<Pattern, TokenType>() {{
        put(Pattern.compile("[a-zA-Z][a-zA-Z0-9]*"), TokenType.IDENTIFIER);
        put(Pattern.compile("\\d+"), TokenType.NUMBER);
        put(Pattern.compile("collect|done|down|downleft|downright|else|if|invest|move|nearby|opponent|relocate|shoot|then|up|upleft|upright|while"), TokenType.RESERVED);
        put(Pattern.compile("="), TokenType.ASSIGN);
        put(Pattern.compile("[+\\-*/%]"), TokenType.OP);
        put(Pattern.compile("==|!=|<=|>=|<|>"), TokenType.COMP_OP);
        put(Pattern.compile("\\("), TokenType.LPAREN);
        put(Pattern.compile("\\)"), TokenType.RPAREN);
        put(Pattern.compile("\\{"), TokenType.LBRACE);
        put(Pattern.compile("\\}"), TokenType.RBRACE);
        put(Pattern.compile(";"), TokenType.SEMICOLON);
        put(Pattern.compile(","), TokenType.COMMA);
        put(Pattern.compile("up|down|upleft|upright|downleft|downright"), TokenType.DIRECTION);
        put(Pattern.compile("if"), TokenType.KEYWORD_IF);
        put(Pattern.compile("then"), TokenType.KEYWORD_THEN);
        put(Pattern.compile("else"), TokenType.KEYWORD_ELSE);
        put(Pattern.compile("while"), TokenType.KEYWORD_WHILE);
    }};

    public Token nextToken() {
        skipWhitespace();
        if (currentPos >= input.length()) {
            return new Token(TokenType.EOF, "");
        }
        char currentChar = input.charAt(currentPos);
        for (int i = 0; i < tokenPatterns.size(); i++) {
            Map.Entry<Pattern, TokenType> entry = (Map.Entry<Pattern, TokenType>) tokenPatterns.entrySet().toArray()[i];
            Pattern pattern = entry.getKey();
            TokenType tokenType = entry.getValue();
            Matcher matcher = pattern.matcher(input);
            if (matcher.find()) {
                String text = matcher.group().trim();
                input = matcher.replaceFirst("");
                if (tokenType == TokenType.KEYWORD_WHILE) {
                    // Ignore whitespace and comments
                    i--;
                } else if (tokenType == TokenType.NUMBER) {
                    return new Token(tokenType, text);
                } else if (tokenType == TokenType.IDENTIFIER) {
                    return new Token(tokenType, text);
                } else if (tokenType == TokenType.STRING) {
                    return new Token(tokenType, text.substring(1, text.length() - 1));
                } else if (tokenType == TokenType.OP) {
                    return new Token(tokenType, text);
                } else {
                    //throw new LexerException("Unknown token type: " + tokenType);
                }
            }
        }
        throw new RuntimeException("Invalid character: " + currentChar);
    }


    public enum TokenType {
        // Single-character tokens
        LPAREN, RPAREN, LBRACE, RBRACE,OP,ASSIGN,
        COMMA, DOT, MINUS, PLUS, SEMICOLON, SLASH, STAR, MODULO
        ,

        // One or two character tokens
        BANG, BANG_EQUAL,
        EQUAL, EQUAL_EQUAL,
        GREATER, GREATER_EQUAL,
        LESS, LESS_EQUAL,COMP_OP,

        // Literals
        IDENTIFIER, STRING, NUMBER,

        // Keywords
        AND, CLASS, ELSE, FALSE, FUN, FOR, KEYWORD_IF, NIL, OR,KEYWORD_ELSE,DIRECTION,
        PRINT, RETURN, SUPER, THIS, TRUE, VAR, WHILE,RESERVED,KEYWORD_THEN,KEYWORD_WHILE,

        // End-of-file token
        EOF
    }


    public Lexer(String input) {
        this.input = input;
        this.tokens = new ArrayList<>();
        this.currentPos = 0;
    }



    public ArrayList<Token> tokenize() {
        while (currentPos < input.length()) {
            char currentChar = input.charAt(currentPos);
            Matcher m;

            if (currentChar == '{') {
                tokens.add(new Token(TokenType.LBRACE, "{"));
                currentPos++;
            } else if (currentChar == '}') {
                tokens.add(new Token(TokenType.RBRACE, "}"));
                currentPos++;
            } else if (currentChar == '(') {
                tokens.add(new Token(TokenType.LPAREN, "("));
                currentPos++;
            } else if (currentChar == ')') {
                tokens.add(new Token(TokenType.RPAREN, ")"));
                currentPos++;
            } else if (currentChar == ';') {
                tokens.add(new Token(TokenType.SEMICOLON, ";"));
                currentPos++;
            } else if (currentChar == ',') {
                tokens.add(new Token(TokenType.COMMA, ","));
                currentPos++;
            } else if (currentChar == '+'
                    || currentChar == '-'
                    || currentChar == '*'
                    || currentChar == '/'
                    || currentChar == '%') {
                tokens.add(new Token(TokenType.OP, String.valueOf(currentChar)));
                currentPos++;
            } else if (currentChar == '=') {
                m = ASSIGN.matcher(input.substring(currentPos));
                if (m.lookingAt()) {
                    tokens.add(new Token(TokenType.ASSIGN, m.group()));
                    currentPos += m.group().length();
                } else {
                    throw new RuntimeException("Invalid token: " + input.substring(currentPos));
                }
            } else if (currentChar == '>' || currentChar == '<' || currentChar == '=') {
                m = COMP_OP.matcher(input.substring(currentPos));
                if (m.lookingAt()) {
                    tokens.add(new Token(TokenType.COMP_OP, m.group()));
                    currentPos += m.group().length();
                } else {
                    throw new RuntimeException("Invalid token: " + input.substring(currentPos));
                }
            } else if (Character.isDigit(currentChar)) {
                m = NUMBER.matcher(input.substring(currentPos));
                if (m.lookingAt()) {
                    tokens.add(new Token(TokenType.NUMBER, m.group()));
                    currentPos += m.group().length();
                } else {
                    throw new RuntimeException("Invalid token: " + input.substring(currentPos));
                }
            } else if (Character.isLetter(currentChar)) {
                m = IDENTIFIER.matcher(input.substring(currentPos));
                if (m.lookingAt()) {
                    String text = m.group();
                    if (RESERVED.matcher(text).matches()) {
                        tokens.add(new Token(TokenType.RESERVED, text));
                    } else if (KEYWORD_IF.matcher(text).matches()) {
                        tokens.add(new Token(TokenType.KEYWORD_IF, text));
                    } else if (KEYWORD_THEN.matcher(text).matches()) {
                        tokens.add(new Token(TokenType.KEYWORD_THEN, text));
                    } else if (KEYWORD_ELSE.matcher(text).matches()) {
                        tokens.add(new Token(TokenType.KEYWORD_ELSE, text));
                    } else if (KEYWORD_WHILE.matcher(text).matches()) {
                        tokens.add(new Token(TokenType.KEYWORD_WHILE, text));
                    } else {
                        tokens.add(new Token(TokenType.IDENTIFIER, text));
                    }
                    currentPos += text.length();
                } else {
                    throw new RuntimeException("Invalid token: " + input.substring(currentPos));
                }
            } else if (Character.isWhitespace(currentChar)) {
                m = WHITESPACE.matcher(input.substring(currentPos));
                if (m.lookingAt()) {
                    currentPos += m.group().length();
                } else if (Character.isLetter(currentChar)) {
                    m = IDENTIFIER.matcher(input.substring(currentPos));
                    if (m.lookingAt()) {
                        String text = m.group();
                        if (RESERVED.matcher(text).matches()) {
                            tokens.add(new Token(TokenType.RESERVED, text));
                        } else if (KEYWORD_IF.matcher(text).matches()) {
                            tokens.add(new Token(TokenType.KEYWORD_IF, text));
                        } else if (KEYWORD_THEN.matcher(text).matches()) {
                            tokens.add(new Token(TokenType.KEYWORD_THEN, text));
                        } else if (KEYWORD_ELSE.matcher(text).matches()) {
                            tokens.add(new Token(TokenType.KEYWORD_ELSE, text));
                        } else if (KEYWORD_WHILE.matcher(text).matches()) {
                            tokens.add(new Token(TokenType.KEYWORD_WHILE, text));
                        } else {
                            tokens.add(new Token(TokenType.IDENTIFIER, text));
                        }
                        currentPos += text.length();
                    } else {
                        throw new RuntimeException("Invalid token: " + input.substring(currentPos));
                    }
                } else if (Character.isWhitespace(currentChar)) {
                    m = WHITESPACE.matcher(input.substring(currentPos));
                    if (m.lookingAt()) {
                        currentPos += m.group().length();
                    }
                } else if (currentChar == '/') {
                    if (input.charAt(currentPos + 1) == '/') {
                        currentPos = input.length();
                    } else {
                        tokens.add(new Token(TokenType.OP, "/"));
                        currentPos++;
                    }
                } else if (OPERATOR_CHARS.indexOf(currentChar) != -1) {
                    int startPos = currentPos;
                    while (OPERATOR_CHARS.indexOf(currentChar) != -1 && currentPos < input.length()) {
                        currentPos++;
                        if (currentPos < input.length()) {
                            currentChar = input.charAt(currentPos);
                        }
                    }
                    String operator = input.substring(startPos, currentPos);
                    tokens.add(new Token(TokenType.OP, operator));
                } else if (Character.isDigit(currentChar)) {
                    m = NUMBER.matcher(input.substring(currentPos));
                    if (m.lookingAt()) {
                        String number = m.group();
                        try {
                            Long.parseLong(number);
                            tokens.add(new Token(TokenType.NUMBER, number));
                        } catch (NumberFormatException e) {
                            throw new RuntimeException("Invalid number: " + number);
                        }
                        currentPos += number.length();
                    } else {
                        throw new RuntimeException("Invalid token: " + input.substring(currentPos));
                    }
                } else {
                    throw new RuntimeException("Invalid token: " + currentChar);
                }
            }
        }
        return tokens;
    }

    private void skipWhitespace() {
        while (currentPos < input.length() && Character.isWhitespace(input.charAt(currentPos))) {
            currentPos++;
        }
    }

    private void skipComment() {
        currentPos += 2; // skip the opening "/*" characters
        int commentEnd = input.indexOf("*/", currentPos);
        if (commentEnd == -1) {
            throw new RuntimeException("Unterminated comment");
        }
        currentPos = commentEnd + 2; // skip the closing "*/" characters
    }
}