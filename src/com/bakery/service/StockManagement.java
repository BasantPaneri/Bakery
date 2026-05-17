package src.com.bakery.service;

import src.com.bakery.model.Bill;
import src.com.bakery.model.Products;
import src.com.bakery.utility.TraceLog;
import src.com.bakery.utility.Utility;

public class StockManagement {
    Products[] product = new Products[10];
    int productCount = 0;
    // Products item = new Products(null, productCount, productCount, productCount);
    public Products[] getProducts() {
        return product;
    }

    public int getProductCount() {
        return productCount;
    }
    

    public void addProduct(){
        if (productCount >= product.length) {
            TraceLog.info(Utility.printCurrentLine(), "Stock is full. Cannot add more products.");
            return;
        }
        for(int i = 0 ; i < product.length ; i++){
            String newProduct = Utility.getStringInput("product name");
            if(product[i] != null && newProduct.equalsIgnoreCase(product[i].getName())){
                TraceLog.info(Utility.printCurrentLine(), "Product already present.");
                return;
            }if (product[i] == null){
                productCount = i;
                TraceLog.info(Utility.printCurrentLine(), "enter capacity.");
                int capacity = Utility.getIntInput();
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
                product[productCount++] = new Products(newProduct, capacity, qty, price);
                TraceLog.info(Utility.printCurrentLine(), "-------new product added---------");
                break;
            }
        }
    }

    public void show(){
        if(productCount == 0){
            TraceLog.info(Utility.printCurrentLine(), "nothing to show");
            return;
        }
        TraceLog.info(Utility.printCurrentLine(), "---------------available products---------------");
        for(int i = 0; i < productCount; i++) {
            Products p = product[i];
            TraceLog.info(Utility.printCurrentLine(), "Product : " + p.getName() + "| Capacity : " + p.getCapacity() + "| quantity : " + p.getQuantity() + "| Price : " + p.getPrice());
        }
    }

    public boolean showToCustomer(){
        if(productCount == 0){
            TraceLog.info(Utility.printCurrentLine(), "nothing to show");
            return false;
        }
        TraceLog.info(Utility.printCurrentLine(), "---------------available products---------------");
        for(int i = 0; i < productCount; i++) {
            Products p = product[i];
            TraceLog.info(Utility.printCurrentLine(), "Product : " + p.getName() + "| quantity : " + p.getQuantity() + "| Price : " + p.getPrice());
        }
        return true;
    }

    public void reStock(){
        boolean found = false;
        String productName = Utility.getStringInput("product name");
        for(int i = 0 ; i < productCount ; i++){
            Products p = product[i];
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
        if(billService!=null && billService.billNumber!=0){
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

            
            for(int i = 0 ; i<billService.billNumber; i++){
                Bill bill = billService.bill[i];           
                billService.printCurrectProductDetails(bill.getId()+"", bill.getCart().getProduct().getName(), bill.getQuantity()+"", bill.getCart().getProduct().getPrice()+"",bill.getAmount()+"");
            }
            TraceLog.info(Utility.printCurrentLine(), "-".repeat(header.length()));
        }else{
            TraceLog.info(Utility.printCurrentLine(), "No Sales recorded...............");
        }
        TraceLog.info(Utility.printCurrentLine(), "===========Sales Book End=================");
    }
}
