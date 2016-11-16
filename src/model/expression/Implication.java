package model.expression;

import model.Expression;

import java.util.ArrayList;

public class Implication extends Expression {

    /*************
     * VARIABLES *
     *************/

    private Expression expression1;
    private Expression expression2;

    /****************
     * CONSTRUCTORS *
     ****************/

    public Implication(Expression e1, Expression e2) {
        this.expression1 = e1;
        this.expression2 = e2;
    }

    /***********
     * METHODS *
     ***********/

    public int subExpressionNumber() {
        return 2;
    }

    public ArrayList<Expression> getSubExpressions() {

        ArrayList<Expression> a = new ArrayList<>();
        a.add(this.expression1);
        a.add(this.expression2);

        return a;
    }

    public Expression solveExpression() {
        Expression exp = createExpression("(!" + this.expression1.toString() + "|" + this.expression2.toString() + ")");
        return exp;
    }

    public String toString() {
        return "(" + this.expression1.toString() + " → " + this.expression2.toString() + ")";
    }
}
