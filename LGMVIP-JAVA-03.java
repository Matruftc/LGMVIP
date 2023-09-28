import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Calculator extends JFrame implements ActionListener {
    // Components
    private JTextField display;
    private JButton[] numericButtons;
    private JButton[] operationButtons;
    private JButton equalsButton;
    private JButton clearButton;

    // Variables to store operands and operator
    private double num1, num2;
    private char operator;

    // Constructor
    public Calculator() {
        setTitle("Scientific Calculator");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Display
        display = new JTextField();
        display.setHorizontalAlignment(JTextField.RIGHT);
        add(display, BorderLayout.NORTH);

        // Numeric buttons
        JPanel numericPanel = new JPanel(new GridLayout(4, 3));
        numericButtons = new JButton[10];
        for (int i = 0; i < 10; i++) {
            numericButtons[i] = new JButton(String.valueOf(i));
            numericButtons[i].addActionListener(this);
            numericPanel.add(numericButtons[i]);
        }
        add(numericPanel, BorderLayout.CENTER);

        // Operation buttons
        JPanel operationPanel = new JPanel(new GridLayout(4, 1));
        operationButtons = new JButton[4];
        operationButtons[0] = new JButton("+");
        operationButtons[1] = new JButton("-");
        operationButtons[2] = new JButton("*");
        operationButtons[3] = new JButton("/");
        for (int i = 0; i < 4; i++) {
            operationButtons[i].addActionListener(this);
            operationPanel.add(operationButtons[i]);
        }
        add(operationPanel, BorderLayout.EAST);

        // Equals and Clear buttons
        equalsButton = new JButton("=");
        equalsButton.addActionListener(this);
        clearButton = new JButton("C");
        clearButton.addActionListener(this);

        JPanel bottomPanel = new JPanel(new GridLayout(1, 2));
        bottomPanel.add(equalsButton);
        bottomPanel.add(clearButton);
        add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        // Numeric buttons
        if (Character.isDigit(command.charAt(0))) {
            display.setText(display.getText() + command);
        }
        // Operator buttons
        else if (command.equals("+") || command.equals("-") || command.equals("*") || command.equals("/")) {
            num1 = Double.parseDouble(display.getText());
            operator = command.charAt(0);
            display.setText("");
        }
        // Equals button
        else if (command.equals("=")) {
            num2 = Double.parseDouble(display.getText());
            double result = calculate();
            display.setText(String.valueOf(result));
        }
        // Clear button
        else if (command.equals("C")) {
            display.setText("");
            num1 = 0;
            num2 = 0;
            operator = '\0';
        }
    }

    private double calculate() {
        switch (operator) {
            case '+':
                return num1 + num2;
            case '-':
                return num1 - num2;
            case '*':
                return num1 * num2;
            case '/':
                if (num2 != 0) {
                    return num1 / num2;
                } else {
                    JOptionPane.showMessageDialog(this, "Cannot divide by zero!");
                    return 0;
                }
            default:
                return 0;
        }
    }

    public static void main(String[] args) {
        new Calculator();
    }
}

 class main {
    public static void main (String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Calculator();
        });
    }
}
