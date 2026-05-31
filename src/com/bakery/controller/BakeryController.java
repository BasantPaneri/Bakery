package src.com.bakery.controller;

import src.com.bakery.service.Authentication;
import src.com.bakery.service.BillService;
import src.com.bakery.service.DatabaseService;
import src.com.bakery.service.MyCartService;
import src.com.bakery.service.StockManagement;
import src.com.bakery.utility.TraceLog;
import src.com.bakery.utility.Utility;

public class BakeryController {
    public static void main(String[] args) {
        DatabaseService.createTables();
        Authentication auth = new Authentication();
        StockManagement stock = new StockManagement();
        MyCartService myCart = new MyCartService();
        BillService billService = new BillService();
        boolean isOpen = false;
        while (true) {
            TraceLog.info(Utility.printCurrentLine(), "===============================================");
            TraceLog.info(Utility.printCurrentLine(), "1. LogIn as owner.");
            TraceLog.info(Utility.printCurrentLine(), "2. walk in as customer.");
            TraceLog.info(Utility.printCurrentLine(), "3. exit");
            int option = Utility.getIntInput();
            TraceLog.info(Utility.printCurrentLine(), "===============================================");
            // Utility.lineEater();
            if (option == 1) {
                boolean isTrue = auth.signIN();
                while (isTrue) {
                    TraceLog.info(Utility.printCurrentLine(), "===============================================");
                    TraceLog.info(Utility.printCurrentLine(), "1. change bakery status");   // Y
                    TraceLog.info(Utility.printCurrentLine(), "2. check stock");            // Y
                    TraceLog.info(Utility.printCurrentLine(), "3. re-stock");               // Y
                    TraceLog.info(Utility.printCurrentLine(), "4. Add new product");        // Y
                    TraceLog.info(Utility.printCurrentLine(), "5. check billbook");         // N
                    TraceLog.info(Utility.printCurrentLine(), "6. Reset shop");             // Y
                    TraceLog.info(Utility.printCurrentLine(), "7. Log-out");                // Y
                    option = Utility.getIntInput();
                    TraceLog.info(Utility.printCurrentLine(), "===============================================");
                    if (option == 1) {
                        // isOpen = isOpen ? false : true;
                        // false, 1 = true
                        if (isOpen) {
                            isOpen = false;
                            TraceLog.info(Utility.printCurrentLine(), "Bakery is now CLOSED");
                        } else {
                            isOpen = true;
                            TraceLog.info(Utility.printCurrentLine(), "Bakery is now OPEN");
                        }
                    } else if (option == 2) {
                        // stock.show();
                        DatabaseService.showProductsToOwner();
                    } else if (option == 3) {
                        DatabaseService.showProductsToOwner();
                        stock.reStock();
                    } else if (option == 4) {
                        stock.addProduct();
                    } else if (option == 5) {
                        stock.showBillBook(billService);
                    } else if (option == 6) {
                        TraceLog.info(Utility.printCurrentLine(), "enter YES to reset full shop.");
                        String response = Utility.getStringInput("Response");
                        if (response.equalsIgnoreCase("yes") || response.equalsIgnoreCase("no") ) {
                            if (response.equalsIgnoreCase("yes")){
                                DatabaseService.resetShop();
                            }else if (response.equalsIgnoreCase("no")){
                                break;
                            }
                        } else {
                            TraceLog.info(Utility.printCurrentLine(), "-------------INVALID INPUT!-----------");
                        }
                        
                    }else if (option == 7) {
                        break;
                    }
                }
            } else if (option == 2) {
                if(isOpen){
                    TraceLog.info(Utility.printCurrentLine(), "-----------------------WEL-COME TO FRESH OVEN BAKERS---------------------");
                    while (true){
                        TraceLog.info(Utility.printCurrentLine(), "===================================================================");
                        TraceLog.info(Utility.printCurrentLine(), "1. show all products.");    // Y 
                        TraceLog.info(Utility.printCurrentLine(), "2. Show cart.");            // Y
                        TraceLog.info(Utility.printCurrentLine(), "3. checkout.");             // N
                        TraceLog.info(Utility.printCurrentLine(), "====================================================================");
                        option = Utility.getIntInput();
                        while(true){
                            if (option == 1) {
                            // boolean allowToAddInCart = stock.showToCustomer();
                            boolean allowToAddInCart = DatabaseService.showProductsToCustomer();
                            if (allowToAddInCart) myCart.addToCart(stock);
                                break;
                            } else if (option == 2) {
                                myCart.showCart();
                                break;
                            } else if (option == 3) {
                                billService.generateBill(myCart);
                                TraceLog.info(Utility.printCurrentLine(), "=====================Thank you visit again=================");
                                break;
                            }else{
                                break;
                            }
                        }
                        if(option == 3) break;
                    }
                }else TraceLog.info(Utility.printCurrentLine(), "-----------------------Bakery is Closed------------------------");
            } else if (option == 3) {
                System.exit(0);
            }
        }

    }
}