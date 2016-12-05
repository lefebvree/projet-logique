package model.expression;

import model.Expression;

import java.util.ArrayList;

public class Negation extends Expression {
    /*************
     * VARIABLES *
     *************/

    private Expression expression;

    /****************
     * CONSTRUCTORS *
     ****************/

    public Negation(Expression e) {
        this.expression = e;
    }

    /***********
     * METHODS *
     ***********/

    public int subExpressionNumber() {
        return 1;
    }

    public Expression solveExpression() {
        String className = this.expression.getClass().getSimpleName();
        Expression exp = null;
        ArrayList<Expression> subExpression = this.expression.getSubExpressions();

        switch(className) {
            case "Implication":
                exp = createExpression("("   + subExpression.get(0).toString() + "&!"  + subExpression.get(1).toString() + ")");
                break;
            case "Disjunction":
                exp = createExpression("(!" + subExpression.get(0).toString() + "&!" + subExpression.get(1).toString() + ")");
                break;
            case "Conjunction":
                exp = createExpression("(!" + subExpression.get(0).toString() + "|!" + subExpression.get(1).toString() + ")");
                break;
            case "Negation":
                exp = this.expression;
                break;
        }

        return exp;
    }

    public ArrayList<Expression> getSubExpressions() {

        ArrayList<Expression> a = new ArrayList<>();
        a.add(this.expression);

        return a;
    }

    public boolean isNegationOf(Litteral l) {
        return (this.expression.getClass() == Litteral.class && ((Litteral)this.expression).getName() == l.getName());
    }

    public String toString() {
        return "¬" + this.expression.toString();
    }
}
