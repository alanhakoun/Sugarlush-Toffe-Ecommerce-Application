package src.UsersManagement;

import src.ShoppingManagement.Order;

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
    }
    public void setLoyaltyPoints(double loyaltyPoints) {
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

    public Vector<Order> getOrderHistory() {
        return orderHistory;
    }

    public void setOrderHistory(Vector<Order> orderHistory) {
        this.orderHistory = orderHistory;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

//    void logout(){}


    //TODO: activate  orderHistoryToString(), logout()
}


