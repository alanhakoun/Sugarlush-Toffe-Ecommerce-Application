package src.ShoppingManagement;

import src.UsersManagement.Customer;

import java.util.*;

/**
 * It illustrates an order in the application. Each order has an id and contains information such as the address and status.
 */
public class Order {

    private Vector<OrderedProduct> list;

    private Customer customer;
    private int orderID;

    private static int cnt = 1;

    private OrderStatus orderStatus;

    private double amount;

    private String address;

    public Order(Vector<OrderedProduct> list, Customer customer, OrderStatus orderStatus, double amount) {
        this.orderID = cnt;
        cnt++;
        this.list = list;
        this.customer = customer;
        this.orderID = orderID;
        this.orderStatus = orderStatus;
        this.amount = amount;
    }

    /** getters and setters.*/
    public OrderStatus getOrderStatus() {
        return orderStatus;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return this.address;
    }

    public void setOrderStatus(src.ShoppingManagement.OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public int getOrderID() {
        return orderID;
    }
}

