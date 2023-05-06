package UsersManagement;

import java.util.Vector;

public class Customer {
    private String username;
    private String email;
    private String password;
    private String phoneNum;
    private double loyaltyPoints;
    static private UsersDatabase database;
//    Vector<Order> orderHistory;

    public Customer(String name, String pass, String mail, String number)
    {
        username = name;
        password = pass;
        email = mail;
        phoneNum = number;
        loyaltyPoints = 0;
        database.addCustomer(this);
    }
    String getPassword(){return password;}
    String getEmail(){return email;}
    public void setPassword(String password) {
        this.password = password;
    }
//    String orderHistoryToString()
//    {
//        return orderHistory.toString();
//    }


//    void logout(){}

    void fillCustomers()    /// used for testing
    {
        Customer c1 = new Customer("Mohamed", "12345", "m@gmial.com", "01234567890");
        Customer c2 = new Customer("Ahmed", "98765", "A@gmial.com", "01034567890");
        Customer c3 = new Customer("Smith", "24680", "S@gmial.com", "01134567890");
    }
    //TODO: activate orderHistory , orderHistoryToString(), logout()
}


