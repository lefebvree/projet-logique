import model.Expression;
import model.expression.ExpressionParser;
import view.Window;

public class Main {
    public static void main(String[] args) {

        ExpressionParser parser = new ExpressionParser();
        //Expression ex = parser.getExpression(3);
        Expression ex = parser.getRandomExpression();
        //Expression ex = Expression.createExpression("!(((p → q) Λ ((r Λ s) → p)) Λ (t → r))|!(s Λ t)");

        System.out.println("Expression: " + ex.toString());

        Window window = new Window(ex);
    }
}
