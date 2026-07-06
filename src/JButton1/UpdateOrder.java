/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JButton1;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


class Update_Order implements ActionListener{
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
         static JTextField text5=new JTextField();
        static JButton button5=new JButton();
        
        
        Update_Order()
        {
             ImageIcon image=new ImageIcon("yug.jpg");
        ImageIcon image1=new ImageIcon("Order_update.jpg");
        JPanel panel=new JPanel();
        JLabel label=new JLabel();
        
       panel.setBackground(new Color(0x00243e));
       panel.setBounds(0, 0, 1400, 200);
       label.setIcon(image1);
        
        
        
        
        JPanel panel2=new JPanel();
        JLabel label2=new JLabel(); 
        panel2.setBackground(new Color(0xCCCCCC));
        panel2.setBounds(0,290, 120, 50);
        label2.setText(" Order TYPE");
        label2.setFont(new Font("Times new Roman",Font.BOLD,18));
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
        label3.setText("  Product ID");
        label3.setFont(new Font("Times new Roman",Font.BOLD,18));
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
        label4.setText(" Customer ID");
        label4.setFont(new Font("Times new Roman",Font.BOLD,18));
        panel4.setLayout(new BorderLayout());
        text4.setBounds(140, 450, 120, 50);
        button4.setBounds(280, 450, 120, 50);
        button4.setText("Submit");
        button4.setFocusable(false);
        button4.addActionListener(this);
        
        JPanel panel5=new JPanel();
        JLabel label5=new JLabel();
         panel5.setBackground(new Color(0xCCCCCC));
        panel5.setBounds(0,530, 120, 50);
        label5.setText(" Order Quantity");
        label5.setFont(new Font("Times new Roman",Font.BOLD,16));
        panel5.setLayout(new BorderLayout());
        text5.setBounds(140, 530, 120, 50);
        button5.setBounds(280, 530, 120, 50);
        button5.setText("Submit");
        button5.setFocusable(false);
        button5.addActionListener(this);
        
        JPanel panel1=new JPanel();
        JLabel label1=new JLabel(); 
        panel1.setBackground(new Color(0xCCCCCC));
        panel1.setBounds(90,610, 190, 50);
        label1.setText(" Order ID To UPDATE");
        label1.setFont(new Font("Times new Roman",Font.BOLD,18));
        panel1.setLayout(new BorderLayout());
        text1.setBounds(300, 610, 120, 50);
        button1.setBounds(440, 610, 120, 50);
        button1.setText("Submit");
        button1.setFocusable(false);
        button1.addActionListener(this);
        
        
        JPanel panel6=new JPanel();
        JLabel label6=new JLabel();
        panel6.setBackground(new Color(0x0243e));
        panel6.setBounds(440, 210, 300, 30);
        label6.setText("c_id      c_name    contact_no    queue_no");
        label6.setForeground(Color.white);  
        
        
        
        JPanel panel7=new JPanel();
        JTextArea area=new JTextArea();
        
        area.setBackground(new Color(0x0243e));
        area.setBounds(450,250,250,250);
        panel7.setLayout(new BorderLayout());
        panel7.setBackground(Color.red);
        panel7.setBounds(440,250,300,300);
        area.setForeground(Color.WHITE);
        
         try{
        Connection conn=DriverManager.getConnection(url, username, password);
        Statement stmt=conn.createStatement();
        stmt.executeUpdate("use supermarket");
        String sql2="select *from customer";
        ResultSet rs=stmt.executeQuery(sql2);
        
        
        new FileOutputStream("demo2.txt").close();
        while(rs.next())
        {
        int id=rs.getInt("c_id");
        String name=rs.getString("c_name");
        long c_no=rs.getLong("c_no");
        int q=rs.getInt("queue_no");
              FileOutputStream fout=new FileOutputStream("demo2.txt",true);
            BufferedWriter writer=new BufferedWriter(new OutputStreamWriter(fout));
           
            String line="         "+id+"         "+name+"         "+c_no+"         "+q;
            
            writer.write(line);
            writer.newLine();
            writer.close();
            System.out.println("Value inserted...");
        }
        FileInputStream fin=new FileInputStream("demo2.txt");
       BufferedReader reader=new BufferedReader(new InputStreamReader(fin));
        StringBuilder content=new StringBuilder();
        String line;
        
        while((line=reader.readLine())!=null)
        {
            content.append(line).append("\n");
        }
        area.setText(content.toString());
        area.setFocusable(false);
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        JPanel panel8=new JPanel();
        JLabel label8=new JLabel();
        
        label8.setText("p_id      p_name    p_price    p_stock");
        label8.setForeground(Color.white);
        panel8.setBackground(new Color(0x0243e));
        panel8.setBounds(760,210, 300, 30);
        
        JPanel panel9=new JPanel();
        JTextArea area9=new JTextArea();
      
        panel9.setBackground(new Color(0x0243e));
        panel9.setBounds(760,250, 300, 300);
        area9.setBackground(new Color(0x0243e));
        
         try{
        Connection conn=DriverManager.getConnection(url, username, password);
        Statement stmt=conn.createStatement();
        stmt.executeUpdate("use supermarket");
        ResultSet rs=stmt.executeQuery("select *from product");
         new FileOutputStream("demo1.txt").close();
        while(rs.next())
        {
            FileOutputStream fout=new FileOutputStream("demo1.txt",true);
            BufferedWriter writer=new BufferedWriter(new OutputStreamWriter(fout));
            int p_id=rs.getInt("p_id");
            String p_name=rs.getString("p_name");
            int p_price=rs.getInt("p_price");
            int p_stock=rs.getInt("p_stock");
            String line="  "+p_id+"           "+p_name+"            "+p_price+"         "+p_stock+"        ";
            writer.append(line);
            writer.newLine();
            writer.close();
            System.out.println("VALUE INSERTED....");
        }
        
        FileInputStream fin=new FileInputStream("demo1.txt");
        BufferedReader reader=new BufferedReader(new InputStreamReader(fin));
        StringBuilder content=new StringBuilder();
        String line;
        while((line=reader.readLine())!=null)
        {
            content.append(line).append("\n");
        }
        area9.setText(content.toString());
        area9.setForeground(Color.white);
        area9.setFocusable(false);
        }
        catch(IOException e)
        {
            System.out.println(e.getMessage());
        }
        catch(SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
       
        
        JPanel panel10=new JPanel();
        JLabel label10=new JLabel();
        
        label10.setText("o_id    o_type    p_id    c_id     o_quantity");
        label10.setForeground(Color.white);
        panel10.setBackground(new Color(0x0243e));
        panel10.setBounds(1090,210,300,30);
        
        JPanel panel11=new JPanel();
        JTextArea area11=new JTextArea();
        
        panel11.setBackground(new Color(0x0243e));
        panel11.setBounds(1090,250,300,300);
        area11.setBounds(1090,200,300,300);
        area11.setBackground(Color.white);
        panel11.setLayout(new BorderLayout());
        
        try{
            Connection conn=DriverManager.getConnection(url, username, password);
            Statement stmt=conn.createStatement();
            stmt.executeUpdate("use supermarket");

            String sql2="select *from orders";
            ResultSet rs=stmt.executeQuery(sql2);

            // CLEAR FILE
            new FileOutputStream("demo3.txt").close();

            // OPEN WRITER ONCE
            BufferedWriter writer=new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream("demo3.txt",true))
            );

            while(rs.next())
            {
                int id=rs.getInt("o_id");
                String o_type=rs.getString("o_type");
                int p_id=rs.getInt("p_id");
                int c_id=rs.getInt("c_id");
                int o_quantity=rs.getInt("o_quantity");

                area11.setBackground(new Color(0x0243e));

                // SAME SPACING STYLE (your format)
                String line="         "+id+
                             "         "+o_type+
                             "         "+p_id+
                             "         "+c_id+
                             "         "+o_quantity;

                writer.write(line);
                writer.newLine();
            }

            writer.close();

            // READ FILE
            BufferedReader reader=new BufferedReader(
                    new InputStreamReader(new FileInputStream("demo3.txt"))
            );

            StringBuilder content=new StringBuilder();
            String line;

            while((line=reader.readLine())!=null)
            {
                content.append(line).append("\n");
            }

            area11.setText(content.toString());
            area11.setForeground(Color.white);

            reader.close();
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(frame,ex.getMessage());
        }
                frame.setSize(1600,800);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setTitle("Order Update Panel->");
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
        
        
        frame.add(panel5);
        panel5.add(label5);
        frame.add(text5);
        frame.add(button5);
        
        frame.add(panel6);
        panel6.add(label6);
        
        frame.add(panel7);
        panel7.add(area);
        
        frame.add(panel8);
        panel8.add(label8);
        
        frame.add(panel9);
        panel9.add(area9);
        
        frame.add(panel10);
        panel10.add(label10);
        
        frame.add(panel11);
        panel11.add(area11);
    }
        
    
     @Override
    public void actionPerformed(ActionEvent e) {
        try{
        Connection conn=DriverManager.getConnection(url, username, password);
        Statement stmt=conn.createStatement();
        stmt.executeUpdate("use supermarket");
//        if(e.getSource()==button1)
//        {
//            try{
//                int o_id=Integer.parseInt(text1.getText());
//                
//                System.out.println("Order_ID:"+o_id);
//                button1.setEnabled(false);
//            }
//            catch(NumberFormatException ex)
//            {
//                JOptionPane.showMessageDialog(frame,"ENTER VALID VALUE!");
//            }
//        }
        if(e.getSource()==button2)
        {
            try{
            String o_type=text2.getText();
            int o_id=Integer.parseInt(text1.getText());
            System.out.println("Order Type:"+o_type);
            button2.setEnabled(false);
            String sql="update orders set o_type="+"'"+o_type+"'"+" where o_id="+o_id+";";
            stmt.executeUpdate(sql);
            JOptionPane.showMessageDialog(frame,"Value Updated....");
            button1.setEnabled(false);
            }
            catch(NumberFormatException ex)
            {
                JOptionPane.showMessageDialog(frame,"ENTER VALID VALUE!");
            }
        }
        if(e.getSource()==button3)
        {
            try{
            int p_id=Integer.parseInt(text3.getText());
            int o_id=Integer.parseInt(text1.getText());
            System.out.println("Product ID:"+p_id);
            button3.setEnabled(false);
            String sql="update orders set p_id="+p_id+" where o_id="+o_id+";";
              stmt.executeUpdate(sql);
            JOptionPane.showMessageDialog(frame,"Value Updated....");
            }
            catch(NumberFormatException ex)
            {
                 JOptionPane.showMessageDialog(frame,"ENTER VALID VALUE!");
            }
        }
        if(e.getSource()==button4)
        {
            try{
            
             int c_id=Integer.parseInt(text4.getText());
             int o_id=Integer.parseInt(text1.getText());
            System.out.println("Customer ID:"+c_id);
            button4.setEnabled(false);
            String sql="update orders set c_id="+c_id+" where o_id="+o_id+";";
              stmt.executeUpdate(sql);
            JOptionPane.showMessageDialog(frame,"Value Updated....");
            button1.setEnabled(false);
            
            }
            catch(NumberFormatException ex)
            {
                JOptionPane.showMessageDialog(frame,"ENTER VALID VALUE!");
            }
        }
        if(e.getSource()==button5)
        {
            try{
            int o_id=Integer.parseInt(text1.getText());
            int o_quantity=Integer.parseInt(text5.getText());
            String sql="update orders set o_quantity="+o_quantity+" where o_id="+o_id+";"; 
            stmt.executeUpdate(sql);
           System.out.println("Order Quantity:"+o_quantity);
           button5.setEnabled(false);
           button1.setEnabled(false);
           JOptionPane.showMessageDialog(frame,"Value Updated....");
            }
             catch(NumberFormatException ex)
            {
                JOptionPane.showMessageDialog(frame,"ENTER VALID VALUE!");
            }
            
        }
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(frame,ex.getMessage());
        }
    }
    
 }
public class UpdateOrder {
    public static void main(String[] args) {
        Update_Order uo=new Update_Order();
    }
   
}
