package src.com.bakery.service;

import src.com.bakery.model.User;
import src.com.bakery.utility.TraceLog;
import src.com.bakery.utility.Utility;

public class Authentication {

    User u = new User("Bakery Ji", "bakery@gmail.com", "12345");
    public boolean signIN(){
        boolean isTrue = false;
        String email = Utility.getStringInput("email");
        TraceLog.debbug(Utility.printCurrentLine(), email);
        String password = Utility.getStringInput("password");
        while(true){
            if(u.getEmail().equals(email) && u.getPassword().equals(password)){
                TraceLog.info(Utility.printCurrentLine(), "==============WEL-COME " + u.getName() +"==============");
                isTrue = true;
                break;
            }else {
                TraceLog.info(Utility.printCurrentLine(), "-----------------invalid credentials.try again-------------");
                break;
            }
        }
        
        return isTrue;
    }
}
