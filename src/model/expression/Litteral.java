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
        ArrayList<Expression> a = new ArrayList<>();
        return a;
    }
}
