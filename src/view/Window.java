package view;

import model.Expression;
import model.expression.ExpressionParser;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Window {

    private JFrame frame;
    private JPanel gamepanel;

    private int openexpressioncount = 0;
    private boolean isGameEnded = false;

    public Window (ExpressionParser expressions) {

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

        JPanel toppanel = new JPanel();
        toppanel.setLayout(new FlowLayout());

        JComboBox expression_list = new JComboBox(expressions.getExpressionsList());

        JButton launch = new JButton("LANCER");
        JButton random = new JButton("ALÉATOIRE");

        launch.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int index = expression_list.getSelectedIndex();
                launchGame(expressions.getExpression(index));
            }
        });

        random.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                launchGame(expressions.getRandomExpression());
            }
        });

        toppanel.add(expression_list);
        toppanel.add(launch);
        toppanel.add(random);
        this.frame.add(toppanel, BorderLayout.NORTH);

        this.gamepanel = new JPanel();
        JScrollPane scrollPane = new JScrollPane(this.gamepanel);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        this.frame.add(scrollPane);
        this.gamepanel.setBackground(ExpressionComponent.bgcolor);

        this.frame.setVisible(true);
    }

    void incrementOpenExpressionCount() {
        this.openexpressioncount++;
    }

    void decrementOpenExpressionCount() {
        this.openexpressioncount--;

        if (this.openexpressioncount == 0) {
            this.isGameEnded = true;

            displayMessage("Bravo ! Tu es très fort !");
        }
    }

    void looser() {
        this.isGameEnded = true;

        displayMessage("Oulala ! Il faudrait revoir tes cours HLIN509 plus communément appele Logique des propositions !");
    }

    private void launchGame (Expression e) {

        this.gamepanel.removeAll();

        ArrayList<Expression> exp = new ArrayList<>();
        exp.add(e);

        this.isGameEnded = false;
        this.openexpressioncount = 1;

        ExpressionComponent firstexpression = new ExpressionComponent(exp, this);
        this.gamepanel.add(firstexpression.getPanel());

        this.gamepanel.validate();
        this.gamepanel.repaint();
    }

    boolean isGameEnded() {
        return isGameEnded;
    }

    JFrame getFrame() {
        return frame;
    }

    private static void displayMessage(String message) {
        JFrame messageWindow = new JFrame();
        messageWindow.setTitle("Information");
        messageWindow.setLocation(500, 500);

        JLabel label = new JLabel(message, SwingConstants.CENTER);
        label.setBorder(new EmptyBorder(15, 25, 15, 25));
        messageWindow.add(label);

        messageWindow.pack();
        messageWindow.setVisible(true);
    }
}
