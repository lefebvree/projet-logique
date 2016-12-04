package view;

import model.Expression;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Window {

    private JFrame frame;
    private ExpressionComponent firstexpression;

    public Window (Expression e) {

        this.frame = new JFrame();
        this.frame.setTitle("The Logic Game");
        this.frame.setLocation(50, 50);
        this.frame.setSize(new Dimension(800, 600));
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception exc) {
            System.err.println("Could not retrieve System look and feel");
            exc.printStackTrace();
        }

        ArrayList<Expression> exp = new ArrayList<>();
        exp.add(e);

        this.firstexpression = new ExpressionComponent(exp);

        JScrollPane scrollPane = new JScrollPane(this.firstexpression.getPanel());
        this.frame.add(scrollPane);

        this.frame.setVisible(true);
    }
}
