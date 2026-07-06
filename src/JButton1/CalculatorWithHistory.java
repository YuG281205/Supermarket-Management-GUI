package JButton1;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CalculatorWithHistory extends JFrame implements ActionListener {

    // GUI Components
    JTextField display;
    JTextArea historyArea;
    JButton[] numberButtons = new JButton[10];
    JButton addButton, subButton, mulButton, divButton, decButton;
    JButton eqButton, clrButton, delButton;
    JPanel panel;

    // Operation variables
    double num1 = 0, num2 = 0, result = 0;
    char operator;
    StringBuilder history;

    // Constructor
    public CalculatorWithHistory() {
        setTitle("Calculator with History");
        setSize(400, 630);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // Display field
        display = new JTextField();
        display.setBounds(30, 25, 320, 50);
        display.setFont(new Font("Arial", Font.BOLD, 24));
        display.setEditable(true);
        add(display);

        // History area
        history = new StringBuilder();
        historyArea = new JTextArea();
        historyArea.setEditable(false);
        historyArea.setFont(new Font("Arial", Font.PLAIN, 14));
        JScrollPane scroll = new JScrollPane(historyArea);
        scroll.setBounds(30, 460, 320, 120);
        add(scroll);

        // Buttons
        addButton = new JButton("+");
        subButton = new JButton("-");
        mulButton = new JButton("*");
        divButton = new JButton("/");
        decButton = new JButton(".");
        eqButton = new JButton("=");
        clrButton = new JButton("C");
        delButton = new JButton("Del");

        // Number buttons 0–9
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].addActionListener(this);
        }

        // Function buttons
        JButton[] funcButtons = { addButton, subButton, mulButton, divButton, decButton, eqButton, clrButton, delButton };
        for (JButton btn : funcButtons) {
            btn.addActionListener(this);
        }

        // Layout panel
        panel = new JPanel();
        panel.setBounds(30, 100, 320, 300);
        panel.setLayout(new GridLayout(4, 4, 10, 10));

        panel.add(numberButtons[1]);
        panel.add(numberButtons[2]);
        panel.add(numberButtons[3]);
        panel.add(addButton);
        panel.add(numberButtons[4]);
        panel.add(numberButtons[5]);
        panel.add(numberButtons[6]);
        panel.add(subButton);
        panel.add(numberButtons[7]);
        panel.add(numberButtons[8]);
        panel.add(numberButtons[9]);
        panel.add(mulButton);
        panel.add(decButton);
        panel.add(numberButtons[0]);
        panel.add(eqButton);
        panel.add(divButton);

        clrButton.setBounds(30, 410, 150, 40);
        delButton.setBounds(200, 410, 150, 40);

        add(clrButton);
        add(delButton);
        add(panel);
        setVisible(true);
    }

    // Event handling
    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 10; i++) {
            if (e.getSource() == numberButtons[i]) {
                display.setText(display.getText().concat(String.valueOf(i)));
                return;
            }
        }

        if (e.getSource() == decButton) {
            display.setText(display.getText().concat("."));
        }

        if (e.getSource() == addButton) {
            num1 = Double.parseDouble(display.getText());
            operator = '+';
            display.setText("");
        }

        if (e.getSource() == subButton) {
            num1 = Double.parseDouble(display.getText());
            operator = '-';
            display.setText("");
        }

        if (e.getSource() == mulButton) {
            num1 = Double.parseDouble(display.getText());
            operator = '*';
            display.setText("");
        }

        if (e.getSource() == divButton) {
            num1 = Double.parseDouble(display.getText());
            operator = '/';
            display.setText("");
        }

        if (e.getSource() == eqButton) {
            try {
                num2 = Double.parseDouble(display.getText());

                switch (operator) {
                    case '+': result = num1 + num2; break;
                    case '-': result = num1 - num2; break;
                    case '*': result = num1 * num2; break;
                    case '/': 
                        if (num2 != 0) result = num1 / num2;
                        else {
                            display.setText("Error");
                            history.append("Error: Divide by zero\n");
                            historyArea.setText(history.toString());
                            return;
                        }
                        break;
                }

                String operation = num1 + " " + operator + " " + num2 + " = " + result + "\n";
                history.append(operation);
                historyArea.setText(history.toString());

                display.setText(String.valueOf(result));
                num1 = result;
            } catch (Exception ex) {
                display.setText("Error");
            }
        }

        if (e.getSource() == clrButton) {
            display.setText("");
            history.setLength(0);
            historyArea.setText("");
        }

        if (e.getSource() == delButton) {
            String text = display.getText();
            if (!text.isEmpty()) {
                display.setText(text.substring(0, text.length() - 1));
            }
        }
    }

    // Main method
    public static void main(String[] args) {
        new CalculatorWithHistory();
    }
}