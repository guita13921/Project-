package Parser;

import java.util.LinkedList;

public class Parser implements ParserInterface {

    private final Tokenizer tkz;
    private Plan plan;
    private VariableStorage variableStorage = new VariableStorage();

    public Parser(Tokenizer tkz) {
        this.tkz = tkz;
    }

    @Override

    public String parse(){
        Plan plan = new Plan();
        System.out.println("IM Parser.Plan");
        if (!tkz.hasNextToken()) {
            throw new SyntaxError(">> No more Tokens");
        }
        plan.addState(parseStatement());
        return plan.eval();
    }

    private Statement parseStatement() throws SyntaxError {
        System.out.println("IM parseStatement");
        if (tkz.peek("{")) {
            return parseBlockStatement();
        } else if (tkz.peek("if") || tkz.peek("than")) {
            return parseIfStatement();
        } else if (tkz.peek("while")) {
            return parseWhileStatement();
        } else {
            return parseCommand();
        }
    }


    private Statement parseCommand() throws SyntaxError {
        System.out.println("IM parseCommand");
        String value = tkz.peek();
        if (!isReservedKeyword(value)) {
            return parseAssignmentStatement();
        } else {
            return parseActionCommand();
        }
    }

    //AssignmentStatement → <identifier> = Expression
    private Statement parseAssignmentStatement() throws SyntaxError {
        System.out.println("IM parseAssignmentStatement");
        Identifier identifier = parseIdentifier();
        tkz.consume("=");
        Statement exp = parseExpression();
        return new AssignStatement(identifier,"=",exp,variableStorage);

    }

    private Statement parseActionCommand() throws SyntaxError {
        String value = tkz.peek();
        if (value.equals("done")) {
            tkz.consume();
            return new ActionCommand("done");
        } else if (value.equals("relocate")) {
            tkz.consume();
            return new ActionCommand("relocate");
        } else if (tkz.peek("move")) {
            return parseMoveCommand();
        } else if (tkz.peek("invest") || tkz.peek("collect")) {
            return parseRegionCommand();
        } else if (tkz.peek("shoot")) {
            return parseAttackCommand();
        }else{
            throw new SyntaxError("Error");
        }
    }

    private Statement parseMoveCommand() throws SyntaxError {
        System.out.println("parseMoveCommand");
        tkz.consume("move");
        return new ActionCommand("move",parseDirection());
    }

    //RegionCommand → invest Expression | collect Expression
    private Statement parseRegionCommand() throws SyntaxError {
        System.out.println("IM parseRegionCommand");
        String value = tkz.peek();
        if(value.equals("invest")){
            tkz.consume();
            return new ActionCommand("invest",parseExpression());
        }else{
            tkz.consume();
            return new ActionCommand("collect",parseExpression());
        }

    }

    //AttackCommand → shoot Direction Expression
    private Statement parseAttackCommand() throws SyntaxError {
        System.out.println("IM parseAttackCommand");
        String value = tkz.peek();
        tkz.consume();
        return new ActionCommand("shoot", parseDirection());
    }

    //Direction → up | down | upleft | upright | downleft | downright
    private Direction parseDirection() throws SyntaxError {
        System.out.println("IM parseDirection");
        String value = tkz.peek();
        return new Direction(value);
    }

    //BlockStatement → { Statement* }
    private BlockStatement parseBlockStatement() throws SyntaxError { //Not FInish
        System.out.println("IM parseBlockStatement");
        tkz.consume("{");
        LinkedList<Statement> state = new LinkedList<>();
        while (!tkz.peek("}")) {
            state.add(parseStatement());
        }
        tkz.consume("}");
        return new BlockStatement(state);
    }
    //IfStatement → if ( Expression ) then Statement else Statemen
    private Statement parseIfStatement() throws SyntaxError {
        System.out.println("IM IfStatement");
        tkz.consume("if");
        tkz.consume("(");
        Statement Expression = parseExpression();
        tkz.consume(")");
        String value = tkz.peek();
        System.out.println(value);
        tkz.consume("then");
        Statement thanStatement = parseStatement();
        value = tkz.peek();
        System.out.println(value);
        tkz.consume("else");
        Statement elseStatement = parseStatement();
        return new IfStatement(Expression,thanStatement,elseStatement);
    }

    //WhileStatement → while ( Expression ) Statement
    private Statement parseWhileStatement() throws SyntaxError {
        System.out.println("IM parseWhileStatement");
        tkz.consume("while");
        Statement Expression = parseExpression();
        Statement statement = parseStatement();
        return new WhileStatement(Expression,statement);
    }

    //Expression → Expression + Term | Expression - Term | Term
    private Statement parseExpression() throws SyntaxError {
        System.out.println("IM parseExpression");
        String value = tkz.peek();
        Statement term = parseTerm();
        while(value.equals("+") || value.equals("-")){
            if(value.equals("+")){
                tkz.consume();
                term = new BinaryAri(term, "+", parseTerm());
            }else if(value.equals("-")){
                tkz.consume();
                term = new BinaryAri(term, "-", parseTerm());
            }else{
                throw new SyntaxError("Error");
            }
        }
        return term;
    }

    //Term → Term * Factor | Term / Factor | Term % Factor | Factor
    private Statement parseTerm() throws SyntaxError {
        System.out.println("IM parseTerm");
        Statement factor = parseFactor();
        while (tkz.peek("*") || tkz.peek("/") || tkz.peek("%")) {
            if (tkz.peek().equals("*")) {
                tkz.consume();
                factor = new BinaryAri(factor, "*", parseFactor());
            } else if (tkz.peek().equals("/")) {
                tkz.consume();
                factor = new BinaryAri(factor, "/", parseFactor());
            } else if (tkz.peek().equals("%")) {
                tkz.consume();
                factor = new BinaryAri(factor, "%", parseFactor());
            } else {
                throw new SyntaxError("Error");
            }
        }
        return factor;
    }

    //Factor → Power ^ Factor | Power
    private Statement parseFactor() throws SyntaxError {
        System.out.println("IM parseFactor");
        Statement power = parsePower();
            if(tkz.peek("^")){
                tkz.consume("^");
                power = new BinaryAri(power,"^",parseFactor());
            }
            return power;
    }

    //Power → <number> | <identifier> | ( Expression ) | InfoExpression
    private Statement parsePower() throws SyntaxError {
        System.out.println("IM parsePower");
            String value = tkz.peek();
            if(isLong(value)) {
                return new java.lang.Number(tkz.consume());
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

    private Statement parseInfoExpression() throws SyntaxError {
        System.out.println("IM parseInfoExpression");
        String value = tkz.peek();
            if(value.equals("opponent")) {
                return new InfoExpression(tkz.consume());
            }else {
                InfoExpression left = new InfoExpression(tkz.consume());
                Expr right = parseDirection();
                return new BinaryAri(left, (Expr) null, right);
            }
    }

    private Identifier parseIdentifier() throws SyntaxError {
        if (isReservedKeyword(tkz.peek())){
            tkz.consume();
            throw new SyntaxError("Error");
        }
        if (!tkz.isNumber("" + tkz.peek().charAt(0))) {
            if (tkz.peek().substring(1).chars().allMatch(Character::isLetterOrDigit)) {
                return new Identifier(tkz.consume(),variableStorage);
            }
        }
        throw new SyntaxError("Error");
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
