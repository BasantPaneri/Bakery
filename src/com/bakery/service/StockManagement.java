package src.com.bakery.service;

import src.com.bakery.model.Bill;
import src.com.bakery.model.Products;
import src.com.bakery.utility.TraceLog;
import src.com.bakery.utility.Utility;

public class StockManagement {
    
    
    // Products item = new Products(null, productCount, productCount, productCount);
    // public Products[] getProducts() {
    //     return DatabaseService.product;
    // }

    // public int getProductCount() {
    //     return DatabaseService.productCount;
    // }
    

    public void addProduct(){
        try {
            String newProduct = Utility.getStringInput("Product name");
            Products [] products = DatabaseService.getProducts();                    
            for(int i = 0 ; i < products.length ; i++){

                if(products[i] != null && newProduct.equalsIgnoreCase(products[i].getName())){
                    TraceLog.info(Utility.printCurrentLine(), "Product already present.");
                    return;
                }else if (products[i] == null){
                    int capacity = 0;
                    // DatabaseService.productCount = i;
                    TraceLog.info(Utility.printCurrentLine(), "enter capacity.");
                    capacity = Utility.getIntInput();
                    TraceLog.info(Utility.printCurrentLine(), "please enter int value only.");
                    int qty ;
                    while(true){
                        TraceLog.info(Utility.printCurrentLine(), "enter quantity.");
                        qty = Utility.getIntInput();
                        if(qty > capacity){
                            TraceLog.info(Utility.printCurrentLine(), "not enough capacity(maximum-" + capacity +")");
                        }else break;
                    }
                    TraceLog.info(Utility.printCurrentLine(), "enter price.");
                    double price = Utility.getDoubleInput();
                    Products product = new Products(1,newProduct, capacity, qty, price);
                    DatabaseService.addNewProduct(product);
                    TraceLog.info(Utility.printCurrentLine(), "Product Added ---> "+product.toString());
                    TraceLog.info(Utility.printCurrentLine(), "-------new product added---------");
                    break;
                }
            }
        } catch (Exception e) {
            TraceLog.error(Utility.printCurrentLine(), "Exception while Adding Product ..... ");
            e.printStackTrace();
        }
    }

    // public void show(){
    //     if(DatabaseService.productCount == 0){
    //         TraceLog.info(Utility.printCurrentLine(), "nothing to show");
    //         return;
    //     }
    //     TraceLog.info(Utility.printCurrentLine(), "---------------available products---------------");
    //     for(int i = 0; i < DatabaseService.productCount; i++) {
    //         Products p = DatabaseService.product[i];
    //         TraceLog.info(Utility.printCurrentLine(), "Product : " + p.getName() + "| Capacity : " + p.getCapacity() + "| quantity : " + p.getQuantity() + "| Price : " + p.getPrice());
    //     }
    // }

    // public boolean showToCustomer(){
    //     if(DatabaseService.productCount == 0){
    //         TraceLog.info(Utility.printCurrentLine(), "nothing to show");
    //         return false;
    //     }
    //     TraceLog.info(Utility.printCurrentLine(), "---------------available products---------------");
    //     for(int i = 0; i < DatabaseService.productCount; i++) {
    //         Products p = DatabaseService.product[i];
    //         TraceLog.info(Utility.printCurrentLine(), "Product : " + p.getName() + "| quantity : " + p.getQuantity() + "| Price : " + p.getPrice());
    //     }
    //     return true;
    // }

    public void reStock(){
        try {
            boolean found = false;
            String productName = Utility.getStringInput("product name");
            Products[] product = DatabaseService.getProducts();

            for(int i = 0 ; i < product.length ; i++){
                if(product[i] == null) break; 

                Products p = product[i];
                if(productName.equalsIgnoreCase(p.getName())){
                    found = true;
                    TraceLog.info(Utility.printCurrentLine(), p.toString());
                    TraceLog.info(Utility.printCurrentLine(), "enter quantity to add: ");
                    int newQty;
                    while (true){
                        newQty = Utility.getIntInput();
                        if (newQty + p.getQuantity() > p.getCapacity() ){
                            TraceLog.info(Utility.printCurrentLine(), "not enough capacity- you can add " + (p.getCapacity() - p.getQuantity()) + "pieces only");
                        }else break;
                    }
                    p.setQuantity(p.getQuantity()+ newQty);
                    int affectiedRows =   DatabaseService.updateProduct(p);
                    if(affectiedRows > 0) {
                        TraceLog.info(Utility.printCurrentLine(), "Updated quantity : " + p.getQuantity());
                        TraceLog.info(Utility.printCurrentLine(), "-----------product re-stocked successfully----------");
                    } else if (affectiedRows == 0 ) {
                        TraceLog.info(Utility.printCurrentLine(), "-----------Nothing Has Been Updated----------");
                    } 
                    break;
                }            
            }
            if(!found) TraceLog.info(Utility.printCurrentLine(), "--------------Product not found-please add it------------");
        } catch (Exception e) {
            TraceLog.error(Utility.printCurrentLine(), "Exception while re Stocking ..... ");
            e.printStackTrace();
        }        
    }

    public void showBillBook(BillService billService){
       try {
         String total =  "Total Revenue";
        TraceLog.info(Utility.printCurrentLine(), "===========Sales Book Start===============");
        TraceLog.info(Utility.printCurrentLine(), "===================");
        TraceLog.info(Utility.printCurrentLine(),  total +" : "+ billService.getTotalRevenue());
        TraceLog.info(Utility.printCurrentLine(), "===================");
        
        String sr =  "SrNo";
        String productName =  "Product Name";
        String qty =  "Qty";
        String rate =  "Rate";
        String amt =  "Amount";
        String header =  "|"+sr +        
                        "|"+" ".repeat(3) + productName + 
                        "|"+" ".repeat(1) + qty 
                        +"|"+ rate 
                        +"|"+ amt +"|";
        TraceLog.info(Utility.printCurrentLine(), header);
        TraceLog.info(Utility.printCurrentLine(), "-".repeat(header.length()));
        
        Bill [] bills = DatabaseService.getAllBillEntry();
        int i = 0 ;
        for(i = 0 ; i<bills.length; i++){
            if(bills[i] == null ) break;
            Bill bill = bills[i];           
            billService.printCurrectProductDetails(bill.getId()+"", bill.getProductName(), bill.getQuantity()+"", bill.getPrice()+"",bill.getAmount()+"");
        }
        if(i == 0 ) TraceLog.info(Utility.printCurrentLine(), "NO SALES RECORDE............");
        
        TraceLog.info(Utility.printCurrentLine(), "-".repeat(header.length()));
        TraceLog.info(Utility.printCurrentLine(), "===========Sales Book End=================");

       } catch (Exception e) {
            TraceLog.error(Utility.printCurrentLine(), "Exception while generating bill ..... ");
            e.printStackTrace();
        }
    }
}
