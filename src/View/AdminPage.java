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
public class AdminPage extends JFrame{
    Connector conn = new Connector();
    
    JLabel lid = new JLabel("Id");
    JLabel lname = new JLabel("Coffe name");
    JLabel lprice = new JLabel("Price");
    
    public JTextField tfid = new JTextField();
    public JTextField tfname = new JTextField();
    public JTextField tfprice = new JTextField();
    
    public JButton btnAdd = new JButton("Add");
    public JButton btnUpdate = new JButton("Update");
    public JButton btnDelete = new JButton("Delete");
    public JButton btnReset = new JButton("Clear");
    public JButton btnBack = new JButton("Back");
    
    JTable table;
    DefaultTableModel dtm;
    JScrollPane scrollPane;
    Object[] columnName = {"Id", "Coffe Name", "Price"};
    
    public AdminPage(){
        int total = conn.getTotalCoffe();
        String data[][] = new String[total][3];
        
        dtm = new DefaultTableModel(columnName, 0);
        table = new JTable(dtm);
        scrollPane = new JScrollPane(table);
        
        setTitle("Coffe List");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        setLayout(null);
        setSize(700,400);
        
        add(scrollPane);
        scrollPane.setBounds(20, 20, 480, 300);
        
        add(lid);
        lid.setBounds(510, 20, 90, 20);
        add(tfid);
        tfid.setBounds(510, 40, 120, 20);
        
        add(lname);
        lname.setBounds(510, 60, 90, 20);
        add(tfname);
        tfname.setBounds(510, 80, 120, 20);
        
        add(lprice);
        lprice.setBounds(510, 100, 90, 20);
        add(tfprice);
        tfprice.setBounds(510, 120, 120, 20);
        
        add(btnAdd);
        btnAdd.setBounds(510, 150, 90, 20);
        
        add(btnUpdate);
        btnUpdate.setBounds(510, 180, 90, 20);
        
        add(btnDelete);
        btnDelete.setBounds(510, 210, 90, 20);
        
        add(btnReset);
        btnReset.setBounds(510, 240, 90, 20);
        
        add(btnBack);
        btnBack.setBounds(510, 270, 90, 20);
        
        data = conn.getCoffeList();
        table.setModel((new JTable(data, columnName)).getModel());
        
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                conn.addCoffe(getId(), getName(), getPrice());
                
                dispose();
                AdminPage admin = new AdminPage();
            }
        });
        
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                conn.updateCoffe(getId(), getName(), getPrice());
                
                dispose();
                AdminPage admin = new AdminPage();
            }
        });
        
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                conn.deleteCoffe(getId());
                
                dispose();
                AdminPage admin = new AdminPage();
            }
        });
        
        btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                tfid.setText("");
                tfname.setText("");
                tfprice.setText("");
            }
        });
        
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                dispose();
                FirstPage back = new FirstPage();
            }
        });
    }
    
    public String getId(){
        return tfid.getText();
    }
    
    public String getName(){
        return tfname.getText();
    }
    
    public String getPrice(){
        return tfprice.getText();
    }
}
