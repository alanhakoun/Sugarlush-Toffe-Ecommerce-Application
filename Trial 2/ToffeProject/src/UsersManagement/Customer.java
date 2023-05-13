package src.UsersManagement;

import src.ShoppingManagement.Order;

import java.util.Vector;

public class Customer {


    private String username;
    private String email;
    private String password;
    private String phoneNum;
    private String address;


    public Customer(String name, String pass, String mail, String number, String address)
    {
        this.username = name;
        this.password = pass;
        this.email = mail;
        this.phoneNum = number;
        this.address = address;
    }
    public String getPassword(){return password;}
    public String getMail(){return email;}
    public void setPassword(String password) {
        this.password = password;
    }
    public String getUsername() {
        return username;
    }
    public String getPhoneNum() {
        return phoneNum;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
}


