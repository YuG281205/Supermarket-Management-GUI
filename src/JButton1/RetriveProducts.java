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

/**
 *
 * @author yugjh
 */
public class RetriveProducts {
    private static final String url="jdbc:mysql://localhost:3306/?user=root";
    private static final String username="root";
    private static final String password="YuG@#2647";
    static void display()
    {
        try{
        JFrame frame=new JFrame();
        JPanel panel=new JPanel();
        JTextArea text=new JTextArea();
        JLabel label=new JLabel();
        
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
        
        panel.setBackground(Color.black);
        panel.setBounds(0,0,300,300);
        
        label.setText("p_id      p_name    p_price    p_stock");
        label.setForeground(Color.white);
        panel.setLayout(new BorderLayout());
        label.setVerticalAlignment(JLabel.TOP);
        label.setHorizontalAlignment(JLabel.LEFT);
   
       FileInputStream fin=new FileInputStream("demo1.txt");
       BufferedReader reader=new BufferedReader(new InputStreamReader(fin));
        StringBuilder content=new StringBuilder();
        String line;
        
        while((line=reader.readLine())!=null)
        {
            content.append(line).append("\n");
        }
        text.setText(content.toString());
        text.setForeground(Color.white);
        text.setBackground(Color.black);
        text.setBounds(10,20, 500, 500);
        frame.setSize(800,800);
        
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.add(panel);
        panel.add(text);
        panel.add(label);
        
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        
    }
    
    public static void main(String[] args) {
        display();
    }
}
