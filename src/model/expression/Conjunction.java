package model.expression;

import model.Expression;

import java.util.ArrayList;

public class Conjunction extends Expression {
    /*************
     * VARIABLES *
     *************/

    private Expression expression1;
    private Expression expression2;

    /****************
     * CONSTRUCTORS *
     ****************/

    public Conjunction(Expression e1, Expression e2) {
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

    public String toString() {
        return "(" + this.expression1.toString() + " Λ " + this.expression2.toString() + ")";
    }
}
