import model.Expression;

import static model.Expression.createExpression;

public class Main {
    public static void main(String[] args) {
        Expression e = createExpression("(p&(p>q)&((p>q)>r))>(p&q&r)");
        System.out.println(e.toString());
    }
}
