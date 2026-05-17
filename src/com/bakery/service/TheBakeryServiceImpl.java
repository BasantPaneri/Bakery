package src.com.bakery.service;

import src.com.bakery.utility.*;

public class TheBakeryServiceImpl implements BakeryService {
    
    @Override
    public void run(){
        TraceLog.info(Utility.printCurrentLine()," ................ The Bakery is Up and Running ............. ");
    }
}
