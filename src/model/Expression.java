package model;

import model.expression.Litteral;

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

        // Remove spaces
        e = e.replaceAll("\\s+","");

        Expression exp = null;

        char currentChar = e.charAt(0);
        int  indexChar = 0;

        switch (currentChar) {
            default:
                exp = new Litteral(currentChar);
                break;
        }

        return exp;
    }
}
