package src.com.bakery.model;

public class Bill {
    private int id;
    private String customerName;
    private int quantity;
    private Products product;
    private double amount;

    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
    Bill(){}
    public Bill(int id, String customerName, int quantity, Products product, double amount) {
        this.id = id;
        this.customerName = customerName;
        this.quantity = quantity;
        this.product = product;
        this.amount = amount;
    }
    public Products getProduct() {
        return product;
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
}