import java.util.LinkedList;
import java.util.NoSuchElementException;

public class Parser implements ParserInterface{

    private final Tokenizer tkz;

    private final Statement stm;
    public Parser(Tokenizer tkz){ this.tkz = tkz;}
    @Override
    public void parse() throws SyntaxError {
        try{
            String and = Statement();
        }catch(SyntaxError e){
            throw e;
        }
    }
    private Statement Statement() throws SyntaxError{
        Statement stm;
        char beChar = tkz.consume().charAt(0);

        try{
            if(tkz.peek("Command")){
                tkz.consume();
                Command();
            }
            else if (tkz.peek("done")){
                ActionCommand();
            }else if(tkz.peek("relocate")){
                ActionCommand();
            }
            else if(tkz.peek("move")){
                MoveCommand(tkz.consume());
            }else if(tkz.peek("invest")){
                tkz.consume();
                RegionCommand(tkz.consume());
            }
            else if(tkz.peek("collect")){
                tkz.consume();
                RegionCommand(tkz.consume());
            }else if(tkz.peek("shoot")){
                tkz.consume();
                AttackCommand(tkz.consume());
            }
            else if(tkz.peek("{")){
                tkz.consume();
                BlockStatement(tkz.consume());
            }
            else if(tkz.peek("if")){
                tkz.consume();
                IfStatement(tkz.consume());
            }
            else if(tkz.peek("while")){
                tkz.consume();
                WhileStatement(tkz.consume());
            }

        }catch (IllegalArgumentException | NoSuchElementException e){
            throw new SyntaxError(e.getMessage());

        }
        return null;
    }
    private void Command() throws SyntaxError{
        try{
            if(tkz.peek("AssignmentStatement")){
                tkz.consume();
                AssignmentStatement();
            }
            else if(tkz.peek("ActionCommand")){
                tkz.consume();
                ActionCommand();
            }
        }catch (IllegalArgumentException | NoSuchElementException e){
            throw new SyntaxError(e.getMessage());
        }
    }

    private void AssignmentStatement() throws SyntaxError{//////////////////////////
        try{
            if(tkz.peek("Expression")){
                tkz.consume();
                Expression();
            }
        }catch (IllegalArgumentException | NoSuchElementException e){
            throw new SyntaxError(e.getMessage());
        }
    }
    private void ActionCommand() throws SyntaxError{
        try{
            if(tkz.peek("done")){
                tkz.consume();
                
            }else if(tkz.peek("relocate")){
                tkz.consume();
                
            }else if(tkz.peek("MoveCommand")){
                tkz.consume();
                MoveCommand(tkz.consume());
            }else if(tkz.peek("RegionCommand")){
                tkz.consume();
                RegionCommand(tkz.consume());
            }else if(tkz.peek("AttackCommand")){
                tkz.consume();
                AttackCommand(tkz.consume());
            }
        }catch (IllegalArgumentException | NoSuchElementException e){
            throw new SyntaxError(e.getMessage());
        }
    }
    private void MoveCommand(String consume) throws SyntaxError{
        try{
            if(tkz.peek(consume)){
                tkz.consume();
                Direction();
            }
        }catch (IllegalArgumentException | NoSuchElementException e){
            throw new SyntaxError(e.getMessage());
        }
    }
    private void RegionCommand(String consume) throws SyntaxError{
        try{
            if(tkz.peek("Expression")){
                tkz.consume();
                Expression();
            }
        }catch (IllegalArgumentException | NoSuchElementException e){
            throw new SyntaxError(e.getMessage());
        }
    }
    private void AttackCommand(String consume) throws SyntaxError{
        try{
            if(tkz.peek(consume)){
                tkz.consume();
                Direction();
            }
        }catch (IllegalArgumentException | NoSuchElementException e){
            throw new SyntaxError(e.getMessage());
        }
    }

    private void Direction() throws SyntaxError{
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
    }
    private void BlockStatement(String consume) throws SyntaxError{
        try{
            LinkedList<Statement> l = new LinkedList<>() ;
            if(tkz.peek("}")){
                tkz.consume();
                Statement();
            }
            else l.add(Statement());
        }catch (IllegalArgumentException | NoSuchElementException e){
            throw new SyntaxError(e.getMessage());
        }
    }
    private void IfStatement(String consume) throws SyntaxError{
        try{
            tkz.consume("(");
            Expression();
            tkz.consume(")");
            tkz.consume("then");
            tkz.consume("else");
            Statement();
        }catch (IllegalArgumentException | NoSuchElementException e){
            throw new SyntaxError(e.getMessage());
        }
    }

    private void WhileStatement(String consume) throws SyntaxError{
        try{
            if(tkz.peek(consume)){
                tkz.consume();
                Statement();
            }
        }catch (IllegalArgumentException | NoSuchElementException e){
            throw new SyntaxError(e.getMessage());
        }
    }
    private void Expression() throws SyntaxError{
        try{
            if(tkz.peek("+")){
                tkz.consume();
                Term();
            }else if(tkz.peek("-")){
                tkz.consume();
                Term();
            }else if(tkz.peek("Term")){
                tkz.consume();
                Term();
            }
        }catch (IllegalArgumentException | NoSuchElementException e){
            throw new SyntaxError(e.getMessage());
        }
    }

    private void Term() throws SyntaxError{
        try{
            if(tkz.peek("*")){
                tkz.consume();
                Factor();
            }else if(tkz.peek("/")){
                tkz.consume();
                Factor();
            }else if(tkz.peek("%")){
                tkz.consume();
                Factor();
            }else if(tkz.peek("Term")){
                tkz.consume();
                Factor();
            }
        }catch (IllegalArgumentException | NoSuchElementException e){
            throw new SyntaxError(e.getMessage());
        }
    }

    private void Factor() throws SyntaxError{
        try{
            if(tkz.peek("^")){
                tkz.consume();
                Power();
            }else if(tkz.peek("Power")){
                tkz.consume();
                Power();
            }
        }catch (IllegalArgumentException | NoSuchElementException e){
            throw new SyntaxError(e.getMessage());
        }
    }

    private void Power() throws SyntaxError{
        try{
            if(tkz.peek(Integer)){//ต้องการเช็คว่าเป็นตัวเลขเปล่า
                tkz.consume();

            }else if(tkz.peek("identifier")){
                tkz.consume();
            }else if(tkz.peek("Expression")){
                tkz.consume();
                Expression();
            }else if(tkz.peek("InfoExpression")){
                tkz.consume();
                InfoExpression();
            }
        }catch (IllegalArgumentException | NoSuchElementException e){
            throw new SyntaxError(e.getMessage());
        }
    }

    private void InfoExpression() throws SyntaxError{
        try{
            if(tkz.peek("opponent")){
                tkz.consume();

            }
            else if (tkz.peek("nearby")){
                tkz.consume();
            }
            else if(tkz.peek("Direction")){
                tkz.consume();
                Direction();
            }
        }catch (IllegalArgumentException | NoSuchElementException e){
            throw new SyntaxError(e.getMessage());
        }
    }
}
