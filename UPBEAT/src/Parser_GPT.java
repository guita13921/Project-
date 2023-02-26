import com.sun.source.tree.AssignmentTree;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class Parser_GPT {
    /*
    private Lexer lexer;
    private Token currentToken;

    public Parser_GPT(Lexer lexer) {
        this.lexer = lexer;
        currentToken = lexer.nextToken();
    }

    // Parse the start symbol "Plan"
    public List<Statement> parsePlan() {
        List<Statement> statements = new ArrayList<>();
        while (currentToken.getType() != Lexer.TokenType.EOF) {
            statements.add(parseStatement());
        }
        return statements;
    }

    public Statement parseStatement() throws ParseException {
        switch (currentToken.getType()) {
            case KEYWORD_IF:
                return parseIfStatement();
            case WHILE:
                return parseWhileStatement();
            case LBRACE:
                return parseBlockStatement();
            default:
                return parseCommand();
        }
    }

    public Command parseCommand() throws ParseException {
        switch (currentToken.getType()) {
            case IDENTIFIER:
                return parseAssignmentStatement();
            case DONE:
            case RELOCATE:
                return parseActionCommand();
            case MOVE:
                return parseMoveCommand();
            case INVEST:
            case COLLECT:
                return parseRegionCommand();
            case SHOOT:
                return parseAttackCommand();
            default:
                throw new ParseException("Unexpected token " + currentToken.getType());
        }
    }

    public AssignmentStatement parseAssignmentStatement() throws ParseException {
        String identifier = currentToken.getLexeme();
        match(TokenType.IDENTIFIER);
        match(TokenType.ASSIGN);
        Expression expression = parseExpression();
        return new AssignmentStatement(identifier, expression);
    }

    public ActionCommand parseActionCommand() throws ParseException {
        switch (currentToken.getType()) {
            case DONE:
                match(TokenType.DONE);
                return new ActionCommand(ActionType.DONE);
            case RELOCATE:
                match(TokenType.RELOCATE);
                return new ActionCommand(ActionType.RELOCATE);
            default:
                throw new ParseException("Unexpected token " + currentToken.getType());
        }
    }

    public MoveCommand parseMoveCommand() throws ParseException {
        match(TokenType.MOVE);
        Direction direction = parseDirection();
        return new MoveCommand(direction);
    }

    public RegionCommand parseRegionCommand() throws ParseException {
        switch (currentToken.getType()) {
            case INVEST:
                match(TokenType.INVEST);
                Expression investExpr = parseExpression();
                return new RegionCommand(RegionType.INVEST, investExpr);
            case COLLECT:
                match(TokenType.COLLECT);
                Expression collectExpr = parseExpression();
                return new RegionCommand(RegionType.COLLECT, collectExpr);
            default:
                throw new ParseException("Unexpected token " + currentToken.getType());
        }
    }

    public AttackCommand parseAttackCommand() throws ParseException {
        match(TokenType.SHOOT);
        Direction direction = parseDirection();
        Expression expression = parseExpression();
        return new AttackCommand(direction, expression);
    }

    public IfStatement parseIfStatement() throws ParseException {
        match(TokenType.IF);
        match(TokenType.LEFT_PAREN);
        Expression condition = parseExpression();
        match(TokenType.RIGHT_PAREN);
        Statement thenStatement = parseStatement();
        match(TokenType.ELSE);
        Statement elseStatement = parseStatement();
        return new IfStatement(condition, thenStatement, elseStatement);
    }

    public WhileStatement parseWhileStatement() throws ParseException {
        match(TokenType.WHILE);
        match(TokenType.LEFT_PAREN);
        Expression condition = parseExpression();
        match(TokenType.RIGHT_PAREN);
        Statement body = parseStatement();
        return new WhileStatement(condition, body);
    }

    public BlockStatement parseBlockStatement() throws ParseException {
        match(TokenType.LEFT_BRACE);
        List<Statement> statements = new ArrayList<>();
        while (currentToken.getType() != TokenType.RIGHT_BRACE) {
            Statement statement = parseStatement();
            statements.add(statement);
        }
        match(TokenType.RIGHT_BRACE);
        return new BlockStatement(statements);
    }

    public Direction parseDirection() throws ParseException {
        switch (currentToken.getType()) {
            case UP:
                match(TokenType.UP);
                return Direction.UP;
            case DOWN:
                match(TokenType.DOWN);
                return Direction.DOWN;
            case UP_LEFT:
                match(TokenType.UP_LEFT);
                return Direction.UP_LEFT;
            case UP_RIGHT:
                match(TokenType.UP_RIGHT);
                return Direction.UP_RIGHT;
        }
        return null;
    }

    private ActionCommand parseActionCommand() throws ParseException {
        switch (currentToken.getType()) {
            case DONE:
                match(TokenType.DONE);
                return new ActionCommand(ActionCommandType.DONE);
            case RELOCATE:
                match(TokenType.RELOCATE);
                return new ActionCommand(ActionCommandType.RELOCATE);
            case MOVE:
                match(TokenType.MOVE);
                Direction direction = parseDirection();
                return new MoveCommand(direction);
            case INVEST:
                match(TokenType.INVEST);
                Expression investExpr = parseExpression();
                return new RegionCommand(RegionCommandType.INVEST, investExpr);
            case COLLECT:
                match(TokenType.COLLECT);
                Expression collectExpr = parseExpression();
                return new RegionCommand(RegionCommandType.COLLECT, collectExpr);
            case SHOOT:
                match(TokenType.SHOOT);
                Direction shootDirection = parseDirection();
                Expression shootExpr = parseExpression();
                return new AttackCommand(shootDirection, shootExpr);
            default:
                throw new ParseException("Invalid action command", currentToken);
        }
    }
*/
}