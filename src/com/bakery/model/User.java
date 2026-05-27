package src.com.bakery.model;

public class User {
    private String name ;
    private String email ;
    private String password ;
    private String temp ;

    User(){}
    public User(String name, String email,String temp, String password){
        this.name = name;
        this.email = email;
        this.password = password;
        this.temp = temp;
    }

    public String getEmail() {
        return email;
    }
    public String getName() {
        return name;
    }
    public String getPassword() {
        return password;
    }
    public String getTemp() {
        return temp;
    }
}
