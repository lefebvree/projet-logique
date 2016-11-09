package model;

public class Expression {
    private char name;
    private Expression firstElement;
    private Expression secondElement;
    private char operator;
    private boolean negative;

    private static int stringProgression;

    public Expression(char name, boolean negative) {
        this.name = name;
        this.firstElement = null;
        this.secondElement = null;
        this.negative = negative;
    }

    public Expression(String expression, boolean negative, int index) {
        this.stringProgression = index;
        StringBuffer buff = new StringBuffer(expression);
        if(buff.charAt(this.stringProgression) == '!') {
            this.stringProgression++;
            if(buff.charAt(this.stringProgression) == '(') {
                this.stringProgression++;
                this.firstElement = new Expression(buff.toString(), true, this.stringProgression);
            } else {
                this.firstElement = new Expression(buff.charAt(this.stringProgression), true);
                this.stringProgression++;
            }
        } else {
            if(buff.charAt(this.stringProgression) == '(') {
                this.stringProgression++;
                this.firstElement = new Expression(buff.toString(), false, this.stringProgression);
            } else {
                this.firstElement = new Expression(buff.charAt(this.stringProgression), false);
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
                this.secondElement = new Expression(buff.toString(), true, this.stringProgression);
            } else {
                this.secondElement = new Expression(buff.charAt(this.stringProgression), true);
                this.stringProgression++;
            }
        } else {
            if(buff.charAt(this.stringProgression) == '(') {
                this.stringProgression++;
                this.secondElement = new Expression(buff.toString(), false, this.stringProgression);
            } else {
                this.secondElement = new Expression(buff.charAt(this.stringProgression), false);
                this.stringProgression++;
                this.stringProgression++;
            }
        }
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
