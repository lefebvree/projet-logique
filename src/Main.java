import model.Expression;

import java.util.ArrayList;

import static model.Expression.createExpression;

public class Main {
    public static void main(String[] args) {
//        Expression e1 = Expression.createExpression("( p > (( p > q ) > q ))");
//        System.out.println(e1);
//
//        Expression e2 = Expression.createExpression("( p > ( !q > !( p > q ) ))");
//        System.out.println(e2);
//
//        Expression e3 = Expression.createExpression("(( !p > !q ) > ( q > p ))");
//        System.out.println(e3);
//
//        Expression e4 = Expression.createExpression("((( p & ( p > q)) & (( p > q ) > r )) > (( p & q ) & r ))");
//        System.out.println(e4);
//
//        Expression e5 = Expression.createExpression("(( p > q ) > (( q > r ) > ( p > r )))");
//        System.out.println(e5);
//
//        Expression e6 = Expression.createExpression("(((( s & p ) > ( q & r )) & ( !r | !q ) & p ) > !s )");
//        System.out.println(e6);
//
//        Expression e7 = Expression.createExpression("((((( p > q ) & (( r & s ) > p )) & ( t > r)) & ( s & t )) > q )");
//        System.out.println(e7);
//
//        Expression e8 = Expression.createExpression("(p & !!q)");
//        System.out.println(e8);

        System.out.println("\t# Creation e");
        Expression e = createExpression("(p > ((p > q) > q))");
        System.out.println("\t# e cree");
        System.out.println("\t# e = " + e);
        System.out.println("\t# Appel de solveExpression pour creer e2");
        Expression e2 = e.solveExpression();
        System.out.println("\t# e2 cree");
        System.out.println("\t# e2 = " + e2);
        System.out.println("\t# Appel de getSubExpressions sur e2");
        ArrayList<Expression> subExpressions = e2.getSubExpressions();
        System.out.println("\t# subExpressions cree");
        System.out.println("\t# Affichage subExpressions");
        for(int i = 0; i < subExpressions.size(); i++) {
            System.out.println("subExpression = " + subExpressions.get(i).toString());
        }
    }
}
