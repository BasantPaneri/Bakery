package src.com.bakery.utility;

public class Repository {
    //CREATE QUERY
    public static String CREATE_bill_TABLE = "CREATE TABLE bill(id INT PRIMARY KEY AUTO_INCREMENT, customerName VARCHAR(20), P_Id INT, quantity INT, amount DOUBLE, FOREIGN KEY (P_Id) REFERENCES products(id))";
    public static String CREATE_products_TABLE = "CREATE TABLE products (id INT PRIMARY KEY AUTO_INCREMENT , name VARCHAR(20) , capacity INT , quantity INT , price DOUBLE)";

    // Product Query 
    public static String INSERT_PRODUCT = "INSERT INTO products VALUES (?,?,?,?,?)"; 
    public static String GET_ALL_PRODUCT = "SELECT * FROM products"; 
    public static String DESC_PRODUCT_TABLE = "DESC products";
    
    // Bill Query
    public static String INSERT_BILL = "INSERT INTO bill VALUES (null,?,?,?,?)"; 
    public static String GET_ALL_BILL = "SELECT * FROM bill"; 
    public static String DESC_BILL_TABLE = "DESC bill";
    public static String GET_ALL_PRODUCT_AND_BILL_DETAILS_WITH_PID = "SELECT b.id, b.customerName, p.name, b.quantity, p.price, b.quantity * p.price AS total FROM bill b JOIN products p ON b.P_Id = p.id";
    public static String DROP_TABLE_bill = "DROP TABLE bill";
    public static String DROP_TABLE_products = "DROP TABLE products";
}
