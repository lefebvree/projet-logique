package model.expression;

import model.Expression;

import java.util.ArrayList;

public class Disjunction extends Expression {
    /***********
     * METHODS *
     ***********/

    public int subExpressionNumber() {
        return 0;
    }

    public ArrayList<Expression> getSubExpressions() {
        ArrayList<Expression> a = new ArrayList<>();
        return a;
    }
}
