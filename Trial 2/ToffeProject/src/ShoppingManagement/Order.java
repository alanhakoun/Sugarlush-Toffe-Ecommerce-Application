package src.ShoppingManagement;

import src.UsersManagement.Customer;

import java.util.*;

public class Order {

    private Vector<OrderedProduct> list;

    private Customer customer;
    private int orderID;

    private static int cnt = 1;
    private src.ShoppingManagement.OrderStatus orderStatus;

    private double amount;
    private double schemaVar = 5;

    public Order(Vector<OrderedProduct> list, Customer customer, OrderStatus orderStatus, double amount) {
        this.orderID = cnt;
        cnt++;
        this.list = list;
        this.customer = customer;
        this.orderID = orderID;
        this.orderStatus = orderStatus;
        this.amount = amount;
    }

    public src.ShoppingManagement.OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(src.ShoppingManagement.OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

//    public boolean paymentType() {
//        if(paid){
//            return true;
//        } else {
//            return false;
//        }
//    }

    public void setSchemaVar(double schemaVar) {
        this.schemaVar = schemaVar;
    }


    public double getSchemaVar() {
        return schemaVar;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Vector<OrderedProduct> getList() {
        return list;
    }
}

