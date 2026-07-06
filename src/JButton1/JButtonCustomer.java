/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JButton1;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author yugjh
 */
public class JButtonCustomer implements ActionListener {
    private static final String url="jdbc:mysql://localhost:3306/?user=root";
    private static final String username="root";
    private static final String password="YuG@#2647";
    
    
      static JTextField text1=new JTextField();
       static JButton button1=new JButton();
       static JTextField text2=new JTextField();
       static JButton button2=new JButton();
       static JTextField text3=new JTextField();
       static JButton button3=new JButton();
        static JTextField text4=new JTextField();
       static JButton button4=new JButton();
       static JFrame frame=new JFrame();
    public static void main(String[] args) {
        
        ImageIcon image=new ImageIcon("yug.jpg");
        ImageIcon image1=new ImageIcon("customer_logo.jpg");
        JPanel panel=new JPanel();
        JLabel label=new JLabel();
        
        
        
        
       
        
        panel.setBackground(new Color(0x00243e));
        panel.setBounds(0, 0, 600, 200);
        label.setIcon(image1);
        
       JPanel panel1=new JPanel();
       JLabel label1=new JLabel();
     
        panel1.setBackground(new Color(0xCCCCCC));
        panel1.setBounds(0, 200, 120, 50);
        label1.setText("    CUSTOMER_ID ");
        panel1.setLayout(new BorderLayout());
        text1.setBounds(140,200, 120, 50);
        text1.setBorder(BorderFactory.createEtchedBorder());
        button1.setText("Submit");
        button1.setBounds(280,200, 120, 50);
        button1.setFocusable(false);
        button1.addActionListener(new JButtonCustomer());
        
        
        
          JPanel panel2=new JPanel();
       JLabel label2=new JLabel();
     
        panel2.setBackground(new Color(0xCCCCCC));
        panel2.setBounds(0, 265, 120, 50);
        label2.setText("   CUSTOMER_NAME ");
        panel2.setLayout(new BorderLayout());
        text2.setBounds(140,265, 120, 50);
        text2.setBorder(BorderFactory.createEtchedBorder());
        button2.setText("Submit");
        button2.setBounds(280,265, 120, 50);
        button2.setFocusable(false);
        button2.addActionListener(new JButtonCustomer());
        
        
           JPanel panel3=new JPanel();
       JLabel label3=new JLabel();
     
        panel3.setBackground(new Color(0xCCCCCC));
        panel3.setBounds(0, 335, 120, 50);
        label3.setText("   CONTACT NUMBER ");
        panel3.setLayout(new BorderLayout());
        text3.setBounds(140,335, 120, 50);
        text3.setBorder(BorderFactory.createEtchedBorder());
        button3.setText("Submit");
        button3.setBounds(280,335, 120, 50);
        button3.setFocusable(false);
        button3.addActionListener(new JButtonCustomer());
        
       JPanel panel4=new JPanel();
       JLabel label4=new JLabel();
     
        panel4.setBackground(new Color(0xCCCCCC));
        panel4.setBounds(0, 400, 120, 50);
        label4.setText("    QUEUE NUMBER ");
        panel4.setLayout(new BorderLayout());
        text4.setBounds(140,400, 120, 50);
        text4.setBorder(BorderFactory.createEtchedBorder());
        button4.setText("Submit");
        button4.setBounds(280,400, 120, 50);
        button4.setFocusable(false);
        button4.addActionListener(new JButtonCustomer());
        
        
        
        
        
        
        
        
        panel.add(label);
        frame.setSize(600,600);
        frame.setTitle("Customer Panel→");
        frame.setIconImage(image.getImage());
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(null);
        frame.add(panel);
        
        panel1.add(label1);
        frame.add(text1);
       frame.add(panel1);
       frame.add(button1);
       
     
        frame.add(panel2);
        panel2.add(label2);
        frame.add(text2);
        frame.add(button2);
        
        frame.add(panel3);
        panel3.add(label3);
        frame.add(text3);
        frame.add(button3);
        
        frame.add(panel4);
        panel4.add(label4);
        frame.add(text4);
        frame.add(button4);
        
        
        frame.setVisible(true);
        frame.setResizable(false);
     
        
    }
    @Override
public void actionPerformed(ActionEvent e) {
    try {
        Connection conn = DriverManager.getConnection(url, username, password);
        Statement stmt = conn.createStatement();
        stmt.executeUpdate("USE supermarket");

        if (e.getSource() == button1) {
            try {
                int id = Integer.parseInt(text1.getText());
                System.out.println("Customer ID: " + id);
                button1.setEnabled(false);
                JOptionPane.showMessageDialog(frame, "Customer ID Added Successfully");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Enter a valid Customer ID (numeric)");
            }
        }

        if (e.getSource() == button2) {
            String name = text2.getText().trim();
            if (!name.isEmpty()) {
                System.out.println("Customer Name: " + name);
                button2.setEnabled(false);
                JOptionPane.showMessageDialog(frame, "Customer Name Added Successfully");
            } else {
                JOptionPane.showMessageDialog(frame, "Customer name cannot be empty");
            }
        }

        if (e.getSource() == button3) {
            try {
                long num = Long.parseLong(text3.getText());
                System.out.println("Contact Number: " + num);
                button3.setEnabled(false);
                JOptionPane.showMessageDialog(frame, "Contact Number Added Successfully");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Enter a valid contact number");
            }
        }

        if (e.getSource() == button4) {
            try {
                int s4 = Integer.parseInt(text4.getText());
                int id = Integer.parseInt(text1.getText());
                String name = text2.getText();
                long num = Long.parseLong(text3.getText());

                // Insert to DB only after all fields are submitted
                String sql1 = "INSERT INTO customer VALUES (" + id + ", '" + name + "', " + num + ", " + s4 + ");";
                stmt.executeUpdate(sql1);

                System.out.println("Queue Number: " + s4);
                button4.setEnabled(false);
                JOptionPane.showMessageDialog(frame, "Customer added to database successfully");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Enter valid values in all fields before submitting");
            }
        }

    } catch (SQLException ex1) {
        JOptionPane.showMessageDialog(frame, ex1.getMessage());
    }
}
}

  