package src.com.bakery.service;

import src.com.bakery.model.Cart;
import src.com.bakery.model.Products;
import src.com.bakery.utility.TraceLog;
import src.com.bakery.utility.Utility;

public class MyCartService {
    

    public void addToCart(StockManagement stock){ 
       try {
            String addedItem = Utility.getStringInput("name of product you want to add: ");
            TraceLog.info(Utility.printCurrentLine(), "Enter quantity you want.");
            int itemQuantity = Utility.getIntInput();
            Products [] products = DatabaseService.getProducts();
            int i = 0 ; 
            Products p = null;
            TraceLog.info(Utility.printCurrentLine(), "--------------------->"+products.length+ "item Requested '"+ addedItem+"'");
            for (i = 0; i < products.length ; i++) {
                if(products[i] == null) break;
                TraceLog.info(Utility.printCurrentLine(), "--------------------->"+products[i].getName().equalsIgnoreCase(addedItem) + "product "+products[i].toString());
                if (products[i].getName().equalsIgnoreCase(addedItem)){
                    p = products[i];
                    break;
                }
            }

            if(p != null){
                int quantityPicked = getAvavilableProduct( p, itemQuantity);
                DatabaseService.cart[DatabaseService.itemCount++] = new Cart(p, quantityPicked);
                TraceLog.info(Utility.printCurrentLine(),quantityPicked + " "+ products[i].getName()+" added");

                return;
            }
            TraceLog.info(Utility.printCurrentLine(),"Product Not available");
       } catch (Exception e) {
            TraceLog.error(Utility.printCurrentLine(), "Exception while adding product to cart..... ");
            e.printStackTrace();
       }
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
        try {
            if(DatabaseService.itemCount == 0){
                TraceLog.info(Utility.printCurrentLine(), "Cart is empty.");
                return;
            }
            TraceLog.info(Utility.printCurrentLine() , "srNo.  |      item     | quantity");
            int i = 1;
            for (Cart c :DatabaseService.cart) {
                if(c == null){
                    continue;
                }
                String str = i++ +  " | " + c.getProduct().getName() + " | " + c.getMyQuantity(); 
                TraceLog.info(Utility.printCurrentLine() , str);
            }
        } catch (Exception e) {
            TraceLog.error(Utility.printCurrentLine(), "Exception while showing cart..... ");
            e.printStackTrace();
        }
    }
}
