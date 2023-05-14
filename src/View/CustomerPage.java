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
public class CustomerPage extends JFrame{
    Connector conn = new Connector();
    
    JLabel cname = new JLabel(" == HORSE CAFE == ");
    
    JLabel lid = new JLabel("Coffee Id");
    JTextField tid = new JTextField();
    
    JLabel lamount = new JLabel("Amount");
    JTextField tamount = new JTextField();
    
    JButton border = new JButton("ADD");
    JButton bdone = new JButton("ORDER");
    
    JTable table;
    DefaultTableModel model;
    JScrollPane scrollPane;
    Object[] columnName = {"Id", "Menu", "Price"};
    
    public CustomerPage(){
        int total = conn.getTotalCoffe();
        String data[][] = new String[total][3];
        
        model = new DefaultTableModel(columnName, 0);
        table = new JTable(model);
        scrollPane = new JScrollPane(table);
        
        setTitle("Orde Page");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        setLayout(null);
        setSize(500,600);
        
        add(scrollPane);
        scrollPane.setBounds(20, 80, 450, 300);
        
        add(cname);
        cname.setBounds(180, 15, 150, 50);
        
        add(lid);
        lid.setBounds(70, 395, 80, 50);
        add(tid);
        tid.setBounds(130, 410, 120, 20);
        
        add(lamount);
        lamount.setBounds(70, 430, 80, 50);
        add(tamount);
        tamount.setBounds(130, 445, 120, 20);
        
        add(border);
        border.setBounds(300, 410, 90, 20);
        add(bdone);
        bdone.setBounds(300, 445, 90, 20);
        
        data = conn.getCoffeList();
        table.setModel((new JTable(data, columnName)).getModel());
        
        border.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                conn.InputOrder(getid(), getamount());
                tid.setText("");
                tamount.setText("");
            }
        });
        
        bdone.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                Struct struct = new Struct();
                conn.Struct();
                dispose();
            }
        });
    }
    
    public String getid(){
        return tid.getText();
    }
    
    public String getamount(){
        return tamount.getText();
    }
}
