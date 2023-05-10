package src.ShoppingManagement;
import src.UsersManagement.*;

import java.util.*;

public class controller {
    private ProudctsCatalog catalog;

    private Customer customer;
    private Vector<Order> orders ;
    private Vector<OrderedProduct>  products ;

    public controller(ProudctsCatalog productsDatabase, Customer user){
        catalog = productsDatabase;
        customer = user;
        orders = new Vector<>();
        products = new Vector<>();
    }

    public void addItem(String itemName , int quantity ){
        boolean flag = false;
        for (int i = 0; i < catalog.getProducts().size() ; i++) {
            if (itemName == catalog.getProducts().get(i).getName()) {
                double totalPrice = catalog.getProducts().get(i).getPrice() * quantity;
                OrderedProduct temp = new OrderedProduct(catalog.getProducts().get(i), quantity, totalPrice);
                products.add(temp);
                flag = true;
                break;
            }
        }
        if(!flag){
            System.out.println("Dont have the product!");}
    }

    public void updateOrderQty(String productName , int quantity){
        boolean flag = false;
        for (int i = 0; i < products.size() ; i++) {
            if(productName == products.get(i).getProduct().getName()){
                products.get(i).updateQuantity(quantity);
                flag = true;
                break;
            }
        }
        if(!flag){
        System.out.println("Could not update your order!");}
    }

    public void placeOrder(){
        double total = 0;
        for (int i = 0; i < products.size(); i++) {
            catalog.updateProductQtyInStock(products.get(i).getProduct().getId(), products.get(i).getQuantity());
            total += products.get(i).getTotalPrice();
        }
        Order order = new Order(products, customer, OrderStatus.CREATED, total);
        orders.add(order);
    }




    public void payment(payment payemnt , Order order){ // what is the condtion to call each payemnt, on order or not ?
        //order.paymentType(payemnt);                        // or should it take it directly?
    }

    public void updateOrderStatus(int orderId, OrderStatus newStatus){
        Order order = orders.get(orderId);
        order.setOrderStatus(newStatus);
    }

    public void cancelOrder(int orderId){
        orders.remove();
    }

    public void setLoyaltyPoints(Customer customer, double orderAmount) {
        Payment payment = new Payment();
        int points = payment.calcLoyaltyPoints(orderAmount);
        customer.setLoyaltyPoints(customer.getLoyaltyPoints() + points);
    }
}
