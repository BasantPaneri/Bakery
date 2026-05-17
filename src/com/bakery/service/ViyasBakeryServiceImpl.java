package src.com.bakery.service;

import src.com.bakery.utility.TraceLog;
import src.com.bakery.utility.Utility;

public class ViyasBakeryServiceImpl implements BakeryService {
    boolean isRunning = false; 
    @Override
    public void run(){
        if (isRunning)
        TraceLog.info(Utility.printCurrentLine()," ................ Viyas Bakery is Up and Running ............. ");
        else TraceLog.info(Utility.printCurrentLine()," ................ Viyas Bakery is closed ............. ");
    }
}
