package view;

import model.Expression;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;

public class ExpressionComponent {

    private ArrayList<Expression> expressions;
    private JPanel panel, toppanel, bottompanel;

    private boolean expanded;

    private static Random rand = new Random();

    private static final String colorlist[] = {"#2ecc71","#27ae60","#16a085","#1abc9c","#3498db","#2980b9","#34495e","#2c3e50","#8e44ad","#9b59b6","#e74c3c","#c0392b","#d35400","#e67e22","#f39c12","#f1c40f"};

    public ExpressionComponent (ArrayList<Expression> exps) {
        this.expressions = exps;
        this.expanded = false;

        this.panel = new JPanel();
        this.panel.setLayout(new BoxLayout(this.panel, BoxLayout.PAGE_AXIS));
        //this.panel.setBackground(getRandomColor());

        this.toppanel = new JPanel();
        this.toppanel.setLayout(new BoxLayout(this.toppanel, BoxLayout.Y_AXIS));

        for (int i = 0; i < this.expressions.size(); i++) {

            final int index = i;

            Expression expression = this.expressions.get(i);
            System.out.println("e: " + expression);

            JPanel expressionpanel = new JPanel();
            //expressionpanel.setLayout(new BoxLayout(expressionpanel, BoxLayout.Y_AXIS));
            JLabel expressionname  = new JLabel(expression.toString(), SwingConstants.CENTER);

            expressionpanel.setBackground(getRandomColor());
            expressionpanel.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    solveExpression(index);
                }
            });
            expressionname.setForeground(Color.WHITE);
            expressionname.setFont(new Font("Monospaced", Font.PLAIN, 20));
            expressionname.setBorder(new EmptyBorder(10, 10, 10, 10));

            expressionpanel.add(expressionname);
            this.toppanel.add(expressionpanel);
        }

        this.panel.add(this.toppanel);
    }

    public void solveExpression(int i) {

        if (!this.expanded) {
            this.expanded = true;

            Expression expression = this.expressions.get(i);

            ArrayList<Expression> subExpression;

            Expression newexpression;
            String expressiontype;

            this.bottompanel = new JPanel();

            if (expression.hasSubExpression()) {

                newexpression = expression.solveExpression();

                expressiontype = newexpression.getClass().getSimpleName();

                System.out.println(expressiontype);

                switch (expressiontype) {

                    case "Conjunction":

                        subExpression = newexpression.getSubExpressions();

                        ArrayList<Expression> subexp = new ArrayList<>(this.expressions);
                        subexp.remove(i);

                        subexp.add(0, subExpression.get(0));
                        subexp.add(0, subExpression.get(1));

                        ExpressionComponent expcomp = new ExpressionComponent(subexp);

                        this.bottompanel.add(expcomp.getPanel());

                        break;

                    case "Disjunction":
                    case "Implication":

                        subExpression = newexpression.getSubExpressions();

                        ArrayList<ExpressionComponent> subExpressionComponent = new ArrayList<>();

                        ArrayList<Expression> subexp1 = new ArrayList<>(this.expressions);
                        ArrayList<Expression> subexp2 = new ArrayList<>(this.expressions);
                        subexp1.remove(i);
                        subexp2.remove(i);

                        subexp1.add(0, subExpression.get(0));
                        subexp2.add(0, subExpression.get(1));

                        subExpressionComponent.add(new ExpressionComponent(subexp1));
                        subExpressionComponent.add(new ExpressionComponent(subexp2));

                        this.bottompanel.setLayout(new GridLayout(1, 2));

                        this.bottompanel.add(subExpressionComponent.get(0).getPanel());
                        this.bottompanel.add(subExpressionComponent.get(1).getPanel());

                        break;

                    default:
                        return;
                }

                this.panel.add(this.bottompanel);

                this.bottompanel.validate();
                this.bottompanel.repaint();

                this.panel.validate();
                this.panel.repaint();
            }
        }
    }

    public JPanel getPanel() {
        return panel;
    }

    public static Color getRandomColor() {
        String color = colorlist[rand.nextInt(colorlist.length)];

        return Color.decode(color);
    }
}
