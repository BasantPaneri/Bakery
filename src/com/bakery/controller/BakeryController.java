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
                    TraceLog.info(Utility.printCurrentLine(), "1. change bakery status");
                    TraceLog.info(Utility.printCurrentLine(), "2. check stock");
                    TraceLog.info(Utility.printCurrentLine(), "3. re-stock");
                    TraceLog.info(Utility.printCurrentLine(), "4. Edit product/add new product");
                    TraceLog.info(Utility.printCurrentLine(), "5. check billbook");
                    TraceLog.info(Utility.printCurrentLine(), "6. Log-out");
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
                        stock.show();
                    } else if (option == 3) {
                        stock.reStock();
                    } else if (option == 4) {
                        stock.addProduct();
                    } else if (option == 5) {
                        stock.showBillBook(billService);
                    } else if (option == 6) {
                        break;
                    }

                }
            } else if (option == 2) {
                if(isOpen){
                    TraceLog.info(Utility.printCurrentLine(), "-----------------------WEL-COME TO FRESH OVEN BAKERS---------------------");
                    while (true){
                        TraceLog.info(Utility.printCurrentLine(), "===================================================================");
                        TraceLog.info(Utility.printCurrentLine(), "1. show all products.");
                        TraceLog.info(Utility.printCurrentLine(), "2. Show cart.");
                        TraceLog.info(Utility.printCurrentLine(), "3. checkout.");
                        TraceLog.info(Utility.printCurrentLine(), "====================================================================");
                        option = Utility.getIntInput();
                        while(true){
                            if (option == 1) {
                            boolean allowToAddInCart = stock.showToCustomer();
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