package model.expression;

import model.Expression;

import java.util.ArrayList;

public class Implication extends Expression {
    /****************
     * CONSTRUCTORS *
     ****************/

    public Litteral(String expression) {

    }

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
