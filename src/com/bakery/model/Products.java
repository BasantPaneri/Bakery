package src.com.bakery.model;

public class Products {
    private String name;
    private int quantity;
    private double price;
    private int capacity;



    public Products(){}
    public Products(String name, int capacity, int quantity, double price){
        this.name = name;
        this.capacity = capacity;
        this.quantity = quantity;
        this.price = price;
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
}
