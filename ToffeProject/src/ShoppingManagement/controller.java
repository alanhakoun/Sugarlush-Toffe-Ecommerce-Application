package ShoppingManagement;
import src.ShoppingManagement.*;

import java.util.*;

public class controller {
    private Map<Integer, ShoppingManagement.Order> orders ;
    private Map <String, ShoppingManagement.Product> products ;

    public controller(){
        orders = new HashMap<>();
        products = new HashMap<>();
    }

    public void addItem(OrderedProduct item){
        items.addOrderedItem(item);
    }

    public void placeOrder(Order order){
        //orders.put(order.getOrderID(),order);

    }

    public void updatePQtyInStock(String productId, int quantity) {
        Product product = products.get(productId);
        product.setQuantityInStock(product.getQuantityInStock()- quantity); // !!! setter in product. add if contrion to make sure quantity is enough
    }


    public void payment(payment payemnt , Order order){ // what is the condtion to call each payemnt, on order or not ?
        //order.paymentType(payemnt);                        // or should it take it directly?
    }

    public void updateOrderStatus(int orderId, OrderStatus newStatus){
        Order order = orders.get(orderId);
        order.setOrderStatus(newStatus);
    }

    public void cancelOrder(int orderId){
        orders.remove(orderId);
    }

    public void setLoyaltyPoints(Customer customer, double orderAmount) {
        int points = payment.calculatePoints(orderAmount);
        customer.setLoyaltyPoints(customer.getLoyaltyPoints() + points);
    }
}
