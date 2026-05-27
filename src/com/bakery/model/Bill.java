package src.com.bakery.model;


public class Bill {
    private int id;
    private String customerName;
    private int quantity;
    private int pId;
    private double amount;
    private double price;
    private String productName;
    
    public Bill(){}
    public Bill(int id, String customerName, int quantity, int pId, double amount) {
        this.id = id;
        this.customerName = customerName;
        this.quantity = quantity;
        this.pId = pId;
        this.amount = amount;
    }
    
    public Bill(int id, String customerName, int quantity, double amount, String productName,double price) {
        this.id = id;
        this.customerName = customerName;
        this.price = price;
        this.quantity = quantity;
        this.amount = amount;
        this.productName = productName;
    }
    
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public int getpId() {
        return pId;
    }
    public void setpId(int pId) {
        this.pId = pId;
    }
    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getCustomerName() {
        return customerName;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    @Override
    public String toString() {
        return "Bill [id=" + id + ", customerName=" + customerName + ", quantity=" + quantity + ", pId=" + pId
                + ", amount=" + amount + ", price=" + price + ", productName=" + productName + "]";
    }
}