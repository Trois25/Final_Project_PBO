/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;
import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author OWNER
 */
public class Connector {
    String DBurl = "jdbc:mysql://localhost/coffeshop";
    String DBusername = "root";
            String DBpassword = "";
            
            String[] data = new String[3];
            public String name;
            Connection conn;
            Statement stmt;
             public Connector(){
                 try{
                    Class.forName("com.mysql.jdbc.Driver");
                    conn = (Connection)DriverManager.getConnection(DBurl, DBusername, DBpassword);
                    System.out.println("Connection Success");
                 }catch(Exception ex){
                    System.out.println("Connection Failed " + ex);
                 }
                
             }
             
            public String[] Login(String id, String pass){
                try {
                    String query = "SELECT * FROM `login` WHERE id='" + id + "'AND password='" + pass + "'";
                    stmt = conn.createStatement();
                    ResultSet resultSet = stmt.executeQuery(query);
                    
                    resultSet.next();
                    data[0] = resultSet.getString("id");
            
                    stmt.close();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    data[0] = "Error";
                }finally{
                    return data;
                }
            }
            
            public int getTotalCoffe(){
                int totalData = 0;
                try {
                    String query = "SELECT * FROM coffelist";
                    stmt = conn.createStatement();
                    ResultSet resultSet = stmt.executeQuery(query);

                    while(resultSet.next()){
                        totalData++;
                    }
                    stmt.close();
                    return totalData;
                } catch (Exception e) {
                    return 0;
                }
            }
            
            public int getTotalOrder(){
                String cname,sid;
                int totalData = 0;
                try {
                    cname = getName();
                    System.out.println( "dari total order " + cname);
                    String query2 = "SELECT id FROM `struct` WHERE name='" + cname + "'";
                    stmt = conn.createStatement();
                    ResultSet get = stmt.executeQuery(query2);
                    
                    get.next();
                    sid = get.getString("id");
                    
                    String query = "SELECT * FROM `item` WHERE structid='" + sid + "'";
                    stmt = conn.createStatement();
                    ResultSet resultSet = stmt.executeQuery(query);

                    while(resultSet.next()){
                        totalData++;
                    }
                    stmt.close();
                    return totalData;
                } catch (Exception e) {
                    return 0;
                }
            }
            
            public String[][] getCoffeList(){
                int totalcoffe = getTotalCoffe();
                String data[][] = new String [totalcoffe][4];
                try {
                    int totalData = 0;
                    String query = "SELECT * FROM `coffelist`";
                    stmt = conn.createStatement();
                    ResultSet resultSet = stmt.executeQuery(query);

                    while(resultSet.next()){
                        data[totalData][0] = resultSet.getString("Id");
                        data[totalData][1] = resultSet.getString("CoffeName");
                        data[totalData][2] = resultSet.getString("Price");
                        
                        totalData++;
                    }
                    stmt.close();
                } catch (Exception e) {
                    System.out.println("Error");
                } finally{
                    return data;
                }
            }
            
            public String[][] getOrderList(){
                int totalorder = getTotalOrder();
                System.out.println(totalorder);
                String cname,sid;
                String data[][] = new String [totalorder][3];
                String gprice[][] = new String [totalorder][1];
                try {
                    int totalData = 0;
                    
                    cname = getName();
                    System.out.println(cname);
                    String query2 = "SELECT id FROM `struct` WHERE name='" + cname + "'";
                    stmt = conn.createStatement();
                    ResultSet get = stmt.executeQuery(query2);
                    
                    get.next();
                    sid = get.getString("id");
                    
                    String query = "SELECT * FROM `item` WHERE structid='" + sid + "'";
                    stmt = conn.createStatement();
                    ResultSet resultSet = stmt.executeQuery(query);

                    while(resultSet.next()){
                        data[totalData][0] = resultSet.getString("CoffeName");
                        data[totalData][1] = resultSet.getString("amount");
                        gprice[totalData][0] = resultSet.getString("Price");
                        
                        int amount = Integer.parseInt(data[totalData][1]);
                        int price = Integer.parseInt(gprice[totalData][0]);
                        
                        int total = amount * price;
                        data[totalData][2] = Integer.toString(total);
                        
                        totalData++;
                    }
                    stmt.close();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                } finally{
                    return data;
                }
            }
            

            public String getName(){
                String data[]=new String[1];
                String check = null;
                try {
                    String query = "SELECT * FROM `struct`";
                    stmt = conn.createStatement();
                    ResultSet resultSet = stmt.executeQuery(query);
                    
                    while(resultSet.next()){
                        data[0]=resultSet.getString("name");
                    }
                    
                    check = data[0];
                            
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    System.out.println("Failed getting name");
                }finally{
                    return check;
                }
            }
            
            
            public void addCoffe(String id, String name, String price){
                try {
                    String querry = "INSERT INTO `coffelist` (`Id`, `CoffeName`, `Price`)"
                    + " VALUES ('" + id + "','" + name + "','" + price + "')";
                    
                    stmt = conn.createStatement();
                    stmt.executeUpdate(querry);
                    
                    JOptionPane.showMessageDialog(null, "Coffe Added");
                } catch (Exception e) {
                    System.out.println("Error");
                    JOptionPane.showMessageDialog(null, "Price or Id must be number");
                }
            }
            
