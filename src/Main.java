import model.expression.ExpressionParser;
import view.Window;

public class Main {
    public static void main(String[] args) {

        ExpressionParser parser = new ExpressionParser();
        new Window(parser);

    }
}
