package src.com.bakery.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import javax.swing.text.Utilities;

import src.com.bakery.service.DatabaseService;
import src.com.bakery.model.*;
import src.com.bakery.utility.TraceLog;
import src.com.bakery.utility.Utility;

public class DBController1 {
    public static void main(String[] args) {
        // Connection con = null;
        // try {
        //     con = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root", "root");
        //     Statement stmt = con.createStatement();
        //     TraceLog.info(Utility.printCurrentLine(), "1.create a table");
        //     TraceLog.info(Utility.printCurrentLine(), "2. drop a table");
        //     TraceLog.info(Utility.printCurrentLine(), "3. delete all rows.");
        //     TraceLog.info(Utility.printCurrentLine(), "4. insert a row");
        //     TraceLog.info(Utility.printCurrentLine(), "5. show all info according to name");
        //     TraceLog.info(Utility.printCurrentLine(), "6. show according to input");
        //     TraceLog.info(Utility.printCurrentLine(), "enter option");
        //     int option = Utility.getIntInput();
        //     String tableName = Utility.getStringInput("Table Name");
        //     if (option == 1 ){
        //         TraceLog.info(Utility.printCurrentLine(), "enter number of column: ");
        //         int column = Utility.getIntInput();
        //         int num = 1;
        //         while (num <= column){
        //             String temp = num+"";
        //             temp = Utility.getStringInput("column name");
        //             TraceLog.info(Utility.printCurrentLine(), "SELECT DATATYPE");
        //             TraceLog.info(Utility.printCurrentLine(), "1. INT");
        //             TraceLog.info(Utility.printCurrentLine(), "2. VARCHAR()");
        //             option = Utility.getIntInput();
        //             if (option == 1){
                        
        //             }
        //             num++;
        //         }
                
        //         int rss = stmt.executeUpdate( "CREATE TABLE "+ tableName +" ("+ "id INT  ," +"name VARCHAR(50)," + "phone VARCHAR(13)" + ")");
        //          TraceLog.info(Utility.printCurrentLine(), "rows affected: " + rss);
        //     }else if (option == 2){
        //         int rss = stmt.executeUpdate("DROP TABLE "+ tableName);
        //          TraceLog.info(Utility.printCurrentLine(), "rows affected: " + rss);
        //     }else if (option ==3 ){
        //         int rss = stmt.executeUpdate("DELETE FROM "+ tableName);
        //          TraceLog.info(Utility.printCurrentLine(), "rows affected: " + rss);
        //     }else if (option == 4){
        //         String name = Utility.getStringInput("name");
        //         String phone = Utility.getStringInput("phone number");
        //         int rss = stmt.executeUpdate("INSERT INTO "+ tableName + "(name , phone) VALUES ('" + name + "','" + phone + "')" );
        //          TraceLog.info(Utility.printCurrentLine(), "rows affected: " + rss);
        //     }else if (option == 5) {
        //         String name = Utility.getStringInput("name");
        //          ResultSet rs = stmt.executeQuery("SELECT" + name + "FROM customers");
        //     boolean temp = false;
        //     do {
        //         if(temp){
        //             TraceLog.info(
        //                 // Utility.printCurrentLine(),rs.getString("phone_number")
        //                 Utility.printCurrentLine(),rs.getInt("id")+" | "+ rs.getString("phone")+ " | " +rs.getString("name") 
        //             );        
        //         }temp = true;
        //     } while (rs.next());
        //     }
            
            
           
        //     TraceLog.info(Utility.printCurrentLine(), "created table");
           
        // } catch (Exception e) {
        //     e.printStackTrace();       
        // }
        // finally{
        //     try {
        //         con.close();
        //     } catch (Exception e) {
        //         e.printStackTrace();
        //     }
        // }

    //     Connection con = null;
    //     try {
    //         con = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root", "root");
    //         Statement stmt = con.createStatement();
    //         Statement stmtt = con.createStatement();
    //         // String [] tables = new String[5];
    //         int tableInd = 0 ;
    //         while (true) {
    //             TraceLog.info(Utility.printCurrentLine(), "1. create a table");
    //             TraceLog.info(Utility.printCurrentLine(), "2. drop a table");
    //             TraceLog.info(Utility.printCurrentLine(), "3. delete based on id.");
    //             TraceLog.info(Utility.printCurrentLine(), "4. insert a row");
    //             TraceLog.info(Utility.printCurrentLine(), "5. show table");
    //             TraceLog.info(Utility.printCurrentLine(), "enter option");
    //             int option = Utility.getIntInput();
    //             String tableName = null;
                
    //             if(option == 1){
    //                 tableName = Utility.getStringInput("Table Name");
    //                 TraceLog.info(Utility.printCurrentLine(), "enter number of column: ");
    //                 int column = Utility.getIntInput();
    //                 int num = 1;
    //                 String createQ = "CREATE TABLE "+tableName+" ( ";
    //                 while (num <= column){
    //                     String temp = Utility.getStringInput("column name");
    //                     String dataType = getDataType(temp);
    //                     if(num == column){
    //                         createQ = createQ + temp +" "+dataType+" ";
    //                     } else {
    //                         createQ = createQ + temp +" "+dataType+" , ";
    //                     }
    //                     num++;
    //                 }
    //                 createQ = createQ + " )";
    //                 TraceLog.info(Utility.printCurrentLine(), "Querry Created : "+createQ);
    //                 int rs = stmt.executeUpdate(createQ);
    //                 TraceLog.info(Utility.printCurrentLine(), "Rows Effected : "+rs);
    //             } else if (option == 2){
    //                 ResultSet rst = stmt.executeQuery("SHOW TABLES");
    //                 while(rst.next()){
    //                     TraceLog.info(
    //                             // Utility.printCurrentLine(),rs.getString("phone_number")
    //                             Utility.printCurrentLine(),rst.getString(1) 
    //                     ); 
    //                 }
    //                 tableName = Utility.getStringInput("Table Name");
    //                 int rss = stmt.executeUpdate("DROP TABLE "+ tableName);
    //                 TraceLog.info(Utility.printCurrentLine(), "rows affected: " + rss);
    //             } else if (option == 3) {
    //                 ResultSet rst = stmt.executeQuery("SHOW TABLES");
    //                 while(rst.next()){
    //                     TraceLog.info(
    //                             // Utility.printCurrentLine(),rs.getString("phone_number")
    //                             Utility.printCurrentLine(),rst.getString(1) 
    //                     ); 
    //                 }
    //                 tableName = Utility.getStringInput("Enter table name");
    //                 TraceLog.info(Utility.printCurrentLine(),"Enter ID to delete");
    //                 int id = Utility.getIntInput();
    //                 String query = "DELETE FROM " + tableName + " WHERE id = " + id;
    //                 int rowsAffected = stmt.executeUpdate(query);
    //                 if (rowsAffected > 0) {
    //                     TraceLog.info(Utility.printCurrentLine(),"Row deleted successfully.");
    //                 } else {
    //                     TraceLog.info( Utility.printCurrentLine(), "No row found with ID " + id);
    //                 }
    //             } else if ( option == 4) {
    //                 ResultSet rst = stmt.executeQuery("SHOW TABLES");
    //                 while(rst.next()){
    //                     TraceLog.info(
    //                         // Utility.printCurrentLine(),rs.getString("phone_number")
    //                         Utility.printCurrentLine(),rst.getString(1) 
    //                     ); 
    //                 }
    //                 tableName = Utility.getStringInput("Table Name");
    //                 String insertQ = "INSERT INTO "+tableName+" VALUES ( null ";
    //                 rst = stmt.executeQuery("DESC "+tableName);

    //                 while(rst.next()){
    //                     if(rst.getString("Field").equalsIgnoreCase("id")){
    //                         continue;
    //                     }
    //                     String temp  = Utility.getStringInput("Enter "+ rst.getString("Field")+" : ");
    //                     insertQ = insertQ + ", '"+ temp+"'";
    //                 }
    //                 insertQ = insertQ + " )";
    //                 TraceLog.info(Utility.printCurrentLine(), "Query Created : "+insertQ);
    //                 int rs = stmt.executeUpdate(insertQ);
    //                 TraceLog.info(Utility.printCurrentLine(), "Rows Effected : "+rs);
    //             } else if (option == 5 ) {
    //                  ResultSet rst = stmt.executeQuery("SHOW TABLES");
    //                 while(rst.next()){
    //                     TraceLog.info(
    //                         // Utility.printCurrentLine(),rs.getString("phone_number")
    //                         Utility.printCurrentLine(),rst.getString(1) 
    //                     ); 
    //                 }
    //                 tableName = Utility.getStringInput("Table Name");
    //                 ResultSet rs = stmt.executeQuery("SELECT * FROM " + tableName);
    //                 ResultSet rstt = stmtt.executeQuery("DESC "+tableName);
    //                 // printResultSet(rs); 
    //                 printTable(tableName,rs,rstt, stmt );
    //             }
    //         }
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //     }
    // }

    // public static String getDataType(String val){
    //     if(val != null && val.equalsIgnoreCase("id")){
    //         return "INT  AUTO_INCREMENT PRIMARY KEY";
    //     }
    //     return "VARCHAR(100)";
    // }

    // public static void printResultSet(ResultSet rs) {
    //     try {
    //         ResultSetMetaData meta = rs.getMetaData();
    //         int columnCount = meta.getColumnCount();

    //         // Print rows
    //         while (rs.next()) {
    //             for (int i = 1; i <= columnCount; i++) {
    //                 System.out.print(rs.getString(i) + "\t");
    //             }
    //             System.out.println();  
    //         }

    //     } catch (Exception e) {
    //         e.printStackTrace();
    //     }
    // }  
    
    // public static void printTable(String tableName,ResultSet rs,ResultSet rst, Statement stmt ){
       
    //     try {
    //         // ResultSet rst = stmt.executeQuery("DESC "+tableName);
    //         String array [] = new String[10];
    //         int ind = 0 ;
    //         while (rst.next()) {
    //             array[ind++] = rst.getString("Field");
    //             TraceLog.info(Utility.printCurrentLine(), ""+array[ind-1]);
    //         }
    //         // ResultSet rs = stmt.executeQuery("SELECT * FROM " + tableName);
    //         while (rs.next()) {
    //             for (int i = 0; i < ind; i++) {
    //                 System.out.print(rs.getString(array[i]) + "\t");
    //             }
    //             System.out.println();  
    //         }
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //     }
    DatabaseService.addNewProduct(new Products("burger", 150, 2, 79));
    } 
}
