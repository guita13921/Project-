import java.util.NoSuchElementException;

public class Parser implements ParserInterface{

    private final Tokenizer tkz;
    public Parser(Tokenizer tkz){ this.tkz = tkz;}
    @Override
    public void parse() throws SyntaxError {
        try{
            String and = Statement();
        }catch(SyntaxError e){
            throw e;
        }
    }
    private String Statement() throws SyntaxError{
        try{
            if(tkz.peek("Command")){
                tkz.consume();
                Command();
            }
            else if(tkz.peek("BlockStatement")){
                tkz.consume();
                BlockStatement();
            }
            else if(tkz.peek("IfStatement")){
                tkz.consume();
                IfStatement();
            }
            else if(tkz.peek("WhileStatement")){
                tkz.consume();
                WhileStatement();
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

    private void AssignmentStatement() throws SyntaxError{
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
                MoveCommand();
            }else if(tkz.peek("RegionCommand")){
                tkz.consume();
                RegionCommand();
            }else if(tkz.peek("AttackCommand")){
                tkz.consume();
                AttackCommand();
            }
        }catch (IllegalArgumentException | NoSuchElementException e){
            throw new SyntaxError(e.getMessage());
        }
    }
    private void MoveCommand() throws SyntaxError{
        try{
            if(tkz.peek("Direction")){
                tkz.consume();
                Direction();
            }
        }catch (IllegalArgumentException | NoSuchElementException e){
            throw new SyntaxError(e.getMessage());
        }
    }
    private void RegionCommand() throws SyntaxError{
        try{
            if(tkz.peek("Expression")){
                tkz.consume();
                Expression();
            }
        }catch (IllegalArgumentException | NoSuchElementException e){
            throw new SyntaxError(e.getMessage());
        }
    }
    private void AttackCommand() throws SyntaxError{
        try{
            if(tkz.peek("shootDirectionExpression")){
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
    private void BlockStatement() throws SyntaxError{
        try{
            if(tkz.peek("Statement")){
                tkz.consume();
                Statement();
            }
        }catch (IllegalArgumentException | NoSuchElementException e){
            throw new SyntaxError(e.getMessage());
        }
    }
    private void IfStatement() throws SyntaxError{
        try{
            if(tkz.peek("Expression")){
                tkz.consume();
            }
        }catch (IllegalArgumentException | NoSuchElementException e){
            throw new SyntaxError(e.getMessage());
        }
    }

    private void WhileStatement() throws SyntaxError{
        try{
            if(tkz.peek("whileExpressionStatement")){
                tkz.consume();
                Statement();
            }
        }catch (IllegalArgumentException | NoSuchElementException e){
            throw new SyntaxError(e.getMessage());
        }
    }
    private void Expression() throws SyntaxError{
        try{
            if(tkz.peek("Expression+Term")){
                tkz.consume();
                Term();
            }else if(tkz.peek("Expression-Term")){
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
            if(tkz.peek("Term*Factor")){
                tkz.consume();
                Factor();
            }else if(tkz.peek("Term/Factor")){
                tkz.consume();
                Factor();
            }else if(tkz.peek("Term%Factor")){
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
            if(tkz.peek("Power^Factor")){
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
            if(tkz.peek("number")){
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
            else if(tkz.peek("Direction")){
                tkz.consume();
                Direction();
            }
        }catch (IllegalArgumentException | NoSuchElementException e){
            throw new SyntaxError(e.getMessage());
        }
    }
}