            public void deleteCoffe(String id){
                try {
                    String querry = "DELETE FROM coffelist WHERE Id='" + id + "'";
                    
                    stmt = conn.createStatement();
                    stmt.executeUpdate(querry);
                    
                    JOptionPane.showMessageDialog(null, "Coffe Deleted");
                } catch (Exception e) {
                    System.out.println("Error");
                }
            }
            
            public void updateCoffe(String id, String name, String price){
                try {
                    String querry = "UPDATE `coffelist` SET CoffeName='" + name + "', Price='" + price + "'" + "WHERE id='" + id + "'";
                    
                    stmt = conn.createStatement();
                    stmt.executeUpdate(querry);
                    
                    JOptionPane.showMessageDialog(null, "Coffe Updated");
                } catch (Exception e) {
                    System.out.println("Error");
                    System.out.println(e.getMessage());
                }
            }
            
            public void InputName(String name){
                try {
                    String querry = "INSERT INTO `struct` (`name`)" + " VALUES ('" + name + "')";
                    
                    stmt = conn.createStatement();
                    stmt.executeUpdate(querry);
                    
                    JOptionPane.showMessageDialog(null, "Success");
                } catch (Exception e) {
                    System.out.println("Success");
                }
            }
            
            public void InputOrder(String id, String amount){
                try {
                    String data1,data2,cname,coffename;
                    String query1 = "SELECT * FROM `coffelist` WHERE Id='" + id + "'";
                    stmt = conn.createStatement();
                    ResultSet price = stmt.executeQuery(query1);
                    price.next();
                    data1 = price.getString("price");
                    coffename = price.getString("CoffeName");
                    
                    cname = getName();
                    System.out.println(cname);
                    String query2 = "SELECT id FROM `struct` WHERE name='" + cname + "'";
                    stmt = conn.createStatement();
                    ResultSet structid = stmt.executeQuery(query2);
                    structid.next();
                    data2 = structid.getString("id");

                    String querry = "INSERT INTO `item` (`structid`, `coffeid`, `amount`, `Price`, `CoffeName`)"
                        + " VALUES ('" + data2 + "','" + id + "','" + amount + "','" + data1 + "','" + coffename + "')";
                    
                    stmt = conn.createStatement();
                    stmt.executeUpdate(querry);
                    
                    JOptionPane.showMessageDialog(null, "Coffe Added");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            public void price(){
                try {
                    String cname = getName();
                } catch (Exception e) {
                }
            }
            
            public void Struct(){
                String data[] = new String[5], tprice;
                int order1[] = null,count = 0, cid;
                try {
                    String cname = getName();
                    
                    tprice = TotalPrice();
                    //insert total
                    String query2 = "UPDATE `struct` SET total='" + tprice + "'" + "WHERE name='" + cname + "'";
                    stmt = conn.createStatement();
                    stmt.executeUpdate(query2);
                    System.out.println("Nama dari struct" + cname);
                    System.out.println("total dari struct" + tprice);
         
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            
            public String TotalPrice(){
                int totalorder = getTotalOrder(), TotalPrice = 0;
                System.out.println(totalorder);
                String cname,sid;
                String data[][] = new String [totalorder][3];
                String gprice[][] = new String [totalorder][1];
                try {
                    int totalData = 0;
                    
                    cname = getName();
                    System.out.println(cname);
                    String query2 = "SELECT id FROM `struct` WHERE name='" + cname + "'";
                    stmt = conn.createStatement();
                    ResultSet get = stmt.executeQuery(query2);
                    
                    get.next();
                    sid = get.getString("id");
                    
                    String query = "SELECT * FROM `item` WHERE structid='" + sid + "'";
                    stmt = conn.createStatement();
                    ResultSet resultSet = stmt.executeQuery(query);

                    while(resultSet.next()){
                        data[totalData][0] = resultSet.getString("CoffeName");
                        data[totalData][1] = resultSet.getString("amount");
                        gprice[totalData][0] = resultSet.getString("Price");
                        
                        int amount = Integer.parseInt(data[totalData][1]);
                        int price = Integer.parseInt(gprice[totalData][0]);
                        
                        int total = amount * price;
                        data[totalData][2] = Integer.toString(total);
                        
                        TotalPrice+=total;
                        
                        totalData++;
                    }
                    stmt.close();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                } finally{
                    return Integer.toString(TotalPrice);
                }
            }

               
}
