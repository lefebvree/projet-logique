package view;

import model.Expression;

import javax.swing.*;
import java.util.ArrayList;

public class ExpressionComponent {

    private Expression expression;
    private JPanel     panel;
    private JLabel     name;

    public ExpressionComponent (Expression e) {
        this.expression = e;

        this.panel = new JPanel();
        this.name  = new JLabel(e.toString());
    }

    public ArrayList<ExpressionComponent> split () {

        ArrayList<ExpressionComponent> subexpressions = new ArrayList<>();
        return subexpressions;

    }

    public JPanel getPanel() {
        return panel;
    }
}
