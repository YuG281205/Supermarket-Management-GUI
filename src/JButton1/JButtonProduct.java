/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JButton1;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author yugjh
 */
class InsertProduct implements ActionListener {
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
        static  JFrame frame=new JFrame();
        static JButton button4=new JButton();
       
    InsertProduct()
    {
     
        ImageIcon image=new ImageIcon("yug.jpg");
        ImageIcon image1=new ImageIcon("products_logo.jpg");
        JPanel panel=new JPanel();
        JLabel label=new JLabel();
        
       panel.setBackground(new Color(0x00243e));
       panel.setBounds(0, 0, 600, 200);
       label.setIcon(image1);
        
        JPanel panel1=new JPanel();
        JLabel label1=new JLabel(); 
        panel1.setBackground(new Color(0xCCCCCC));
        panel1.setBounds(0,210, 120, 50);
        label1.setText("   Product ID");
        label1.setFont(new Font("Times new Roman",Font.BOLD,18));
        panel1.setLayout(new BorderLayout());
        text1.setBounds(140, 210, 120, 50);
        button1.setBounds(280, 210, 120, 50);
        button1.setText("Submit");
        button1.setFocusable(false);
        button1.addActionListener(this);
        
        
        
        JPanel panel2=new JPanel();
        JLabel label2=new JLabel(); 
        panel2.setBackground(new Color(0xCCCCCC));
        panel2.setBounds(0,290, 120, 50);
        label2.setText(" Product NAME");
        label2.setFont(new Font("Times new Roman",Font.BOLD,16));
        panel2.setLayout(new BorderLayout());
        text2.setBounds(140, 290, 120, 50);
        button2.setBounds(280, 290, 120, 50);
        button2.setText("Submit");
        button2.setFocusable(false);
        button2.addActionListener(this);
        
        
        
        JPanel panel3=new JPanel();
        JLabel label3=new JLabel(); 
        panel3.setBackground(new Color(0xCCCCCC));
        panel3.setBounds(0,370, 120, 50);
        label3.setText(" Product PRICE");
        label3.setFont(new Font("Times new Roman",Font.BOLD,16));
        panel3.setLayout(new BorderLayout());
        text3.setBounds(140, 370, 120, 50);
        button3.setBounds(280, 370, 120, 50);
        button3.setText("Submit");
        button3.setFocusable(false);
        button3.addActionListener(this);
        
        
         JPanel panel4=new JPanel();
        JLabel label4=new JLabel(); 
        panel4.setBackground(new Color(0xCCCCCC));
        panel4.setBounds(0,450, 120, 50);
        label4.setText(" Product STOCK");
        label4.setFont(new Font("Times new Roman",Font.BOLD,16));
        panel4.setLayout(new BorderLayout());
        text4.setBounds(140, 450, 120, 50);
        button4.setBounds(280, 450, 120, 50);
        button4.setText("Submit");
        button4.setFocusable(false);
        button4.addActionListener(this);
        
        
        
        frame.setSize(800,800);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setTitle("Product Panel->");
        frame.setIconImage(image.getImage());
        frame.setVisible(true);
        frame.setLayout(null);
        
        
        
        frame.add(panel);
        panel.add(label);
        
        
        frame.add(panel1);
        panel1.add(label1);
        frame.add(text1);
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
    }
    
     @Override
    public void actionPerformed(ActionEvent e) {
        try{
        Connection conn=DriverManager.getConnection(url, username, password);
         Statement stmt=conn.createStatement();
        stmt.executeUpdate("use supermarket");
        
        if(e.getSource()==button1)
        {
            try{
                String sql="select *from product";
                stmt.executeQuery(sql);
                
                int id=Integer.parseInt(text1.getText());
                System.out.println("Product_ID:"+id);
                button1.setEnabled(false);
            }
            catch(NumberFormatException ex)
            {
                JOptionPane.showMessageDialog(frame,"ENTER VALID VALUE!");
            }
        }
        if(e.getSource()==button2)
        {
            try{
            String name=text2.getText();
            System.out.println("Product_NAME:"+text2.getText());
            button2.setEnabled(false);
            }
            catch(NumberFormatException ex)
            {
                JOptionPane.showMessageDialog(frame,"ENTER VALID VALUE!");
            }
        }
        if(e.getSource()==button3)
        {
            try{
            int price=Integer.parseInt(text3.getText());
            System.out.println("Product_PRICE:"+price);
            button3.setEnabled(false);
            }
            catch(NumberFormatException ex)
            {
                 JOptionPane.showMessageDialog(frame,"ENTER VALID VALUE!");
            }
        }
        if(e.getSource()==button4)
        {
            try{
                
            int id=Integer.parseInt(text1.getText());
            String name=text2.getText();
             int price=Integer.parseInt(text3.getText());
            int stock=Integer.parseInt(text4.getText());
            
            String sql="insert into product values("+id+","+"'"+name+"'"+","+price+","+stock+" ) ";
            stmt.executeUpdate(sql);
            System.out.println("Product_STOCK:"+stock);
            button4.setEnabled(false);
            
            FileOutputStream fout=new FileOutputStream("demo1.txt",true);
            BufferedWriter writer=new BufferedWriter(new OutputStreamWriter(fout));
            StringBuilder content=new StringBuilder();
            String line="         "+id+"         "+name+"         "+price+"         "+stock;
            
            writer.write(line);
            writer.newLine();
            writer.close();
            JOptionPane.showMessageDialog(frame,"VALUE INSERTED SUCCESSFULLY...");
            }
            catch(NumberFormatException ex)
            {
                JOptionPane.showMessageDialog(frame,"ENTER VALID VALUE!");
            } 
            catch (FileNotFoundException ex) {
                System.out.println(ex.getMessage());
            } 
            catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(frame,ex.getMessage());
        }
    }
        void display() {
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("use supermarket");

            ResultSet rs = stmt.executeQuery("SELECT * FROM product");

            // Clear file
            new FileOutputStream("demo1.txt").close();

            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream("demo1.txt", true))
            );

            // SAME spacing as your original
            while (rs.next()) {
                int id = rs.getInt("p_id");
                String name = rs.getString("p_name");
                int price = rs.getInt("p_price");
                int stock = rs.getInt("p_stock");

                String line = "         " + id + 
                              "         " + name + 
                              "         " + price + 
                              "         " + stock;

                writer.write(line);
                writer.newLine();
            }

            writer.close();

            // Read and display
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(new FileInputStream("demo1.txt"))
            );

            StringBuilder content = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }

            reader.close();

            JTextArea text = new JTextArea();
            text.setText(content.toString());
            text.setBounds(430, 250, 240, 240);
            text.setForeground(Color.white);
            text.setBackground(new Color(0x0243e));
            text.setFocusable(false);

            JPanel panel = new JPanel();
            panel.setBounds(430, 220, 250, 20);
            panel.setBackground(new Color(0x0243e));

            JLabel label = new JLabel("      p_id      p_name      p_price      p_stock");
            label.setForeground(Color.white);

            panel.add(label);

            JPanel panel2 = new JPanel();
            panel2.setBounds(430, 250, 250, 250);
            panel2.setBackground(new Color(0x0243e));
            panel2.add(text);

            frame.add(panel);
            frame.add(panel2);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
}
public class JButtonProduct{
    public static void main(String[] args) throws IOException {
        InsertProduct ip=new InsertProduct();
        ip.display();
    }

    
    
}
