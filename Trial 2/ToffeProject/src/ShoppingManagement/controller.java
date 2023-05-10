package src.ShoppingManagement;
import src.UsersManagement.*;

import java.util.*;

public class controller {
    private Map<Integer, Order> orders ;
    private Vector<OrderedProduct>  products ;

    public controller(){
        orders = new HashMap<>();
    }

    public void addItem(String itemName , int quantity ){

        for (int i = 0; i <  ; i++) {

        }
        double totalPrice = item.getPrice()*quantity;
        OrderedProduct orderItem = new OrderedProduct(item , quantity, totalPrice);
        products.add(orderItem);
    }

    public void updateOrderQty(String productName , int quantity){
        boolean flag = false;
        for (int i = 0; i < products.size() ; i++) {
            if(productName == products.get(i).getProductID().getName()){
                products.get(i).updateQuantity(quantity);
                flag = true;
                break;
            }
        }
        if(!flag){
        System.out.println("Could not update your order!");}
    }

    public void placeOrder(){

        Order order = new Order(products,)
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
