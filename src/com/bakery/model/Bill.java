package src.com.bakery.model;

public class Bill {
    private int id;
    private String customerName;
    private int quantity;
    private Cart cart;
    private double amount;

    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
    Bill(){}
    public Bill(int id, String customerName, int quantity, Cart cart, double amount) {
        this.id = id;
        this.customerName = customerName;
        this.quantity = quantity;
        this.cart = cart;
        this.amount = amount;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Cart getCart() {
        return cart;
    }
    public void setCart(Cart cart) {
        this.cart = cart;
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