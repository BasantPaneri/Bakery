package src.com.bakery.service;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import src.com.bakery.model.Bill;
import src.com.bakery.model.Cart;
import src.com.bakery.model.Products;
import src.com.bakery.utility.TraceLog;
import src.com.bakery.utility.Utility;

public class DatabaseService {
    public static Products[] product = new Products[10];
    public static int productCount = 0;

    public static Bill [] bill = new Bill[10000];
    public static int billNumber = 0;

    public static Cart cart[] = new Cart[10];
    public static int itemCount = 0;

    private static Connection con ;
    
    private static Connection getConnection(){
        try {
            if(con == null){
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root", "root");
            }
        } catch (Exception e) {
           e.printStackTrace();
        }if (con != null){
            TraceLog.info(Utility.printCurrentLine(), "Connection established.");
        }
        return con;
    }

    public static int createTables(){
        //1. create product
        int rs = 0 ;
        try {
            con = getConnection();
            Statement stmt = con.createStatement();
            rs = stmt.executeUpdate("CREATE TABLE products (id INT PRIMARY KEY AUTO_INCREMENT , name VARCHAR(20) , capacity INT , quantity INT , price DOUBLE)");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
        //2. craate bill - product ref 
    }
    
    public static int addNewProduct(Products product){
        int rs = 0;
        // Insert q 
        try {
            con = getConnection();
            Statement stmt = con.createStatement();
            // rs = stmt.executeUpdate("INSERT INTO products VALUES ( null , " +  + "")
            PreparedStatement prep = con.prepareStatement("INSERT INTO products VALUES (?,?,?,?,?)");
            prep.setString(1, null);
            prep.setString(2, product.getName());
            prep.setInt(3, product.getCapacity());
            prep.setInt(4, product.getQuantity());
            prep.setDouble(5, product.getPrice());

            rs = prep.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }


}
