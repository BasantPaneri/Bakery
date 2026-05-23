package src.com.bakery.service;

import src.com.bakery.model.Bill;
import src.com.bakery.model.Products;
import src.com.bakery.utility.TraceLog;
import src.com.bakery.utility.Utility;

public class StockManagement {
    
    
    // Products item = new Products(null, productCount, productCount, productCount);
    public Products[] getProducts() {
        return DatabaseService.product;
    }

    public int getProductCount() {
        return DatabaseService.productCount;
    }
    

    public void addProduct(){
        if (DatabaseService.productCount >= DatabaseService.product.length) {
            TraceLog.info(Utility.printCurrentLine(), "Stock is full. Cannot add more products.");
            return;
        }
        String newProduct = Utility.getStringInput("product name");
        for(int i = 0 ; i < DatabaseService.product.length ; i++){
            if(DatabaseService.product[i] != null && newProduct.equalsIgnoreCase(DatabaseService.product[i].getName())){
                TraceLog.info(Utility.printCurrentLine(), "Product already present.");
                return;
            }else if (DatabaseService.product[i] == null){
                int capacity = 0;
                    DatabaseService.productCount = i;
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
                    Products product = new Products(newProduct, capacity, qty, price);
                    DatabaseService.product[DatabaseService.productCount++] = product;
                    DatabaseService.addNewProduct(product);
                    TraceLog.info(Utility.printCurrentLine(), "-------new product added---------");
                    break;
            }
        }
    }

    public void show(){
        if(DatabaseService.productCount == 0){
            TraceLog.info(Utility.printCurrentLine(), "nothing to show");
            return;
        }
        TraceLog.info(Utility.printCurrentLine(), "---------------available products---------------");
        for(int i = 0; i < DatabaseService.productCount; i++) {
            Products p = DatabaseService.product[i];
            TraceLog.info(Utility.printCurrentLine(), "Product : " + p.getName() + "| Capacity : " + p.getCapacity() + "| quantity : " + p.getQuantity() + "| Price : " + p.getPrice());
        }
    }

    public boolean showToCustomer(){
        if(DatabaseService.productCount == 0){
            TraceLog.info(Utility.printCurrentLine(), "nothing to show");
            return false;
        }
        TraceLog.info(Utility.printCurrentLine(), "---------------available products---------------");
        for(int i = 0; i < DatabaseService.productCount; i++) {
            Products p = DatabaseService.product[i];
            TraceLog.info(Utility.printCurrentLine(), "Product : " + p.getName() + "| quantity : " + p.getQuantity() + "| Price : " + p.getPrice());
        }
        return true;
    }

    public void reStock(){
        boolean found = false;
        String productName = Utility.getStringInput("product name");
        for(int i = 0 ; i < DatabaseService.productCount ; i++){
            Products p = DatabaseService.product[i];
            if(productName.equalsIgnoreCase(p.getName())){
                found = true;
                TraceLog.info(Utility.printCurrentLine(), "Product : " + p.getName() + "| Quantity : " + p.getQuantity());
                TraceLog.info(Utility.printCurrentLine(), "enter quantity to add: ");
                int newQty;
                while (true){
                    newQty = Utility.getIntInput();
                    if (newQty + p.getQuantity() > p.getCapacity() ){
                        TraceLog.info(Utility.printCurrentLine(), "not enough capacity- you can add " + (p.getCapacity() - p.getQuantity()) + "pieces only");
                    }else break;
                }
                p.setQuantity(p.getQuantity()+ newQty);
                TraceLog.info(Utility.printCurrentLine(), "-----------product re-stocked successfully----------");
                TraceLog.info(Utility.printCurrentLine(), "Updated quantity : " + p.getQuantity());
                break;
            }            
        }if(!found) TraceLog.info(Utility.printCurrentLine(), "--------------Product not found-please add it------------");        
    }

    public void showBillBook(BillService billService){
        String total =  "Total Revenue";
        TraceLog.info(Utility.printCurrentLine(), "===========Sales Book Start===============");
        TraceLog.info(Utility.printCurrentLine(), "===================");
        TraceLog.info(Utility.printCurrentLine(),  total +" : "+ billService.getTotalRevenue());
        TraceLog.info(Utility.printCurrentLine(), "===================");
        if(billService!=null && DatabaseService.billNumber!=0){
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

            
            for(int i = 0 ; i<DatabaseService.billNumber; i++){
                Bill bill = DatabaseService.bill[i];           
                billService.printCurrectProductDetails(bill.getId()+"", bill.getProduct().getName(), bill.getQuantity()+"", bill.getProduct().getPrice()+"",bill.getAmount()+"");
            }
            TraceLog.info(Utility.printCurrentLine(), "-".repeat(header.length()));
        }else{
            TraceLog.info(Utility.printCurrentLine(), "No Sales recorded...............");
        }
        TraceLog.info(Utility.printCurrentLine(), "===========Sales Book End=================");
    }
}
