package src.com.bakery.model;

public class Cart {
    private int myQuantity;
    private Products product;

    Cart(){}
    public Cart(Products product, int myQuantity) {
        this.myQuantity = myQuantity;
        this.product = product;
    }
    public int getMyQuantity() {
        return this.myQuantity;
    }
    public Products getProduct() {
        return product;
    }
    
}
