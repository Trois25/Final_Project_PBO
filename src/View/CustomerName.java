/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.Connector;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
/**
 *
 * @author OWNER
 */
public class CustomerName extends JFrame{
        Connector conn = new Connector();
        
        JLabel title = new JLabel("Input Your Name");
        JTextField tname = new JTextField();
        JButton bnext = new JButton("Next");
        JButton bback = new JButton("Back");
        
        public CustomerName(){
            setTitle("Customer Name");
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setVisible(true);
            setResizable(false);
            setLayout(null);
            setSize(400,250);
            
            add(title);
            add(bnext);
            add(tname);
            add(bback);
            
            title.setBounds(145, 30, 100, 30);
            tname.setBounds(135, 70, 120, 30);
            bnext.setBounds(115, 120, 80, 30);
            bback.setBounds(210, 120, 80, 30);
            
            bnext.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent arg0) {
                    conn.InputName(getName());
                    CustomerPage page = new CustomerPage();
                    dispose();
                }
            });
            
            bback.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent arg0) {
                    dispose();
                    FirstPage back = new FirstPage();
                }
            });
        }
        
        public String getName(){
            return tname.getText();
        }
}
