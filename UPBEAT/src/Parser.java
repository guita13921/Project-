import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser implements ParserInterface{

    private final Tokenizer tkz;
    public Parser(Tokenizer tkz){ this.tkz = tkz;}
    @Override

    public Expr parse() throws SyntaxError {
        if(!tkz.hasNextToken()){
            throw new SyntaxError(">> No more Tokens");
        }
        return parseStatement();
    }

    private Expr parseStatement() throws SyntaxError{
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
        try{
            if(tkz.peek("AssignmentStatement")){
                Expr v = parseAssignmentStatement();
                return v;
            }
            else if(tkz.peek("ActionCommand")){
                Expr v = parseActionCommand();
                return v;
            }
        }catch (IllegalArgumentException | NoSuchElementException e){
            throw new SyntaxError(e.getMessage());
        }
        return null;
    }

    private Expr parseAssignmentStatement() throws SyntaxError{
        try{
            if(tkz.peek("Expression")){
                Expr v = parseExpression();
                return v;
            }
        }catch (IllegalArgumentException | NoSuchElementException e){
            throw new SyntaxError(e.getMessage());
        }
        return null;
    }

    private Expr parseActionCommand() throws SyntaxError{
        try{
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
        }catch (IllegalArgumentException | NoSuchElementException e){
            throw new SyntaxError(e.getMessage());
        }
        return null;
    }

    private Expr parseMoveCommand() throws SyntaxError{
        try{
            if(tkz.peek("Direction")){
                tkz.consume();
                parseDirection();
            }
        }catch (IllegalArgumentException | NoSuchElementException e){
            throw new SyntaxError(e.getMessage());
        }
        return null;
    }

    private Expr parseRegionCommand() throws SyntaxError{
        try{
            if(tkz.peek("Expression")){
                tkz.consume();
                parseExpression();
            }
        }catch (IllegalArgumentException | NoSuchElementException e){
            throw new SyntaxError(e.getMessage());
        }
        return null;
    }
    private Expr parseAttackCommand() throws SyntaxError{
        try{
            if(tkz.peek("shootDirectionExpression")){
                tkz.consume();
                parseDirection();
            }
        }catch (IllegalArgumentException | NoSuchElementException e){
            throw new SyntaxError(e.getMessage());
        }
        return null;
    }

    private Expr parseDirection() throws SyntaxError{
        try{
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
        }catch (IllegalArgumentException | NoSuchElementException e){
            throw new SyntaxError(e.getMessage());
        }
        return null;
    }

    private Expr parseBlockStatement() throws SyntaxError{
        try{
            if(tkz.peek("Statement")){
                tkz.consume();
                parseStatement();
            }
        }catch (IllegalArgumentException | NoSuchElementException e){
            throw new SyntaxError(e.getMessage());
        }
        return null;
    }
    private Expr parseIfStatement() throws SyntaxError{
        System.out.println("IM IfStatement");
        try{
            if(tkz.peek("Expression")){
                tkz.consume();
            }
        }catch (IllegalArgumentException | NoSuchElementException e){
            throw new SyntaxError(e.getMessage());
        }
        return null;
    }

    private Expr parseWhileStatement() throws SyntaxError{
        try{
            if(tkz.peek("whileExpressionStatement")){
                tkz.consume();
                parseStatement();
            }
        }catch (IllegalArgumentException | NoSuchElementException e){
            throw new SyntaxError(e.getMessage());
        }
        return null;
    }

    private Expr parseExpression() throws SyntaxError{
        try{
            if(tkz.peek("Expression+Term")){
                tkz.consume();
                parseTerm();
            }else if(tkz.peek("Expression-Term")){
                tkz.consume();
                parseTerm();
            }else if(tkz.peek("Term")){
                tkz.consume();
                parseTerm();
            }
        }catch (IllegalArgumentException | NoSuchElementException e){
            throw new SyntaxError(e.getMessage());
        }
        return null;
    }

    private Expr parseTerm() throws SyntaxError{
        try{
            if(tkz.peek("Term*Factor")){
                tkz.consume();
                parseFactor();
            }else if(tkz.peek("Term/Factor")){
                tkz.consume();
                parseFactor();
            }else if(tkz.peek("Term%Factor")){
                tkz.consume();
                parseFactor();
            }else if(tkz.peek("Term")){
                tkz.consume();
                parseFactor();
            }
        }catch (IllegalArgumentException | NoSuchElementException e){
            throw new SyntaxError(e.getMessage());
        }
        return null;
    }

    private Expr parseFactor() throws SyntaxError{
        try{
            if(tkz.peek("Power^Factor")){
                tkz.consume();
                parsePower();
            }else if(tkz.peek("Power")){
                tkz.consume();
                parsePower();
            }
        }catch (IllegalArgumentException | NoSuchElementException e){
            throw new SyntaxError(e.getMessage());
        }
        return null;
    }

    private Expr parsePower() throws SyntaxError{
        try{
            if(tkz.peek("number")){
                tkz.consume();

            }else if(tkz.peek("identifier")){
                tkz.consume();
            }else if(tkz.peek("Expression")){
                tkz.consume();
                parseExpression();
            }else if(tkz.peek("InfoExpression")){
                tkz.consume();
                parseInfoExpression();
            }
        }catch (IllegalArgumentException | NoSuchElementException e){
            throw new SyntaxError(e.getMessage());
        }
        return null;
    }

    private Expr parseInfoExpression() throws SyntaxError{
        try{
            if(tkz.peek("opponent")){
                tkz.consume();

            }
            else if(tkz.peek("Direction")){
                tkz.consume();
                return parseDirection();
            }
        }catch (IllegalArgumentException | NoSuchElementException e){
            throw new SyntaxError(e.getMessage());
        }
        return null;
    }
}
