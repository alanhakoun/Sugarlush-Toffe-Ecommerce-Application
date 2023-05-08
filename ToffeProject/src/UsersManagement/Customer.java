package UsersManagement;

import java.util.Vector;

public class Customer {
    private String username;
    private String email;
    private String password;
    private String phoneNum;
    private double loyaltyPoints;
//    Vector<Order> orderHistory;

    public Customer(String name, String pass, String mail, String number)
    {
        username = name;
        password = pass;
        email = mail;
        phoneNum = number;
        loyaltyPoints = 0;
    }
    String getPassword(){return password;}
    String getMail(){return email;}
    public void setPassword(String password) {
        this.password = password;
    }
//    String orderHistoryToString()
//    {
//        return orderHistory.toString();
//    }


//    void logout(){}


    //TODO: activate orderHistory , orderHistoryToString(), logout()
}


