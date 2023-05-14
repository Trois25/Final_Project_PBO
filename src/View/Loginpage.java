    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.Connector;
import View.AdminPage;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author OWNER
 */
public class Loginpage extends JFrame{
    Connector c = new Connector();
    JLabel ltitle = new JLabel("Login");
    
    JLabel lusername = new JLabel("Username");
    JTextField fusername = new JTextField();
    
    JLabel lpassword = new JLabel("Password");
    JPasswordField fpassword = new JPasswordField();
    
    JButton blogin = new JButton("Login");
    
    public Loginpage(){
        setSize(800, 600);
        setTitle("Login Page");
        setVisible(true);
        setLayout(null);
        
        setLocationRelativeTo(null);
        
        add(ltitle);
        add(lusername);
        add(lpassword);
        add(fusername);
        add(fpassword);
        add(blogin);
        
        ltitle.setBounds(365, 50, 75, 50);
        
        lusername.setBounds(275, 120, 100, 30);
        fusername.setBounds(275, 150, 250, 30);
        
        lpassword.setBounds(275, 180, 100, 30);
        fpassword.setBounds(275, 210, 250, 30);
        
        blogin.setBounds(350, 260, 100, 30);
        
        blogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                String username = fusername.getText();
                String password = String.valueOf(fpassword.getPassword());
                String data[] = new String[2];
                
                data = c.Login(username,password);
                
                if(data[0].equals("admin")){
                    System.out.println("Login Success");
                    System.out.println("Login as Admin");
                    AdminPage admin = new AdminPage();
                    dispose();
                }else{
                    JOptionPane.showMessageDialog(null, "Login Failed");
                }
            }
        });
    }
    
}
