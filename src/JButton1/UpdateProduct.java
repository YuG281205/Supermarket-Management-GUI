/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JButton1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author yugjh
 */
class Update_Product implements ActionListener{
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
        
        static JPanel panel5=new JPanel();
        static JTextArea text5=new JTextArea();
        static JLabel label5=new JLabel();
        
          static JPanel panel6=new JPanel();
           static JLabel label6=new JLabel();
    Update_Product() throws IOException
    {
        ImageIcon image=new ImageIcon("yug.jpg");
        ImageIcon image1=new ImageIcon("product_update1.png");
        JPanel panel=new JPanel();
        JLabel label=new JLabel();
        
       panel.setBackground(new Color(0x00243e));
       panel.setBounds(0, 0, 600, 200);
       label.setIcon(image1);
        
       
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
        
        
        JPanel panel1=new JPanel();
        JLabel label1=new JLabel(); 
        panel1.setBackground(new Color(0xCCCCCC));
        panel1.setBounds(100,550, 275, 50);
        label1.setText("     Product ID to UPDATE  ");
        label1.setFont(new Font("Times new Roman",Font.BOLD,18));
        panel1.setLayout(new BorderLayout());
        text1.setBounds(400, 550, 120, 50);
        button1.setBounds(550, 550, 120, 50);
        button1.setText("Submit");
        button1.setFocusable(false);
        button1.addActionListener(this);
        
          try{
        Connection conn=DriverManager.getConnection(url, username, password);
        Statement stmt=conn.createStatement();
        stmt.executeUpdate("use supermarket");
        String sql2="select *from product";
        ResultSet rs=stmt.executeQuery(sql2);
        
        
        new FileOutputStream("demo1.txt").close();
        while(rs.next())
        {
        int id=rs.getInt("p_id");
        String name=rs.getString("p_name");
        int price=rs.getInt("p_price");
        int stock=rs.getInt("p_stock");
              FileOutputStream fout=new FileOutputStream("demo1.txt",true);
            BufferedWriter writer=new BufferedWriter(new OutputStreamWriter(fout));
           
            String line="         "+id+"         "+name+"         "+price+"         "+stock;
            
            writer.write(line);
            writer.newLine();
            writer.close();
            System.out.println("Value inserted...");
        }
        
        panel5.setBackground(Color.black);
        panel5.setBounds(450,250,300,300);
     
        
        label5.setForeground(Color.white);
        panel5.setLayout(new BorderLayout());
        label5.setVerticalAlignment(JLabel.TOP);
        label5.setHorizontalAlignment(JLabel.LEFT);
   
       FileInputStream fin=new FileInputStream("demo1.txt");
       BufferedReader reader=new BufferedReader(new InputStreamReader(fin));
        StringBuilder content=new StringBuilder();
        String line;
        
        while((line=reader.readLine())!=null)
        {
            content.append(line).append("\n");
        }
        text5.setText(content.toString());
        text5.setForeground(Color.white);
        text5.setBackground(new Color(0x0243e));
        text5.setBounds(460,250,100,100);
        text5.setFocusable(false);
      
        
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    
          panel6.setBackground(new Color(0x0243e));
          panel6.setBounds(450,210,300,30);
          label6.setText("p_id      p_name    p_price    p_stock");
          label6.setForeground(Color.white);
        
        
        frame.setSize(800,800);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setTitle("Product Update Panel->");
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
        
       // panel5.add(label5);
        panel5.add(text5);
        frame.add(panel5);
        
        panel6.add(label6);
        frame.add(panel6);
        
    }
     @Override
    public void actionPerformed(ActionEvent e) {
         
        try{
       
        Connection conn=DriverManager.getConnection(url, username, password);
         Statement stmt=conn.createStatement();
        stmt.executeUpdate("use supermarket");
       
        if(e.getSource()==button2)
        {
            try{
            int id=Integer.parseInt(text1.getText());
            String name=text2.getText();
            System.out.println("Product_NAME:"+text2.getText());
            button2.setEnabled(false);
            
           String sql = "Update product set p_name=" + "'" + name + "'" + "where p_id =" + id;
            stmt.executeUpdate(sql);
            button1.setEnabled(false);
            JOptionPane.showMessageDialog(frame,"Value inserted...");
            
           
            }
            catch(NumberFormatException ex)
            {
                 JOptionPane.showMessageDialog(frame,"ENTER VALID VALUE!");
            }
        }
            
            
        if(e.getSource()==button3)
        {
            try{
            int id=Integer.parseInt(text1.getText());
            int price=Integer.parseInt(text3.getText());
            System.out.println("Product_PRICE:"+price);
            button3.setEnabled(false);
            
            String sql = "Update product set p_price="+price+ " where p_id =" + id;
            stmt.executeUpdate(sql);
            button1.setEnabled(false);
            JOptionPane.showMessageDialog(frame,"Value inserted...");
            
            }
            catch(NumberFormatException ex)
            {
                 JOptionPane.showMessageDialog(frame,"ENTER VALID VALUE!");
            }
        }
        if(e.getSource()==button4)
        {
            try{
                
          
            int stock=Integer.parseInt(text4.getText());
            int id=Integer.parseInt(text1.getText());
            System.out.println("Product_STOCK:"+stock);
            button4.setEnabled(false); 
               
            String sql = "Update product set p_stock="+stock+ " where p_id =" + id;
            stmt.executeUpdate(sql);
            button1.setEnabled(false);
           JOptionPane.showMessageDialog(frame,"Value inserted...");
            
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

public class UpdateProduct {
    public static void main(String[] args) throws IOException {
        Update_Product up=new Update_Product();
      
    }
}
