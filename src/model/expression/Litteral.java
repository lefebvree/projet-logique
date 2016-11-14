package model.expression;

import model.Expression;

import java.util.ArrayList;

public class Litteral extends Expression {
    /*************
     * VARIABLES *
     *************/

    private char name;

    /****************
     * CONSTRUCTORS *
     ****************/

    public Litteral(char n) {
        this.name = n;
    }

    /***********
     * METHODS *
     ***********/

    public int subExpressionNumber() {
        return 0;
    }

    public ArrayList<Expression> getSubExpressions() {
        return null;
    }

    public Expression solveExpression() {
        return this;
    }

    public String toString() {
        return "" + this.name;
    }
}
