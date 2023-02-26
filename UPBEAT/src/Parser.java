import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser implements ParserInterface{

    private final Tokenizer tkz;
    public Parser(Tokenizer tkz){ this.tkz = tkz;}
    @Override

    public Expr parse() throws SyntaxError {
        System.out.println("IM Plan");
        if(!tkz.hasNextToken()){
            throw new SyntaxError(">> No more Tokens");
        }
        return parseStatement();
    }

    private Expr parseStatement() throws SyntaxError{
        System.out.println("IM parseStatement");
        if (tkz.peek("{")) {
            Expr v = parseBlockStatement();
            return v;
        } else if (tkz.peek("if")) {
            Expr v = parseIfStatement();
            return v;
        } else if (tkz.peek("while")) {
            Expr v = parseWhileStatement();
            return v;
        } else {
            Expr v = parseCommand();
            return v;
        }
    }


    private Expr parseCommand() throws SyntaxError{
        System.out.println("IM parseCommand");
        String value = tkz.peek();
        if(!isReservedKeyword(value)){
            Expr v = parseAssignmentStatement();
            return v;
        }else{
            Expr v = parseActionCommand();
            return v;
        }
    }

    private Expr parseAssignmentStatement() throws SyntaxError{ //NOT FINISH
        System.out.println("IM parseAssignmentStatement");
        String value = tkz.peek();
        if(!isReservedKeyword(value)){
            tkz.consume();
            //กำหนดตัวแปร
            tkz.consume("=");
            Expr v = parseExpression();
            return v;
        }else{
            throw new SyntaxError("Wrong in parseAssignmentStatement");
        }
    }

    private Expr parseActionCommand() throws SyntaxError{
            if(tkz.peek("done")){
                tkz.consume();
                
            }else if(tkz.peek("relocate")){
                tkz.consume();
                
            }else if(tkz.peek("MoveCommand")){
                tkz.consume();
                parseMoveCommand();
            }else if(tkz.peek("RegionCommand")){
                tkz.consume();
                parseRegionCommand();
            }else if(tkz.peek("AttackCommand")){
                tkz.consume();
                parseAttackCommand();
            }
        return null;
    }

    private Expr parseMoveCommand() throws SyntaxError{
            if(tkz.peek("Direction")){
                tkz.consume();
                parseDirection();
            }
        return null;
    }

    private Expr parseRegionCommand() throws SyntaxError{
            if(tkz.peek("Expression")){
                tkz.consume();
                parseExpression();
            }
        return null;
    }
    private Expr parseAttackCommand() throws SyntaxError{
            if(tkz.peek("shootDirectionExpression")){
                tkz.consume();
                parseDirection();
            }
        return null;
    }

    private Expr parseDirection() throws SyntaxError{
            if(tkz.peek("up")){
                tkz.consume();

            }else if(tkz.peek("down")){
                tkz.consume();
            }
            else if(tkz.peek("upleft")){
                tkz.consume();
            }else if(tkz.peek("downleft")){
                tkz.consume();
            }else if(tkz.peek("upright")){
                tkz.consume();
            }else if(tkz.peek("downright")){
                tkz.consume();
            }
        return null;
    }

    private Expr parseBlockStatement() throws SyntaxError{
            if(tkz.peek("Statement")){
                tkz.consume();
                parseStatement();
            }
        return null;
    }
    private Expr parseIfStatement() throws SyntaxError{
        System.out.println("IM IfStatement");
            tkz.consume();
            tkz.consume("(");
            Expr v = parseExpression();
            tkz.consume(")");
            Expr THEN = null;
            Expr ELSE = null;
            if(tkz.peek("then")){
                tkz.consume();
                THEN = parseStatement();
            }
            if(tkz.peek("else")){
                tkz.consume();
                ELSE = parseStatement();
            }
            v = new BinaryAri(v,THEN,ELSE);
            return v;
    }

    private Expr parseWhileStatement() throws SyntaxError{
            if(tkz.peek("whileExpressionStatement")){
                tkz.consume();
                parseStatement();
            }
        return null;
    }

    private Expr parseExpression() throws SyntaxError{
        System.out.println("IM parseExpression");
        Expr v = parseTerm();
            while (tkz.peek("+") || tkz.peek("-")){
                String operator = tkz.consume();
                Expr left = parseExpression();
                v = new BinaryAri(left,operator,v);
            }
        return v;
    }

    private Expr parseTerm() throws SyntaxError{
        System.out.println("IM parseTerm");
        Expr v = parseFactor();
            while (tkz.peek("*") || tkz.peek("/") || tkz.peek("%")){
                String operator = tkz.consume();
                Expr left = parseFactor();
                v = new BinaryAri(left,operator,v);
            }
        return v;
    }

    private Expr parseFactor() throws SyntaxError{
        System.out.println("IM parseFactor");
        Expr v = parsePower();
            if(tkz.peek("^")){
                String operator = tkz.consume();
                Expr right = parseFactor();
                v = new BinaryAri(v,operator,right);
            }
        return null;
    }

    private Expr parsePower() throws SyntaxError{ //Not Finish
        System.out.println("IM parsePower");
            String value = tkz.peek();
            if(isLong(value) || !isReservedKeyword(value)){
                tkz.consume();
                //สร้าง identifier หรือ number
            }else if(tkz.peek("opponent") || tkz.peek("nearby")){
                Expr v = parseInfoExpression();
                return v;
            }else{
                tkz.consume("(");
                Expr v = parseExpression();
                tkz.consume(")");
                return v;
        }
            return null;
    }

    private Expr parseInfoExpression() throws SyntaxError{
            if(tkz.peek("opponent")){
                tkz.consume();
            }
            else if(tkz.peek("Direction")){
                tkz.consume();
                return parseDirection();
            }
        return null;
    }

    public static boolean isLong(String input) {
        try {
            Long.parseLong(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isReservedKeyword(String input) {
        switch (input) {
            case "collect":
            case "done":
            case "down":
            case "downleft":
            case "downright":
            case "else":
            case "if":
            case "invest":
            case "move":
            case "nearby":
            case "opponent":
            case "relocate":
            case "shoot":
            case "then":
            case "up":
            case "upleft":
            case "upright":
            case "while":
                return true;
            default:
                return false;
        }
    }
}
