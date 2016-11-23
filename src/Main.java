import model.Expression;
import model.expression.ExpressionParser;
import view.Window;

public class Main {
    public static void main(String[] args) {

        ExpressionParser parser = new ExpressionParser();
        Expression ex = parser.getRandomExpression();

        System.out.println("Expression: " + ex.toString());

        Window window = new Window(ex);
    }
}
