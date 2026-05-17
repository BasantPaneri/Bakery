package src.com.bakery.utility;

public class TraceLog {
    public final static int indentation = 100;
    public static void info(String metaInfo , String message) {
        if(message == null ) message = "";
        if (metaInfo == null) metaInfo = " ";
        if (metaInfo != null) metaInfo = metaInfo + " ".repeat(indentation - metaInfo.length());

        // System.out.println("log.info -->" + metaInfo + " ====================== "+ message);
        // System.out.println("log.info --> [--- " + metaInfo + " ---] : " + message );
        System.out.println("log.info-[" + metaInfo + "]: " + message );
    }

    public static void info(String metaInfo , String message, int [] arr) {
        if(message == null ) message = "";
        // System.out.println("log.info -->" + metaInfo + " ====================== "+ message);
        // System.out.println("log.info --> [--- " + metaInfo + " ---] : " + message);
        System.out.println("log.info-[" + metaInfo + "]: " + message);
        for(int a : arr){
            System.out.print(a+" ");
        }
    }

    public static void info(String metaInfo , String message, String [] arr) {
        if(message == null ) message = "";
        // System.out.println("log.info -->" + metaInfo + " ====================== "+ message);
        // System.out.println("log.info --> [--- " + metaInfo + " ---] : " + message);
        System.out.println("log.info-[" + metaInfo + "]: " + message);
        for(String a : arr){
            System.out.println(a+" ");
        }
    }

    public static void error(String metaInfo, String message) {
        if(message == null ) message = "";
        // System.err.println("log.error --> [--- " + metaInfo + " ---] : " + message);
        System.err.println("log.error-[" + metaInfo + "]: " + message);
    }

    public static void debbug(String metaInfo , String message) {
        if(message == null ) message = "";
        if (metaInfo == null) metaInfo = " ";
        if (metaInfo != null) metaInfo = metaInfo + " ".repeat(indentation - metaInfo.length());
        // System.out.println("log.debbug --> [--- " + metaInfo + " ---] : " + message );
        System.out.println("log.debbug-[" + metaInfo + "]: " + message );
    }
}