package src.com.bakery.utility;

import java.util.Scanner;

public class Utility{
    
    static Scanner sc = new Scanner(System.in);
    public static Scanner getScannerForInput(){
        return sc; 
    }

    public static String printCurrentLine() {
        StackTraceElement element =
                Thread.currentThread().getStackTrace()[2];
        String lineInfo = "Class: " + element.getClassName() +", Method: " + element.getMethodName() +", Line: " + element.getLineNumber();
        return lineInfo;
    }

    // Will be used for taking Integer type input
    public static int getIntInput(){
        Scanner sc = getScannerForInput();
        int a = 0;
        String input = "";
        String metaInfo = printCurrentLine(); 
        if (metaInfo == null) metaInfo = " ";
        if (metaInfo != null) metaInfo = metaInfo + " ".repeat(TraceLog.indentation - metaInfo.length());
        System.out.print("log.info-[" + metaInfo + "]: " + "Please Enter Int Value :" );
        try {
            input = sc.nextLine();
            a = Integer.parseInt(input);
        } catch (Exception e) {
            TraceLog.error(printCurrentLine(), "invalid input default value 0 returned.....");
            e.printStackTrace();
        }
        return a;
    }

    public static double getDoubleInput(){
        Scanner sc = getScannerForInput();
        double a  = 0;
        String input = "";
         String metaInfo = printCurrentLine(); 
        if (metaInfo == null) metaInfo = " ";
        if (metaInfo != null) metaInfo = metaInfo + " ".repeat(TraceLog.indentation - metaInfo.length());
        System.out.print("log.info-[" + metaInfo + "]: " + "Please Enter Double Value :" );
        try {
            input = sc.nextLine();
            a = Double.parseDouble(input);
            
        } catch (Exception e ) {
            TraceLog.error(printCurrentLine(), "invalid input default double value 0.0 returned.....");
            e.printStackTrace();
        }
         return a;
    }

    public static String getStringInput(String value){
        Scanner sc = getScannerForInput();
        String a = null;
        String metaInfo = printCurrentLine(); 
        if (metaInfo == null) metaInfo = " ";
        if (metaInfo != null) metaInfo = metaInfo + " ".repeat(TraceLog.indentation - metaInfo.length());
        System.out.print("log.info-[" + metaInfo + "]: " + "Please Enter "+ value +" :" );
        try {
            a = sc.nextLine();
        } catch (Exception e) {
            TraceLog.error(printCurrentLine(),"invalid input default value null returned.....");
            e.printStackTrace();
        }
        return a;
    }   

    public static void lineEater(){
        Scanner sc = getScannerForInput();
        try {
            sc.nextLine();
        } catch (Exception e) {
            TraceLog.error(printCurrentLine(),"invalid input default value null returned.....");
            e.printStackTrace();
        }
    }
}