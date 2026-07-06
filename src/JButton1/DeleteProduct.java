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
/**
 *
 * @author yugjh
 */
class Delete_Product implements ActionListener{
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
     Delete_Product(){
        ImageIcon icon1=new ImageIcon("delete_product.jpg");
        panel.setBounds(0, 0, 800,200);
        panel.setBackground(new Color(0x0243e));
        label.setIcon(icon1);
        frame.setIconImage(icon.getImage());
        frame.setSize(800,800);
        frame.setTitle("Product delete panel→");
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        panel1.setBackground(new Color(0xcccccc));
        panel1.setBounds(0,250,250,100);
        label1.setText("        Type product ID TO DELETE→  ");
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
        
        panel.add(label);
        frame.add(panel);
        frame.setVisible(true);
        
        
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
          label6.setText("           p_id      p_name    p_price    p_stock      ");
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
            System.out.println("Deleted product"+text1.getText());
            try{
                 Connection conn=DriverManager.getConnection(url, username, password);
                Statement stmt=conn.createStatement();
                stmt.executeUpdate("use supermarket");
                String sql="delete from product where p_id="+text1.getText()+";";
                stmt.executeUpdate(sql);
                button1.setEnabled(false);
                JOptionPane.showMessageDialog(frame,"Product deleted...");
            }
            catch(Exception ex)
            {
                System.out.println(ex.getMessage());
            }
        }
    }
}
public class DeleteProduct {
    public static void main(String[] args) {
        Delete_Product dp=new Delete_Product();
    }
}
