package src.com.bakery.model;

public class Products {
    private int id;
    private String name;
    private int quantity;
    private double price;
    private int capacity;
    
    
    
    public Products(){}
    public Products(int id, String name, int capacity, int quantity, double price){
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.quantity = quantity;
        this.price = price;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public double getPrice() {
        return price;
    }
    public int getCapacity() {
        return capacity;
    }
    @Override
    public String toString() {
        return "Products [name=" + name + ", quantity=" + quantity + ", price=" + price + ", capacity=" + capacity
                + "]";
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
