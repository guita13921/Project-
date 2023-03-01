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
        String value = tkz.peek();
        if (!isReservedKeyword(value) && value != null) {
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
            Expr v = new Identifier(tkz.consume());
            String op = tkz.peek();
            tkz.consume("=");
            Expr right = parseExpression();
            return new BinaryAri(v,op,right);
        }else{
            throw new SyntaxError("Wrong in parseAssignmentStatement");
        }
    }

    private Expr parseActionCommand() throws SyntaxError{
        String value = tkz.peek();
            if(value.equals("done")){
                return new ActionCommand(tkz.consume());
            }else if(value.equals("relocate")){
                return new ActionCommand(tkz.consume());
            }else if(tkz.peek("move")){
                Expr v = parseMoveCommand();
                return v;
            }else if(tkz.peek("invest") || tkz.peek("collect")){
                Expr v = parseRegionCommand();
                return v;
            }else if(tkz.peek("shoot")){
                Expr v = parseAttackCommand();
                return v;
            }
        return null;
    }

    private Expr parseMoveCommand() throws SyntaxError{
        System.out.println("parseMoveCommand");
        tkz.consume("move");
        Expr v = parseDirection();
        return v;
    }

    private Expr parseRegionCommand() throws SyntaxError{
        System.out.println("IM parseRegionCommand");
        String value = tkz.peek();
        RegionCommand op = new RegionCommand(value);
        tkz.consume(value);
        Expr v = parseExpression();
        return new BinaryAri(null,op,v);
    }

    private Expr parseAttackCommand() throws SyntaxError{  //Not finish
        System.out.println("IM parseAttackCommand");
        Expr v =
        v = parseExpression();
        return v;
    }

    private Expr parseDirection() throws SyntaxError{
        System.out.println("IM parseDirection");
        return new Direction(tkz.consume());
    }

    private Expr parseBlockStatement() throws SyntaxError{ //Not FInish
        System.out.println("IM parseBlockStatement");
        tkz.consume("{");
        String value = tkz.peek();
        if(value.equals("}")){
            tkz.consume("}");
            return null;
        }else {
            Expr v = parseStatement();
            return v;
        }
    }
    private Expr parseIfStatement() throws SyntaxError{
        System.out.println("IM IfStatement");
        tkz.consume("if");
        tkz.consume("(");
        Expr left = parseExpression();
        tkz.consume(")");
        String value = tkz.peek();
        System.out.println(value);
        tkz.consume("then");
        Expr right = parseStatement();
        Expr v = new BinaryAri(left,value,right);
        value = tkz.peek();
        System.out.println(value);
        tkz.consume("else");
        Expr right2= parseStatement();
        v = new BinaryAri(v,value,right2);
        return v;
    }

    private Expr parseWhileStatement() throws SyntaxError{//Not finish
        System.out.println("IM parseWhileStatement");
        tkz.consume("while");
        tkz.consume("(");
        Expr left = parseExpression();
        tkz.consume(")");
        Expr right = parseStatement();
        Expr v = new BinaryAri(left, (Expr) null,right);
        return v;
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
                return v;
            }else{
                return v;
            }
    }

    private Expr parsePower() throws SyntaxError{ //Not Finish
        System.out.println("IM parsePower");
            String value = tkz.peek();
            if(isLong(value)) {
                return new Number(tkz.consume());
            }else if(!isReservedKeyword(value)){
                return new Identifier(tkz.consume());
            }else if(tkz.peek("opponent") || tkz.peek("nearby")){
                Expr v = parseInfoExpression();
                return v;
            }else{
                tkz.consume("(");
                Expr left = parseExpression();
                tkz.consume(")");
                return left;
            }
    }

    private Expr parseInfoExpression() throws SyntaxError{//Not Finish
        System.out.println("IM parseInfoExpression");
            if(tkz.peek("opponent")){
                //opponent
            } else if(tkz.peek("nearby")){
                //nearby
                Expr v = parseDirection();
                return v;
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
            case "(":
            case ")":
                return true;
            default:
                return false;
        }
    }
}
