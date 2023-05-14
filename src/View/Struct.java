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
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author OWNER
 */
public class Struct extends JFrame{
    Connector conn = new Connector();
    
    JLabel lheader = new JLabel(" == HORSE CAFE == ");
    
    JLabel lname = new JLabel("Customer Name : ");
    JLabel lcustname = new JLabel(conn.getName());
    
    JLabel ltotal = new JLabel("Total : ");
    JLabel ltotal2 = new JLabel(conn.TotalPrice());
    
    JButton bok = new JButton("OK");
    
    JTable table;
    DefaultTableModel model;
    JScrollPane scrollpane;
    Object[] columnName = {"Order", "Amount", "Price"};
    
    public Struct(){
        int total = conn.getTotalOrder();
        String data[][] = new String[total][3];
        
        model = new DefaultTableModel(columnName, 0);
        table = new JTable(model);
        scrollpane = new JScrollPane(table);
        
        setTitle("Your Order");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        setLayout(null);
        setSize(500,600);
        
        add(scrollpane);
        scrollpane.setBounds(20, 105, 450, 300);
        
        add(lheader);
        lheader.setBounds(180, 15, 150, 50);
        
        add(lname);
        lname.setBounds(20, 60, 150, 50);
        
        add(lcustname);
        lcustname.setBounds(130, 60, 100, 50);
        
        add(ltotal);
        ltotal.setBounds(20, 395, 100, 50);
        
        add(ltotal2);
        ltotal2.setBounds(80, 395, 100, 50);
        
        add(bok);
        bok.setBounds(195, 450, 100, 50);
        
        data = conn.getOrderList();
        table.setModel((new JTable(data, columnName)).getModel());
        
        bok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                dispose();
                FirstPage first = new FirstPage();
            }
        });
    }
}
