package src.com.bakery.service;

import src.com.bakery.model.Cart;
import src.com.bakery.model.Products;
import src.com.bakery.utility.TraceLog;
import src.com.bakery.utility.Utility;

public class MyCartService {
    Cart cart[] = new Cart[10];
    int itemCount = 0;

    public String addToCart(StockManagement stock){
        String addedItem = Utility.getStringInput("name of product you want to add: ");
        TraceLog.info(Utility.printCurrentLine(), "Enter quantity you want.");
        int itemQuantity = Utility.getIntInput();
        Products [] products = stock.getProducts();
        int i = 0 ; 
        Products p = null;

        for (i = 0; i < stock.getProductCount(); i++) {
            if (products[i].getName().equalsIgnoreCase(addedItem)){
                p = products[i];
                break;
            }
        }
        if(p != null){
            int quantityPicked = getAvavilableProduct( p, itemQuantity);
            cart[itemCount++] = new Cart(p, quantityPicked);
            TraceLog.info(Utility.printCurrentLine(),quantityPicked + " "+ products[i].getName()+" added");
            return products[i].getName();
        }
        TraceLog.info(Utility.printCurrentLine(),"Product Not available");
        return "Product Not available";
    }


    public int getAvavilableProduct(Products product, int qty){
        // 50 - 40 = 10
        int currentAvailableStock = product.getQuantity();
        if(currentAvailableStock>=qty){
            int remainingValue = currentAvailableStock-qty;
            product.setQuantity(remainingValue);
            return qty;
        }
        // 50 - 60
        product.setQuantity(0);
        return currentAvailableStock;
    }

    public void showCart(){
        if(itemCount == 0){
            TraceLog.info(Utility.printCurrentLine(), "Cart is empty.");
            return;
        }
        TraceLog.info(Utility.printCurrentLine() , "srNo.  |      item     | quantity");
        int i = 1;
        for (Cart c :this.cart) {
            if(c == null){
                continue;
            }
            String str = i++ +  " | " + c.getProduct().getName() + " | " + c.getMyQuantity(); 
            TraceLog.info(Utility.printCurrentLine() , str);
        }
    }
}
