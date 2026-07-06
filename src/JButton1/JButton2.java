package JButton1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class JButton2 implements ActionListener {
    static JButton button = new JButton();
    static JButton button1 = new JButton();
    static JButton button2 = new JButton();
    static JButton button3 = new JButton();
    static JButton button4 = new JButton();
    static JButton button5 = new JButton();
    static JButton button6 = new JButton();
    static JButton button7 = new JButton();
    static JButton button8 = new JButton();
    static JButton button9 = new JButton();
    static JButton button10 = new JButton();
    static JButton button11 = new JButton();

    public static void main(String[] args) {
        JFrame frame = new JFrame();

        JLabel label = new JLabel();
        JLabel label1 = new JLabel();
        JLabel label2 = new JLabel();

        JPanel panel = new JPanel();
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();

        ImageIcon image = new ImageIcon("supermarket1.png");
        ImageIcon image1 = new ImageIcon("supermarket1.png");

        // 🔹 Reduced top panels
        panel.setBackground(Color.black);
        panel.setBounds(0, 0, 200, 150);

        panel1.setBackground(new Color(0x00243e));
        panel1.setBounds(200, 0, 300, 150);

        panel2.setBackground(new Color(0x00243e));
        panel2.setBounds(500, 0, 300, 150);

        label.setIcon(image1);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);

        label1.setText("    \uD83C\uDFE6  GHUMA SUPERMARKET BILLING");
        label1.setFont(new Font("Segoe UI Emoji", Font.BOLD, 16));
        label1.setForeground(Color.WHITE);
        label1.setHorizontalAlignment(JLabel.CENTER);

        panel.setLayout(new BorderLayout());
        panel1.setLayout(new BorderLayout());

        panel2.setLayout(new BorderLayout());

        label2.setText(
            "<html><center>" +
            "\uD83D\uDCCD LOCATION!:<br>" +
            "Ghuma SuperMarket,<br>" +
            "Ghuma BRTS Road,<br>" +
            "Contact: 8401331819<br>" +
            "Pincode: 380058" +
            "</center></html>"
        );
        label2.setForeground(Color.white);
        label2.setFont(new Font("Segoe UI Emoji", Font.BOLD, 14));
        label2.setHorizontalAlignment(JLabel.CENTER);

        JPanel panel3 = new JPanel(new BorderLayout());
        JLabel label3 = new JLabel("Insertion Panel →", JLabel.CENTER);
        panel3.setBackground(new Color(0xCCCCCC));
        panel3.setBounds(0, 150, 200, 70);
        label3.setFont(new Font("Segoe UI Emoji", Font.BOLD, 16));

        frame.setSize(800, 600);
        frame.setIconImage(image.getImage());
        frame.setTitle("Ghuma Super Market");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        frame.add(label);
        frame.add(panel);
        panel.add(label);

        // 🔹 Insertion buttons
        button.setBounds(200, 150, 200, 70);
        button1.setBounds(400, 150, 200, 70);
        button2.setBounds(600, 150, 200, 70);

        button.setBackground(Color.WHITE);
        button1.setBackground(Color.WHITE);
        button2.setBackground(Color.WHITE);

        button.setText("  \uD83D\uDC64 CUSTOMER");
        button1.setText("  \uD83D\uDCE6 PRODUCT");
        button2.setText("  \uD83D\uDCDD ORDER");

        button.setFont(new Font("Segoe UI Emoji", Font.BOLD, 13));
        button1.setFont(new Font("Segoe UI Emoji", Font.BOLD, 13));
        button2.setFont(new Font("Segoe UI Emoji", Font.BOLD, 13));

        button.addActionListener(new JButton2());
        button1.addActionListener(new JButton2());
        button2.addActionListener(new JButton2());

        button.setFocusable(false);
        button1.setFocusable(false);
        button2.setFocusable(false);

        button.setBorder(BorderFactory.createEtchedBorder());
        button1.setBorder(BorderFactory.createEtchedBorder());
        button2.setBorder(BorderFactory.createEtchedBorder());

        frame.add(panel1);
        panel1.add(label1);

        frame.add(panel2);
        panel2.add(label2);

        frame.add(panel3);
        panel3.add(label3);

        frame.add(button);
        frame.add(button1);
        frame.add(button2);

        // 🔹 Updation Panel
        JPanel panel4 = new JPanel(new BorderLayout());
        JLabel label4 = new JLabel("Updation Panel →", JLabel.CENTER);
        label4.setFont(new Font("Segoe UI Emoji", Font.BOLD, 16));
        panel4.setBackground(new Color(0xCCCCCC));
        panel4.setBounds(0, 230, 200, 70);

        button3.setBounds(200, 230, 200, 70);
        button4.setBounds(400, 230, 200, 70);
        button5.setBounds(600, 230, 200, 70);

        button3.setBackground(Color.WHITE);
        button4.setBackground(Color.WHITE);
        button5.setBackground(Color.WHITE);

        button3.setText("  \uD83D\uDC64 CUSTOMER");
        button4.setText("  \uD83D\uDCE6 PRODUCT");
        button5.setText("  \uD83D\uDCDD ORDER");

        button3.setFont(new Font("Segoe UI Emoji", Font.BOLD, 13));
        button4.setFont(new Font("Segoe UI Emoji", Font.BOLD, 13));
        button5.setFont(new Font("Segoe UI Emoji", Font.BOLD, 13));

        button3.setBorder(BorderFactory.createEtchedBorder());
        button4.setBorder(BorderFactory.createEtchedBorder());
        button5.setBorder(BorderFactory.createEtchedBorder());

        button3.addActionListener(new JButton2());
        button4.addActionListener(new JButton2());
        button5.addActionListener(new JButton2());

        frame.add(button3);
        frame.add(button4);
        frame.add(button5);
        frame.add(panel4);
        panel4.add(label4);

        // 🔹 Deletion Panel
        JPanel panel5 = new JPanel(new BorderLayout());
        JLabel label5 = new JLabel("Deletion Panel →", JLabel.CENTER);
        label5.setFont(new Font("Segoe UI Emoji", Font.BOLD, 16));
        panel5.setBounds(0, 310, 200, 70);
        panel5.setBackground(new Color(0xCCCCCC));

        button6.setBounds(200, 310, 200, 70);
        button7.setBounds(400, 310, 200, 70);
        button8.setBounds(600, 310, 200, 70);

        button6.setBackground(Color.WHITE);
        button7.setBackground(Color.WHITE);
        button8.setBackground(Color.WHITE);

        button6.setText("  \uD83D\uDC64 CUSTOMER");
        button7.setText("  \uD83D\uDCE6 PRODUCT");
        button8.setText("  \uD83D\uDCDD ORDER");

        button6.setFont(new Font("Segoe UI Emoji", Font.BOLD, 13));
        button7.setFont(new Font("Segoe UI Emoji", Font.BOLD, 13));
        button8.setFont(new Font("Segoe UI Emoji", Font.BOLD, 13));

        button6.setBorder(BorderFactory.createEtchedBorder());
        button7.setBorder(BorderFactory.createEtchedBorder());
        button8.setBorder(BorderFactory.createEtchedBorder());

        button6.addActionListener(new JButton2());
        button7.addActionListener(new JButton2());
        button8.addActionListener(new JButton2());

        frame.add(panel5);
        panel5.add(label5);
        frame.add(button6);
        frame.add(button7);
        frame.add(button8);

        // 🔹 Billing Panel
        JPanel panel6 = new JPanel(new BorderLayout());
        JLabel label6 = new JLabel("Billing Panel →", JLabel.CENTER);
        label6.setFont(new Font("Segoe UI Emoji", Font.BOLD, 16));
        panel6.setBounds(0, 390, 200, 70);
        panel6.setBackground(new Color(0xCCCCCC));

        button9.setBounds(200, 390, 200, 70);
        button10.setBounds(400, 390, 200, 70);
        button11.setBounds(600, 390, 200, 70);

        button9.setBackground(Color.WHITE);
        button10.setBackground(Color.WHITE);
        button11.setBackground(Color.WHITE);

        button9.setText("  \uD83D\uDCC5 QUEUE BILLING");
        button10.setText("  \uD83D\uDCCB TOTAL BILLING");
        button11.setText("  \uD83D\uDD22 CALCULATOR");

        button9.setFont(new Font("Segoe UI Emoji", Font.BOLD, 13));
        button10.setFont(new Font("Segoe UI Emoji", Font.BOLD, 13));
        button11.setFont(new Font("Segoe UI Emoji", Font.BOLD, 13));

        button9.setBorder(BorderFactory.createEtchedBorder());
        button10.setBorder(BorderFactory.createEtchedBorder());
        button11.setBorder(BorderFactory.createEtchedBorder());

        button9.addActionListener(new JButton2());
        button10.addActionListener(new JButton2());
        button11.addActionListener(new JButton2());

        frame.add(panel6);
        panel6.add(label6);
        frame.add(button9);
        frame.add(button10);
        frame.add(button11);

        frame.setVisible(true);
        button.setFocusable(false);
        button1.setFocusable(false);
        button2.setFocusable(false);
        button3.setFocusable(false);
        button4.setFocusable(false);
        button5.setFocusable(false);
        button6.setFocusable(false);
        button7.setFocusable(false);
        button8.setFocusable(false);
        button9.setFocusable(false);
        button10.setFocusable(false);
        button11.setFocusable(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // 🔹 SAME FUNCTIONS (UNCHANGED)
        if (e.getSource() == button) {
            System.out.println("Customer");
            JFrame jb = JButtonCustomer.frame;
            JButtonCustomer.main(null);
            jb.setVisible(true);
        }
        if (e.getSource() == button1) {
            System.out.println("Product");
            new InsertProduct().display();
        }
        if (e.getSource() == button2) {
            System.out.println("Order");
            new InsertOrders();
        }
        if (e.getSource() == button3) {
            System.out.println("U_CUSTOMER");
            new Update_Customer().display();
        }
        if (e.getSource() == button4) {
            System.out.println("U_PRODUCT");
            try { new Update_Product(); } catch (Exception ex) {}
        }
        if (e.getSource() == button5) {
            System.out.println("U_ORDER");
            try { new Update_Order(); } catch (Exception ex) {}
        }
        if (e.getSource() == button6) {
            System.out.println("D_CUSTOMER");
            new delete_customer();
        }
        if (e.getSource() == button7) {
            System.out.println("D_PRODUCT");
            new Delete_Product();
        }
        if (e.getSource() == button8) {
            System.out.println("D_ORDER");
            new Delete_Order();
        }
        if (e.getSource() == button9) {
            System.out.println("Queue Billing");
            new Queue_Billing();
        }
        if (e.getSource() == button10) {
            System.out.println("TOTAL BILLING");
            new BillingPanel();
        }
        if (e.getSource() == button11) {
            System.out.println("Clicked Calculator");
            new CalculatorWithHistory();
        }
    }
}