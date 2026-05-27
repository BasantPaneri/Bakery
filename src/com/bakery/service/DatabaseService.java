package src.com.bakery.service;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import src.com.bakery.model.Bill;
import src.com.bakery.model.Cart;
import src.com.bakery.model.Products;
import src.com.bakery.utility.Repository;
import src.com.bakery.utility.TraceLog;
import src.com.bakery.utility.Utility;

public class DatabaseService {
    // public static Products[] product = new Products[10];
    // public static int productCount = 0;

    // public static Bill [] bill = new Bill[10000];
    // public static int billNumber = 0;

    public static Cart cart[] = new Cart[20];
    public static int itemCount = 0;

    private static Connection con ;
    private static final String url = "jdbc:mysql://localhost:3306/demo";
    private static final String user = "root";
    private static final String password ="root";
    private static final int PRODUCT_ARRAY_SIZE = 50;
    private static final int BILL_ARRAY_SIZE = 50;
    
    private static Connection getConnection(){
        try {
            if(con == null){
                con = DriverManager.getConnection(url, user, password);
                if (con != null){
                    TraceLog.info(Utility.printCurrentLine(), "Connection established.");
                }
            }
        } catch (Exception e) {
           e.printStackTrace();
        }
        return con;
    }

    public static int createTables(){
        int rs = 0 ;
        try {
            con = getConnection();
            Statement stmt = con.createStatement();
            //1. create product
            rs = stmt.executeUpdate("CREATE TABLE products (id INT PRIMARY KEY AUTO_INCREMENT , name VARCHAR(20) , capacity INT , quantity INT , price DOUBLE)");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            con = getConnection();
            Statement stmt = con.createStatement();
            //2. craate bill - product ref 
            rs = stmt.executeUpdate("CREATE TABLE bill(id INT PRIMARY KEY AUTO_INCREMENT, customerName VARCHAR(20), P_Id INT, quantity INT, amount DOUBLE, FOREIGN KEY (P_Id) REFERENCES products(id))");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
    
    public static int addNewProduct(Products product){
        int rs = 0;
        // Insert q 
        try {
            con = getConnection();
            PreparedStatement prep = con.prepareStatement(Repository.INSERT_PRODUCT);
            prep.setString(1, null);
            prep.setString(2, product.getName());
            prep.setInt(3, product.getCapacity());
            prep.setInt(4, product.getQuantity());
            prep.setDouble(5, product.getPrice());
            rs = prep.executeUpdate();
        } catch (Exception e) {
            TraceLog.error(Utility.printCurrentLine(), "Exception while adding product in DB ..... ");
            e.printStackTrace();
        }
        return rs;
    }

    public static int updateProduct(Products product){
        int rs = 0;
        try {
            con = getConnection();
            Statement stmt = con.createStatement();
            rs = stmt.executeUpdate("update products set quantity=" + product.getQuantity()+ " where name = '"+ product.getName()+"'");
        } catch (Exception e) {
            TraceLog.error(Utility.printCurrentLine(), "Exception while updating product in DB ..... ");
            e.printStackTrace();
        }
        return rs;
    }

    public static void showProductsToOwner(){
        try {
            String array[] = new String[20];
            int ind = 0;
            Statement stmt = con.createStatement();
            ResultSet rst = stmt.executeQuery(Repository.DESC_PRODUCT_TABLE);
            String header = "";
            while (rst.next()) {
                String field = rst.getString("field");
                array[ind++] = field;
                header += array[ind-1]+ "\t";
            }
            System.out.println(header);

            ResultSet rs = stmt.executeQuery(Repository.GET_ALL_PRODUCT);
             while (rs.next()) {
                 for (int i = 0; i < ind; i++) {
                    System.out.print(rs.getString(array[i]) + "\t");
                 }
                 System.out.println();  
             }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // change desc to direct printing 
    public static boolean showProductsToCustomer(){
        try {
            String array[] = new String[20];
            int ind = 0;
            Statement stmt = con.createStatement();
            ResultSet rst = stmt.executeQuery(Repository.DESC_PRODUCT_TABLE);
            String header = "";
            while (rst.next()) {
                String field = rst.getString("field");
                if(field.equalsIgnoreCase("capacity") || field.equalsIgnoreCase("id")) continue;
                array[ind++] = field;
                header += array[ind-1]+ "\t";
            }
            System.out.println(header);

            ResultSet rs = stmt.executeQuery(Repository.GET_ALL_PRODUCT);
            int count = 0 ;
            while (rs.next()) {
                 for (int i = 0; i < ind; i++) {
                     System.out.print(rs.getString(array[i]) + "\t");
                 }
                 System.out.println();  
                 count++ ;
             }
             if(count == 0 ) return false;
        } catch (Exception e) {
            TraceLog.error(Utility.printCurrentLine(), "Exception while showing product to customer..... ");
            e.printStackTrace();
        }
        return true;
    }

    public static Products[] getProducts(){
        Products[] products = new Products[PRODUCT_ARRAY_SIZE];
        try {
            Statement stmt = con.createStatement();        
            ResultSet rs = stmt.executeQuery(Repository.GET_ALL_PRODUCT);
            int count = 0 ;
            while (rs.next()) {
                products[count++ ] = getProductFromResultSet(rs);
            }
        } catch (Exception e) {
            TraceLog.error(Utility.printCurrentLine(), "Exception while geting Product from DB ..... ");
            e.printStackTrace();
        }
        return products;
    }

    static Products getProductFromResultSet(ResultSet rs){
        Products product = new Products();
        try {
            int id = rs.getInt("id");
            String name  = rs.getString("name");
            int quantity = rs.getInt("quantity");
            double price = rs.getDouble("price");
            int capacity = rs.getInt("capacity");
            product.setId(id);
            product.setQuantity(quantity);
            product.setName(name);
            product.setCapacity(capacity);
            product.setPrice(price);
            TraceLog.info(Utility.printCurrentLine(), product.toString());
        } catch (Exception e) {
            TraceLog.error(Utility.printCurrentLine(), "Exception while parcing DB product to Product..... ");
            e.printStackTrace();
        }
        return product;
    }
    
    public static int punchEntryInBill(Bill bill){
        int rs = 0;
        try {
            con = getConnection();
            PreparedStatement prep = con.prepareStatement(Repository.INSERT_BILL);
            // prep.setInt(1, 1);
            prep.setString(1, bill.getCustomerName());
            prep.setInt(2, bill.getpId());
            prep.setInt(3, bill.getQuantity());
            prep.setDouble(4, bill.getAmount());
            TraceLog.error(Utility.printCurrentLine(), prep+" || "+bill.toString());
            
            rs = prep.executeUpdate();

        } catch (Exception e) {
            TraceLog.error(Utility.printCurrentLine(), "Exception while adding bill Entry in DB ..... ");
            e.printStackTrace();
        }
        return rs;
    }

    public static Bill[] getAllBillEntry(){
        Bill[] bills = new Bill[BILL_ARRAY_SIZE];
        try {
            con = getConnection();
            PreparedStatement prep = con.prepareStatement(Repository.GET_ALL_PRODUCT_AND_BILL_DETAILS_WITH_PID);
            ResultSet rs = prep.executeQuery();
            int i = 0;
            while (rs.next()) {
                bills[i++] = getBillFromResultSet(rs);
            }
        } catch (Exception e) {
            TraceLog.error(Utility.printCurrentLine(), "Exception while getting bill Entry from DB ..... ");
            e.printStackTrace();
        }
        return bills;
    }

    public static Bill getBillFromResultSet(ResultSet rs){
        Bill bill = null;
        try {
            int id = rs.getInt("id");
            String customerName = rs.getString("customerName");
            String productName = rs.getString("name");
            int qty = rs.getInt("quantity");
            double price = rs.getDouble("price");
            double amount = rs.getDouble("total");
            bill  = new Bill(id, customerName, qty, amount, productName, price);
        } catch (Exception e) {
            TraceLog.error(Utility.printCurrentLine(), "Exception while Parsing bill Entry ..... ");
            e.printStackTrace();
        }
        return bill;
    }

}
