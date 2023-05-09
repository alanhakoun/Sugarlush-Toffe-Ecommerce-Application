package src.ShoppingManagement;
import java.util.*;

public class Order {

    private Vector<OrderedProduct> list;

    private Customer customer;
    private int orderID;
    private String dateOrdered;
    private OrderStatus orderStatus;

    private float amount;
    private double schemaVar = 5;

    public Order(Vector<OrderedProduct> list, Customer customer, int orderID, String dateOrdered, OrderStatus orderStatus, float amount) {
        this.list = list;
        this.customer = customer;
        this.orderID = orderID;
        this.dateOrdered = dateOrdered;
        this.orderStatus = orderStatus;
        this.amount = amount;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getDateOrdered() {
        return dateOrdered;
    }

    public void setDateOrdered(String dateOrdered) {
        this.dateOrdered = dateOrdered;
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

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public Vector<OrderedProduct> getList() {
        return list;
    }

    public void addOrderedItem(OrderedProduct item) {
        list.add(item);
        setAmount(this.amount += item.getTotalPrice());
    }
}

