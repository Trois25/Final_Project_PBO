/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import javax.swing.*;
import java.awt.event.*;

/**
 *
 * @author OWNER
 */
public class FirstPage extends JFrame{
    JLabel ltitle = new JLabel("--== Horse Cafe ==--");
    
    JButton badmin = new JButton("Admin");
    JButton bcustomer = new JButton("Customer");
    
    public FirstPage(){
        setSize(500, 400);
        setTitle("Menu");
        
        setVisible(true);
        setLayout(null);
        setLocationRelativeTo(null);
        
        add(ltitle);
        add(badmin);
        add(bcustomer);
        
        ltitle.setBounds(193, 50, 150, 50);
        badmin.setBounds(200, 100, 100, 50);
        bcustomer.setBounds(200, 170, 100, 50);
        
        badmin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                Loginpage loginpage = new Loginpage();
                dispose();
                
            }
        });
        
        bcustomer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                CustomerName customer = new CustomerName();
                dispose();
            }
        });
    }
}
