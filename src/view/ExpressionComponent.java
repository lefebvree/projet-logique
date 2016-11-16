package view;

import model.Expression;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class ExpressionComponent {

    private Expression expression;
    private JPanel     panel, bottompanel;
    private JLabel     name;
    private ArrayList<ExpressionComponent> subExpressionComponent;

    public ExpressionComponent (Expression e) {
        this.expression = e;

        this.panel = new JPanel();
        this.panel.setLayout(new BorderLayout());

        this.name  = new JLabel(e.toString(), SwingConstants.CENTER);
        this.name.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                solveExpression();
            }
        });

        this.panel.add(this.name, BorderLayout.PAGE_START);

        subExpressionComponent = new ArrayList<>();
    }

    public void solveExpression() {

        if (this.expression.hasSubExpression()) {

            Expression e = this.expression.solveExpression();
            ArrayList<Expression> subExpression;

            this.bottompanel = new JPanel();

            switch (e.subExpressionNumber()) {

                case 2:

                    String expressiontype = e.getClass().getSimpleName();
                    GridLayout layout = null;

                    switch (expressiontype) {
                        case "Conjonction":

                            System.out.println("Conjonction");
                            layout = new GridLayout(2, 1);

                            break;

                        case "Disjunction":
                        case "Implication":

                            System.out.println("Disjunction / Implication");
                            layout = new GridLayout(1, 2);

                            break;
                    }

                    subExpression = e.getSubExpressions();
                    System.out.println(e);
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
}
