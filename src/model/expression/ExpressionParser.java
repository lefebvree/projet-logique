package model.expression;

import model.Expression;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class ExpressionParser {

    private final static String filename = "ressources/expressions";

    private ArrayList<String> expressions;

    public ExpressionParser() {

        Scanner in;
        this.expressions = new ArrayList<>();

        try {
            in = new Scanner(new FileReader(filename));
        } catch (FileNotFoundException e) {
            System.err.println("File " + filename + " not found !");
            e.printStackTrace();

            return;
        }

        while(in.hasNext()) {
            this.expressions.add(in.nextLine());
        }
    }

    public Expression getExpression(int i) {
        return Expression.createExpression(this.expressions.get(i));

    }

    public Expression getRandomExpression() {
        Random rand = new Random();
        return getExpression(rand.nextInt(this.expressions.size()));
    }

    public String[] getExpressionsList () {
        String[] ex = new String[this.expressions.size()];

        for (int i = 0; i < this.expressions.size(); i++) {
            String ss = this.expressions.get(i);
            String a;
            try {
                Expression se = Expression.createExpression(ss);
                a = se.toString();
            } catch (Exception e) {
                a = "Exp " + i + " ne marche pas";
            }

            ex[i] = a;
        }
        return ex;
    }
}
