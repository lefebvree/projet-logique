package model;

import model.expression.*;

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
            case '!':
                exp = new Negation(createExpression(e.substring(indexChar+1)));
                break;
            case '(':
                switch (e.charAt(indexChar+2)) {
                    case '&':
                        exp = new Conjunction(createExpression(e.substring(indexChar+1, indexChar+2)), createExpression(e.substring(indexChar+3, indexChar+4)));
                        break;
                    case '|':
                        exp = new Disjunction(createExpression(e.substring(indexChar+1, indexChar+2)), createExpression(e.substring(indexChar+3, indexChar+4)));
                        break;
                    case '>':
                        exp = new Implication(createExpression(e.substring(indexChar+1, indexChar+2)), createExpression(e.substring(indexChar+3, indexChar+4)));
                        break;
                }
                break;
            default:
                exp = new Litteral(currentChar);
                break;
        }

        return exp;
    }
}
