package model;

import java.util.ArrayList;

public abstract class Expression {
    /*********************
     * GETTERS & SETTERS *
     *********************/

    public abstract ArrayList<Expression> getSubExpressions();

    /***********
     * METHODS *
     ***********/

    public abstract int subExpressionNumber();

    public boolean hasSubExpression() {
        return this.subExpressionNumber() != 0;
    }

    public static Expression createExpression(String e) {
        Expression exp = null;

        return exp;
    }
}
