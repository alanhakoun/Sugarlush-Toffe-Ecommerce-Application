package src.UsersManagement;

import ShoppingManagement.Order;

import java.util.Vector;

public class Customer {
    private String username;
    private String email;
    private String password;
    private String phoneNum;
    private double loyaltyPoints;
    private Vector<Order> orderHistory;

    public Customer(String name, String pass, String mail, String number)
    {
        username = name;
        password = pass;
        email = mail;
        phoneNum = number;
        loyaltyPoints = 0;
    }
    public void setLoyaltyPoints(int loyaltyPoints) {
        this.loyaltyPoints = loyaltyPoints;
    }
    public double getLoyaltyPoints(){
        return loyaltyPoints;
    }
    String getPassword(){return password;}
    public String getMail(){return email;}
    public void setPassword(String password) {
        this.password = password;
    }
    String orderHistoryToString()
    {
        return orderHistory.toString();
    }


//    void logout(){}


    //TODO: activate  orderHistoryToString(), logout()
}


