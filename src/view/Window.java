package view;

import model.Expression;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Window {

    private JFrame frame;
    private JPanel gamepanel;

    public Window (Expression e) {

        this.frame = new JFrame();
        this.frame.setTitle("The Logic Game");
        this.frame.setLocation(50, 50);
        this.frame.setSize(new Dimension(1000, 600));
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception exc) {
            System.err.println("Could not retrieve System look and feel");
            exc.printStackTrace();
        }

        this.gamepanel = new JPanel();
        JScrollPane scrollPane = new JScrollPane(this.gamepanel);
        this.frame.add(scrollPane);
        this.frame.setBackground(Color.decode("#2c3e50"));

        launchGame(e);

        this.frame.setVisible(true);
    }

    private void launchGame (Expression e) {

        ArrayList<Expression> exp = new ArrayList<>();
        exp.add(e);

        ExpressionComponent firstexpression = new ExpressionComponent(exp, this.frame);
        this.gamepanel.add(firstexpression.getPanel());
    }
}
