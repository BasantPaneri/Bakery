package src.com.bakery.utility;

public class Repository {
    // Product Query 
    public static String INSERT_PRODUCT = "INSERT INTO products VALUES (?,?,?,?,?)"; 
    public static String GET_ALL_PRODUCT = "SELECT * FROM products"; 
    public static String DESC_PRODUCT_TABLE = "DESC products";
    public static String UPDATE_PRODUCT = "update products set quantity = 21 where name = 'burger'";
    
    // Bill Query
    public static String INSERT_BILL = "INSERT INTO bill VALUES (null,?,?,?,?)"; 
    public static String GET_ALL_BILL = "SELECT * FROM bill"; 
    public static String DESC_BILL_TABLE = "DESC bill";
    public static String GET_ALL_PRODUCT_AND_BILL_DETAILS_WITH_PID = "SELECT b.id, b.customerName, p.name, b.quantity, p.price, b.quantity * p.price AS total FROM bill b JOIN products p ON b.P_Id = p.id";
}
