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
        int endIndex = 0;
        int level = 0;

        // Remove spaces
        e = e.replaceAll("\\s+","");

        Expression exp = null;

        char currentChar = e.charAt(0);
        int  indexChar = 0;

        switch (currentChar) {
            case '!':
                endIndex = indexChar+1;
                level = 0;
                do {
                    if(e.charAt(endIndex) == '(') {
                        level++;
                    } else if(e.charAt(endIndex) == ')') {
                        level--;
                    }
                    endIndex++;
                } while(level != 0);
                exp = new Negation(createExpression(e.substring(indexChar+1, endIndex)));
                break;
            case '(':
                endIndex = indexChar+1;
                level = 0;
                do {
                    if(e.charAt(endIndex) == '(') {
                        level++;
                    } else if(e.charAt(endIndex) == ')') {
                        level--;
                    }
                    endIndex++;
                } while(level != 0);

                int indexOperator = endIndex;
                endIndex++;

                if(e.charAt(endIndex) == '!') {
                    endIndex++;
                }

                level = 1;
                do {
                    if(e.charAt(endIndex) == '(') {
                        level++;
                    } else if(e.charAt(endIndex) == ')') {
                        level--;
                    }
                    endIndex++;
                } while(level != 0);
                switch (e.charAt(indexOperator)) {
                    case '&':
                        exp = new Conjunction(createExpression(e.substring(indexChar+1, indexOperator)), createExpression(e.substring(indexOperator+1, endIndex)));
                        break;
                    case '|':
                        exp = new Disjunction(createExpression(e.substring(indexChar+1, indexOperator)), createExpression(e.substring(indexOperator+1, endIndex)));
                        break;
                    case '>':
                        exp = new Implication(createExpression(e.substring(indexChar+1, indexOperator)), createExpression(e.substring(indexOperator+1, endIndex)));
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
