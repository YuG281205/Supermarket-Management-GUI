/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JButton1;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.io.*;
/**
 *
 * @author yugjh
 */

class delete_customer implements ActionListener
{
    JFrame frame=new JFrame();
    ImageIcon icon=new ImageIcon("yug.jpg");
    JPanel panel=new JPanel();
    JLabel label=new JLabel();
    
    JPanel panel7=new JPanel();
    JPanel panel6=new JPanel();
    JLabel label6=new JLabel();
    JTextArea text7=new JTextArea();
    
    JPanel panel1=new JPanel();
    JLabel label1=new JLabel();
    JTextField text1=new JTextField();
    JButton button1=new JButton();
    
    
    private static final String url="jdbc:mysql://localhost:3306/?user=root";
    private static final String username="root";
    private static final String password="YuG@#2647";
    
        
    delete_customer()
    {
        ImageIcon icon1=new ImageIcon("delete_customer.jpg");
        panel.setBounds(0, 0, 800,200);
        panel.setBackground(new Color(0x0243e));
        label.setIcon(icon1);
        frame.setIconImage(icon.getImage());
        frame.setSize(800,800);
        frame.setTitle("Customer delete panel→");
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        
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
        
        panel7.setBackground(new Color(0x243e));
        panel7.setBounds(450,250,300,300);
       panel7.setLayout(new BorderLayout());
      
       FileInputStream fin=new FileInputStream("demo2.txt");
       BufferedReader reader=new BufferedReader(new InputStreamReader(fin));
        StringBuilder content=new StringBuilder();
        String line;
        
        while((line=reader.readLine())!=null)
        {
            content.append(line).append("\n");
        }
        text7.setText(content.toString());
        text7.setForeground(Color.white);
        text7.setBackground(new Color(0x0243e));
        text7.setBounds(410,250,300,300);
        
        text7.setFocusable(false);
      
        
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    
          panel6.setBackground(new Color(0x0243e));
          panel6.setBounds(450,210,300,30);
          label6.setText("c_id      c_name    contact_no    queue_no");
          label6.setForeground(Color.white);
          frame.add(panel7);
          panel7.add(text7);
          panel6.add(label6);
          frame.add(panel6);
        
        panel.add(label);
        frame.add(panel);
        frame.setVisible(true);
        
        panel1.setBackground(new Color(0xcccccc));
        panel1.setBounds(0,250,250,100);
        label1.setText("        Type customer ID TO DELETE→  ");
        panel1.setLayout(new BorderLayout());
        panel1.add(label1);
        frame.add(panel1);
        
        text1.setBounds(270,270, 50,50);
        frame.add(text1);
        
        button1.setBounds(340,260,100,100);
        button1.setText("SUBMIT");
        button1.setFocusable(false);
        button1.addActionListener(this);
        frame.add(button1);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==button1)
        {
            System.out.println("Customer deleted->"+text1.getText());
            try{
                 Connection conn=DriverManager.getConnection(url, username, password);
                Statement stmt=conn.createStatement();
                stmt.executeUpdate("use supermarket");
                String sql="delete from customer where c_id="+text1.getText()+";";
                stmt.executeUpdate(sql);
                button1.setEnabled(false);
            }
            catch(Exception ex)
            {
                System.out.println(ex.getMessage());
            }
        }
    }
}
public class DeleteCustomer {
    public static void main(String[] args) {
        delete_customer dc=new delete_customer();
    }
}
