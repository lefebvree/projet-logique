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

    private Expression expression;
    private JPanel     panel, bottompanel;
    private JLabel     name;
    private ArrayList<ExpressionComponent> subExpressionComponent;

    private static Random rand = new Random();

    private static final String colorlist[] = {"#2ecc71","#27ae60","#16a085","#1abc9c","#3498db","#2980b9","#34495e","#2c3e50","#8e44ad","#9b59b6","#e74c3c","#c0392b","#d35400","#e67e22","#f39c12","#f1c40f"};

    public ExpressionComponent (Expression e) {
        this.expression = e;

        this.panel = new JPanel();
        this.panel.setLayout(new BorderLayout());
        this.panel.setBackground(getRandomColor());

        this.name  = new JLabel(e.toString(), SwingConstants.CENTER);
        this.name.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                solveExpression();
            }
        });
        this.name.setForeground(Color.WHITE);
        this.name.setFont(new Font("Monospaced", Font.PLAIN, 20));
        this.name.setBorder(new EmptyBorder(10, 10, 10, 10));

        this.panel.add(this.name, BorderLayout.PAGE_START);

        subExpressionComponent = new ArrayList<>();
    }

    public void solveExpression() {

        if (this.expression.hasSubExpression()) {

            Expression e = this.expression.solveExpression();
            ArrayList<Expression> subExpression;

            this.bottompanel = new JPanel();

            this.bottompanel.removeAll();
            this.subExpressionComponent.clear();

            switch (e.subExpressionNumber()) {

                case 2:

                    String expressiontype = e.getClass().getSimpleName();
                    GridLayout layout = null;

                    System.out.println(expressiontype);

                    switch (expressiontype) {
                        case "Conjunction":

                            layout = new GridLayout(2, 1);

                            break;

                        case "Disjunction":
                        case "Implication":

                            layout = new GridLayout(1, 2);

                            break;
                    }

                    subExpression = e.getSubExpressions();
                    //System.out.println(e);
                    //System.out.println(subExpression);

                    System.out.println(subExpression);
                    this.subExpressionComponent.add(new ExpressionComponent(subExpression.get(0)));
                    this.subExpressionComponent.add(new ExpressionComponent(subExpression.get(1)));


                    this.bottompanel.setLayout(layout);

                    this.bottompanel.add(this.subExpressionComponent.get(0).getPanel());
                    this.bottompanel.add(this.subExpressionComponent.get(1).getPanel());

                    break;

                case 1:

                    this.subExpressionComponent.add(new ExpressionComponent(e));

                    this.bottompanel.add(this.subExpressionComponent.get(0).getPanel());

                    break;
            }

            this.panel.add(this.bottompanel, BorderLayout.CENTER);

            this.bottompanel.validate();
            this.bottompanel.repaint();

            this.panel.validate();
            this.panel.repaint();
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
