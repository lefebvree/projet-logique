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
    private JFrame frame;

    private boolean expanded;

    private static Random rand = new Random();

    private static final String colorlist[] = {"#2ecc71","#27ae60","#16a085","#1abc9c","#3498db","#2980b9","#34495e","#2c3e50","#8e44ad","#9b59b6","#e74c3c","#c0392b","#d35400","#e67e22","#f39c12","#f1c40f"};
    private static int lastindex = 0;

    public ExpressionComponent (ArrayList<Expression> exps, JFrame f) {
        this.expressions = exps;
        this.expanded = false;
        this.frame = f;

        this.panel = new JPanel();
        this.panel.setLayout(new GridBagLayout());
        //this.panel.setLayout(new GridLayout(0, 1));
        this.panel.setBorder(new EmptyBorder(2, 2, 2, 2));

        this.toppanel = new JPanel();
        this.toppanel.setLayout(new GridLayout(0, 1));
        this.toppanel.setBorder(new EmptyBorder(0, 0, 15, 0));

        for (int i = 0; i < this.expressions.size(); i++) {

            final int index = i;

            Expression expression = this.expressions.get(i);

            JPanel expressionpanel = new JPanel();
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

        GridBagConstraints c1 = new GridBagConstraints();
        c1.gridy = 0;

        this.panel.add(this.toppanel, c1);

        this.bottompanel = new JPanel();
        this.bottompanel.setLayout(new GridLayout(1, 2));

        GridBagConstraints c2 = new GridBagConstraints();
        c2.gridy = 1;
        c2.weighty = 1;
        c2.anchor = GridBagConstraints.NORTH;
        c2.fill = GridBagConstraints.BOTH;

        //this.bottompanel.setBackground(getRandomColor());
        this.panel.add(this.bottompanel, c2);
    }

    public void solveExpression(int i) {

        if (!this.expanded) {

            Expression expression = this.expressions.get(i);

            ArrayList<Expression> subExpression;

            Expression newexpression;
            String expressiontype;

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

                        ExpressionComponent expcomp = new ExpressionComponent(subexp, this.frame);
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

                        subExpressionComponent.add(new ExpressionComponent(subexp1, this.frame));
                        subExpressionComponent.add(new ExpressionComponent(subexp2, this.frame));

                        this.bottompanel.add(subExpressionComponent.get(0).getPanel());
                        this.bottompanel.add(subExpressionComponent.get(1).getPanel());

                        break;

                    default:
                        return;
                }

                this.expanded = true;

                this.panel.validate();
                this.panel.repaint();

                SwingUtilities.updateComponentTreeUI(this.frame);
            }
        }
    }

    public JPanel getPanel() {
        return panel;
    }

    public static Color getRandomColor() {

        int index = rand.nextInt(colorlist.length);
        while (index == lastindex) index = rand.nextInt(colorlist.length);
        lastindex = index;

        String color = colorlist[index];

        return Color.decode(color);
    }
}
