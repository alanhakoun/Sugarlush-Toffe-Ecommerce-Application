package src.UsersManagement;

/**
    *Represents users who have created an account on the application, and therefore have access to additional features and methods such as making an order, adding to cart and paying for order info rather than just viewing the catalog.   */

public class Customer {
    private String username;
    private String email;
    private String password;
    private String phoneNum;
    private String address;

    /**
     * constructor sets customer info details.
     * @param name customer name .
     * @param pass customer password.
     * @param mail customer email.
     * @param number customer number.
     * @param address customer address.
     */
    public Customer(String name, String pass, String mail, String number, String address)
    {
        this.username = name;
        this.password = pass;
        this.email = mail;
        this.phoneNum = number;
        this.address = address;
    }
    /** Getters and Setters*/

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


