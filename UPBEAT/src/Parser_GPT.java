import java.util.List;
import java.util.ArrayList;

public class Parser_GPT {

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

    // Parse the non-terminal symbol "Statement"
    private Statement parseStatement() {
        if (currentToken.getType() == Lexer.TokenType.IDENTIFIER) {
            // This is a command or assignment statement
            String identifier = currentToken.getText();
            currentToken = lexer.nextToken();
            if (currentToken.getType() == Lexer.TokenType.ASSIGN) {
                // This is an assignment statement
                currentToken = lexer.nextToken();
                Expression expression = parseExpression();
                return new AssignmentStatement(identifier, expression);
            } else {
                // This is an action command
                return parseActionCommand(identifier);
            }
        } else if (currentToken.getType() == Lexer.TokenType.LBRACE) {
            // This is a block statement
            currentToken = lexer.nextToken();
            List<Statement> statements = new ArrayList<>();
            while (currentToken.getType() != Lexer.TokenType.RBRACE) {
                statements.add(parseStatement());
            }
            currentToken = lexer.nextToken();
            return new BlockStatement(statements);
        } else if (currentToken.getType() == Lexer.TokenType.KEYWORD_IF) {
            // This is an if statement
            currentToken = lexer.nextToken();
            expectToken(Lexer.TokenType.LPAREN);
            Expression condition = parseExpression();
            expectToken(Lexer.TokenType.KEYWORD_THEN);
            Statement thenStatement = parseStatement();
            expectToken(Lexer.TokenType.KEYWORD_ELSE);
            Statement elseStatement = parseStatement();
            return new IfStatement(condition, thenStatement, elseStatement);
        } else if (currentToken.getType() == Lexer.TokenType.KEYWORD_WHILE) {
            // This is a while statement
            currentToken = lexer.nextToken();
            expectToken(Lexer.TokenType.LPAREN);
            Expression condition = parseExpression();
            Statement body = parseStatement();
            return new WhileStatement(condition, body);
        } else {
            throw new RuntimeException("Invalid statement: " + currentToken);
        }
    }

    // Parse the non-terminal symbol "Command" as an action command
    private ActionCommand parseActionCommand(String command) {
        switch (command) {
            case "done":
                currentToken = lexer.nextToken();
                return new DoneCommand();
            case "relocate":
                currentToken = lexer.nextToken();
                return new RelocateCommand();
            case "move":
                Direction direction = parseDirection();
                return new MoveCommand(direction);
            case "invest":
                currentToken = lexer.nextToken();
                Expression expression = parseExpression();
                return new InvestCommand(expression);
            case "collect":
                currentToken = lexer.nextToken();
                expression = parseExpression();
                return new CollectCommand(expression);
            case "shoot":
                direction = parseDirection();
                expectToken(Lexer.TokenType.NUMBER);
                long power = Long.parseLong(currentToken.getText());
                currentToken = lexer.nextToken();
                return new AttackCommand(direction, power);
            default:
                throw new RuntimeException("Invalid command: " + command);
        }
    }

    // Parse the non-terminal symbol "Direction"
    private Direction parseDirection() {
        switch (currentToken.getType()) {
            case KEYWORD_UP:
                consumeToken();
                if (currentToken.getType() == TokenType.KEYWORD_LEFT) {
                    consumeToken();
                    return Direction.UP_LEFT;
                } else if (currentToken.getType() == TokenType.KEYWORD_RIGHT) {
                    consumeToken();
                    return Direction.UP_RIGHT;
                } else {
                    return Direction.UP;
                }
            case KEYWORD_DOWN:
                consumeToken();
                if (currentToken.getType() == TokenType.KEYWORD_LEFT) {
                    consumeToken();
                    return Direction.DOWN_LEFT;
                } else if (currentToken.getType() == TokenType.KEYWORD_RIGHT) {
                    consumeToken();
                    return Direction.DOWN_RIGHT;
                } else {
                    return Direction.DOWN;
                }
            case KEYWORD_UPLEFT:
                consumeToken();
                return Direction.UP_LEFT;
            case KEYWORD_UPRIGHT:
                consumeToken();
                return Direction.UP_RIGHT;
            case KEYWORD_DOWNLEFT:
                consumeToken();
                return Direction.DOWN_LEFT;
            case KEYWORD_DOWNRIGHT:
                consumeToken();
                return Direction.DOWN_RIGHT;
            default:
                throw new RuntimeException("Invalid direction: " + currentToken.getText());
        }
    }

}