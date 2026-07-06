/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JButton1;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.sql.*;

class Delete_Order implements ActionListener{
    private static final String url="jdbc:mysql://localhost:3306/?user=root";
    private static final String username="root";
    private static final String password="YuG@#2647";
    
   
    JFrame frame=new JFrame();
    ImageIcon icon=new ImageIcon("yug.jpg");
    JPanel panel=new JPanel();
    JLabel label=new JLabel();
    
     JPanel panel1=new JPanel();
    JLabel label1=new JLabel();
    JTextField text1=new JTextField();
    JButton button1=new JButton();
    
     JPanel panel5=new JPanel();
    JTextArea text5=new JTextArea();
    
    JPanel panel6=new JPanel();
    JLabel label5=new JLabel();
    JLabel label6=new JLabel();
    
    Delete_Order()
    {
         ImageIcon icon1=new ImageIcon("delete_order.jpg");
        panel.setBounds(0, 0, 800,200);
        panel.setBackground(new Color(0x0243e));
        label.setIcon(icon1);
        frame.setIconImage(icon.getImage());
        frame.setSize(800,800);
        frame.setTitle("Order delete panel→");
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(panel);
        panel.add(label);
        
        panel1.setBackground(new Color(0xcccccc));
        panel1.setBounds(0,250,250,100);
        label1.setText("        Type Order ID TO DELETE→  ");
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
        frame.setVisible(true);
        
        try{
        Connection conn=DriverManager.getConnection(url, username, password);
        Statement stmt=conn.createStatement();
        stmt.executeUpdate("use supermarket");
        String sql2="select *from orders";
        ResultSet rs=stmt.executeQuery(sql2);
        
        
         new FileOutputStream("demo3.txt").close();
        while(rs.next())
        {
        int id=rs.getInt("o_id");
        String o_type=rs.getString("o_type");
        int p_id=rs.getInt("p_id");
        int c_id=rs.getInt("c_id");
        int o_quantity=rs.getInt("o_quantity");
        FileOutputStream fout=new FileOutputStream("demo3.txt",true);
        BufferedWriter writer=new BufferedWriter(new OutputStreamWriter(fout));
        text5.setBackground(new Color(0x0243e));
           
            String line="             "+id+"         "+o_type+"         "+p_id+"         "+c_id+"       "+o_quantity;
            
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
   
      FileInputStream fin=new FileInputStream("demo3.txt");
        BufferedReader reader=new BufferedReader(new InputStreamReader(fin));
        StringBuilder content=new StringBuilder();
        String line;
        while((line=reader.readLine())!=null)
        {
            content.append(line).append("\n");
        }
        text5.setText(content.toString());
        text5.setForeground(Color.white);
        }
        
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(frame,ex.getMessage());
        }
    
          panel6.setBackground(new Color(0x0243e));
          panel6.setBounds(450,210,300,30);
          label6.setText("       o_id    o_type    p_id    c_id     o_quantity");
          panel6.setLayout(new BorderLayout());
          label6.setForeground(Color.white);
        frame.add(panel5);
        panel5.add(text5);
        frame.add(panel6);
        panel6.add(label6);
        frame.setVisible(true);
       
        
     }

    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==button1)
        {
            System.out.println("Order Deleted..."+text1.getText());
             try{
                 Connection conn=DriverManager.getConnection(url, username, password);
                Statement stmt=conn.createStatement();
                stmt.executeUpdate("use supermarket");
                String sql="delete from orders where o_id="+text1.getText()+";";
                stmt.executeUpdate(sql);
                button1.setEnabled(false);
                JOptionPane.showMessageDialog(frame,"Order deleted...");
            }
            catch(Exception ex)
            {
                System.out.println(ex.getMessage());
            }
        }
    }
    
}
public class DeleteOrder {
    public static void main(String[] args) {
        Delete_Order dO=new Delete_Order();
    }
}
