package src.com.bakery.service;

import java.util.Date;

import src.com.bakery.model.*;
import src.com.bakery.utility.TraceLog;
import src.com.bakery.utility.Utility;

public class BillService {
    private double totalRevenue = 0;
    public double getTotalRevenue() {
        return totalRevenue;
    }
    

    public void generateBill(MyCartService myCartService){
        if(DatabaseService.itemCount == 0 ){
            printEmptyBill();
            return;
        }

        String name = Utility.getStringInput( "Customer name");
        Date date  = new Date();
        TraceLog.info(Utility.printCurrentLine(), "==============================invoice=======================================");
        TraceLog.info(Utility.printCurrentLine(), "Customer Name: "+ name);
        TraceLog.info(Utility.printCurrentLine(), "Date: "+ date);
        TraceLog.info(Utility.printCurrentLine(), "============================================================================");
        double sum = 0 ; 
        String sr =  "SrNo";
        String productName =  "Product Name";
        String qty =  "Qty";
        String rate =  "Rate";
        String amt =  "Amount";
        String total =  "Grand Total";
        String header =  "|"+sr +        
                        "|"+" ".repeat(3) + productName + 
                        "|"+" ".repeat(1) + qty 
                        +"|"+ rate 
                        +"|"+ amt +"|";
        TraceLog.info(Utility.printCurrentLine(), header);
        TraceLog.info(Utility.printCurrentLine(), "-".repeat(header.length()));
        for(int i =0 ; i<DatabaseService.itemCount; i++){

            Cart currentCart = DatabaseService.cart[i];
            double amount = currentCart.getProduct().getPrice() * currentCart.getMyQuantity();

            Bill bill =  new Bill(1, name, currentCart.getProduct().getId(), currentCart.getMyQuantity(), amount);
            sum += amount ;

            int rs = DatabaseService.punchEntryInBill(bill);
            
            printCurrectProductDetails(i+1+"", currentCart.getProduct().getName(), currentCart.getMyQuantity()+"", currentCart.getProduct().getPrice()+"", amount+"");
            DatabaseService.cart[i] = null;
        }

        TraceLog.info(Utility.printCurrentLine(), "===================");
        TraceLog.info(Utility.printCurrentLine(),  total +" : "+ sum);
        TraceLog.info(Utility.printCurrentLine(), "===================");
        totalRevenue += sum;
        
        TraceLog.info(Utility.printCurrentLine(), "==============================invoice======================================");
        DatabaseService.itemCount = 0 ;
    }

    public void printEmptyBill(){
        TraceLog.info(Utility.printCurrentLine(),".... Cart in empty ....");
    }

    //4| 15| 4 | 4 | 6 |
    public void printCurrectProductDetails(String i, String productName, String qty, String rate, String amt){
        String s = " ".repeat(4-i.length()) + i;
        String p =  " ".repeat(15-productName.length()) + productName;
        String q =  " ".repeat(4-qty.length()) + qty;
        String r =  " ".repeat(10-rate.length()) + rate;
        String a =  amt;
        TraceLog.info(Utility.printCurrentLine(), "|"+s+"|"+p+"|"+q+"|"+r+"|"+a+"|");
    }

    

}
