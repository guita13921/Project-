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
        tkz.consume("shoot");
        return new ActionCommand("shoot", parseDirection(),parseExpression());
    }

    //Direction → up | down | upleft | upright | downleft | downright
    private Direction parseDirection() throws SyntaxError {
        System.out.println("IM parseDirection");
        String value = tkz.peek();
        tkz.consume();
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
        System.out.println("IM parseIfStatement");
        System.out.println(tkz.peek());
        tkz.consume("if");
        tkz.consume("(");
        Statement Expression = parseExpression();
        System.out.println(tkz.peek());
        tkz.consume(")");
        System.out.println(tkz.peek());
        tkz.consume("then");
        Statement thenStatement = parseStatement();
        System.out.println(tkz.peek());
        tkz.consume("else");
        Statement elseeStatement = parseStatement();
        return new IfStatement(Expression, thenStatement, elseeStatement);
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
        Statement term = parseTerm();
        while(tkz.peek("+")) {
            tkz.consume();
            term = new BinaryAri(parseExpression(),"+",term);
        }
        while (tkz.peek("-")){
            tkz.consume();
            term = new BinaryAri(parseExpression(),"-",term);
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
        if (tkz.isNumber(tkz.peek())) {
            return new Long(Integer.parseInt(tkz.consume()));
        } else if (tkz.peek("(")) {
            tkz.consume("(");
            Statement expression = parseExpression();
            tkz.consume(")");
            return expression;
        } else if (tkz.peek("opponent") || tkz.peek("nearby")) {
            return parseInfoExpression();
        } else {
            return parseIdentifier();
        }
    }

    //InfoExpression → opponent | nearby Direction
    private Statement parseInfoExpression() throws SyntaxError {
        System.out.println("IM parseInfoExpression");
        String value = tkz.peek();
            if(value.equals("opponent")) {
                tkz.consume();
                return new InfoExpr("opponent");
            }else if(value.equals("nearby")){
                tkz.consume();
                Direction direction = parseDirection();
                return new InfoExpr("nearby",direction);
            }else {
                throw new SyntaxError("Error");
            }
    }

    private Identifier parseIdentifier() throws SyntaxError {
        System.out.println("IM parseIdentifier");
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
