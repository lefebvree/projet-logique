package model;

import java.util.ArrayList;

public class Expression3 {
    /*************
     * VARIABLES *
     *************/

    private char name;

    private Expression3 firstElement;
    private char       operator;
    private Expression3 secondElement;
    private boolean    negative;

    private static int stringProgression;

    /****************
     * CONSTRUCTORS *
     ****************/

    public Expression3(char name, boolean negative) {
        this.name = name;
        this.firstElement = null;
        this.secondElement = null;
        this.negative = negative;
    }

    public Expression3(String expression, boolean negative, int index) {
        this.stringProgression = index;
        StringBuffer buff = new StringBuffer(expression);
        if(buff.charAt(this.stringProgression) == '!') {
            this.stringProgression++;
            if(buff.charAt(this.stringProgression) == '(') {
                this.stringProgression++;
                this.firstElement = new Expression3(buff.toString(), true, this.stringProgression);
            } else {
                this.firstElement = new Expression3(buff.charAt(this.stringProgression), true);
                this.stringProgression++;
            }
        } else {
            if(buff.charAt(this.stringProgression) == '(') {
                this.stringProgression++;
                this.firstElement = new Expression3(buff.toString(), false, this.stringProgression);
            } else {
                this.firstElement = new Expression3(buff.charAt(this.stringProgression), false);
                this.stringProgression++;
            }
        }

        this.operator = buff.charAt(this.stringProgression);
        this.stringProgression++;
        this.negative = negative;

        if(buff.charAt(this.stringProgression) == '!') {
            this.stringProgression++;
            if(buff.charAt(this.stringProgression) == '(') {
                this.stringProgression++;
                this.secondElement = new Expression3(buff.toString(), true, this.stringProgression);
            } else {
                this.secondElement = new Expression3(buff.charAt(this.stringProgression), true);
                this.stringProgression++;
            }
        } else {
            if(buff.charAt(this.stringProgression) == '(') {
                this.stringProgression++;
                this.secondElement = new Expression3(buff.toString(), false, this.stringProgression);
            } else {
                this.secondElement = new Expression3(buff.charAt(this.stringProgression), false);
                this.stringProgression++;
                this.stringProgression++;
            }
        }
    }

    /*********************
     * GETTERS & SETTERS *
     *********************/



    /***********
     * METHODS *
     ***********/

    public ArrayList<Expression3> split() {
        ArrayList<Expression3> array = new ArrayList<Expression3>();
        array.add(this.firstElement);
        array.add(this.secondElement);
        return array;
    }

    public String toString() {
        if(this.firstElement == null) {
            if(this.negative) {
                return "!" + this.name;
            } else {
                return "" + this.name;
            }
        } else {
            StringBuffer buff = new StringBuffer();
            if(this.negative) {
                buff.append("!");
            }
            buff.append("(");
            buff.append(this.firstElement.toString());
            switch(this.operator) {
                case '>':
                    buff.append(" -> ");
                    break;
                case '&':
                    buff.append(" ^ ");
                    break;
                case '|':
                    buff.append(" v ");
                    break;
            }
            buff.append(this.secondElement.toString());
            buff.append(")");
            return buff.toString();
        }
    }
}
