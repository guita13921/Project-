package Parser;

import java.util.NoSuchElementException;
import java.util.List;
import java.util.Queue;

public class Tokenizer {
    private final String token;
    private String next = "";
    private int position = 0;
    private int line = 1;


    public Tokenizer(String token) throws SyntaxError {
        this.token = token;
        computeNext();
    }

    public String get_token(){
        return token;
    }

    private void computeNext() throws SyntaxError {
        StringBuilder s = new StringBuilder();
        while (position < token.length() && isSpace(token.charAt(position)))
            position++; // ignore whitespace
        if (position == token.length()) {
            next = null;
            return;        } // no more tokens
        char c = token.charAt(position);
        if (Character.isAlphabetic(c)){
            s.append(c);
            for (position++; position < token.length() &&
                    Character.isAlphabetic(token.charAt(position)); position++)
                s.append(token.charAt(position));
        }else if(c == '+' || c == '-' || c == '*'|| c == '/'|| c == '%'|| c == '('|| c == ')'|| c == '{'|| c == '}'|| c == '^'|| c == '='){
            s.append(c); position++;
        }else if(isDigit(c)){
            s.append(c);
            for (position++; position < token.length() &&
                    isDigit(token.charAt(position)); position++)
                s.append(token.charAt(position));
        }else{
            s.append(c);
            for (position++; position < token.length() &&
                    Character.isAlphabetic(token.charAt(position)); position++)
                s.append(token.charAt(position));
        }
        next = s.toString();
    }

    public boolean hasNextToken(){
        return next != null;
    }

    public String peek(){
        if(!hasNextToken()){
            return null;
        }else{
            return next;
        }
    }

    public String consume(){
        if(!hasNextToken()) throw new NoSuchElementException("no more token");
        String result = next;
        computeNext();
        return result;
    }



    /** Returns true if
     *  the next token (if any) is s. */
    public boolean peek(String s) {
        if (!hasNextToken()) return false;
        return peek().equals(s);
    }

    /** Consumes the next token if it is s.
     * Throws Parser.SyntaxError otherwise.
     * effects: removes the next token
     * from input stream if it is s
     */
    public void consume(String s){
        if(peek(s)){
            consume();
        }else{
            throw new SyntaxError(s + " Parser.SyntaxError");
        }
    }

    public static boolean isSpace(char c) {
        return c == ' ';
    }

    public static boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }

    public static boolean isNumber(String s) {
        try {
            Double.parseDouble(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean empty() {
        return token.isEmpty();
    }
}
