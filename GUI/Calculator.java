package PracticeProblem6;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Calculator extends JFrame {
    private JTextField textField;

    public Calculator() {
        setSize(400, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Add the text field to the top of the frame
        textField = new JTextField();
        textField.setPreferredSize(new Dimension(0, 50));
        add(textField, BorderLayout.PAGE_START);

        // Create the button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 3, 5, 5));

        // Add the number buttons
        for (int i = 1; i <= 9; i++) {
            JButton button = new JButton(Integer.toString(i));
            button.addActionListener(e -> textField.setText(textField.getText() + button.getText()));
            buttonPanel.add(button);
        }

        // Add the "C" button
        JButton clearButton = new JButton("C");
        clearButton.addActionListener(e -> textField.setText(""));
        buttonPanel.add(clearButton);

        // Add the "0" button
        JButton zeroButton = new JButton("0");
        zeroButton.addActionListener(e -> textField.setText(textField.getText() + "0"));
        buttonPanel.add(zeroButton);

        // Add the "+" button
        JButton plusButton = new JButton("+");
        plusButton.addActionListener(e -> textField.setText(textField.getText() + "+"));
        buttonPanel.add(plusButton);

        // Add the button panel to the center of the frame
        add(buttonPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    public static void main(String[] args) {
        new Calculator();
    }
}
