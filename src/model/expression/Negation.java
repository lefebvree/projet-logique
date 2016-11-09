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

    public ArrayList<Expression> getSubExpressions() {

        ArrayList<Expression> a = new ArrayList<>();
        a.add(this.expression);

        return a;
    }

    public String toString() {
        return "!" + this.expression;
    }
}
