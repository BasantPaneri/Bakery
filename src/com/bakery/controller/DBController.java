package src.com.bakery.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import src.com.bakery.utility.TraceLog;
import src.com.bakery.utility.Utility;

public class DBController {
    public static void main(String[] args) {
        TraceLog.info(Utility.printCurrentLine(), "hello");
        Connection con = null;
        try {
           con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/demo",
                    "root",
                    "root"
            );

            TraceLog.info(Utility.printCurrentLine(), "Connected Successfully");
            
            Statement stmt = con.createStatement();
            
            // ResultSet rs = stmt.executeQuery("SELECT * FROM employees WHERE SALARY > 30000" );
            int rss = stmt.executeUpdate("INSERT INTO employees VALUES (null, 'Shivam', '8765432567', 'shivam@gmail.com', 87638)" );
            // int rss = stmt.executeUpdate("delete from employees where name = 'Shivam'" );
            TraceLog.info(Utility.printCurrentLine(), "Rows effected "+ rss);
            ResultSet rs = stmt.executeQuery("SELECT * FROM employees" );
            if(rss > 0){

                while(rs.next()) {
                    System.out.println(
                            rs.getInt("employee_id")
                                + "  |  "
                                + rs.getString("name")
                                + "  |  "
                                + rs.getInt("salary")
                                + "  |  "
                                + rs.getString("email_id")
                                + "  |  "
                                + rs.getString("phone_number")
                         );
                     }

            } else {
                TraceLog.info(Utility.printCurrentLine(), "Not Save");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try{
                con.close();
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
