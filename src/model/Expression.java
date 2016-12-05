package model;

import com.sun.xml.internal.ws.util.StringUtils;
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
    public abstract Expression solveExpression();

    public boolean hasSubExpression() {
        return this.subExpressionNumber() != 0;
    }

    public static Expression createExpression(String e) {
        // Remove spaces
        e = e.replaceAll("\\s+","");

        if (!hasCorrectParenthesisNumber(e)) return null;

        Expression exp = null;

        char currentChar = e.charAt(0);
        int  indexChar = 0;

        switch (currentChar) {
            case '!':
            case '¬':
                String subExpression = getNextExpression(e, indexChar + 1);

                exp = new Negation(createExpression(subExpression));
                break;

            case '(':
                indexChar++;

                String firstExpressionString = getNextExpression(e, indexChar);
                // Increment index position by first expression length
                indexChar += firstExpressionString.length();

                char operator = e.charAt(indexChar);
                indexChar++;

                String secondExpressionSting = getNextExpression(e, indexChar);

                Expression firstExpression  = createExpression(firstExpressionString);
                Expression secondExpression = createExpression(secondExpressionSting);

                switch (operator) {
                    case '&':
                    case 'Λ':
                        exp = new Conjunction(firstExpression, secondExpression);
                        break;
                    case '|':
                    case 'V':
                        exp = new Disjunction(firstExpression, secondExpression);
                        break;
                    case '>':
                    case '→':
                        exp = new Implication(firstExpression, secondExpression);
                        break;
                }
                break;
            default:
                exp = new Litteral(currentChar);
                break;
        }

        return exp;
    }

    private static String getNextExpression (String s, int index) {
        int position = index;
        int level = 0;

        do {
            while(s.charAt(position) == '!' || s.charAt(position) == '¬') {
                position++;
            }

            if(s.charAt(position) == '(') {
                level++;
            } else if(s.charAt(position) == ')') {
                level--;
            }
            position++;

        } while(level != 0);

        return s.substring(index, position);
    }

    private static boolean hasCorrectParenthesisNumber (String s) {
        int level = 0;

        for (int i = 0; i < s.length(); i++) {

            char c = s.charAt(i);

            if      (c == '(') level ++;
            else if (c == ')') level --;
        }

        return (level == 0);
    }
}
