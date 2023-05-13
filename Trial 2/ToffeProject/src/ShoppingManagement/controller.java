package src.ShoppingManagement;

import src.UsersManagement.*;

import java.util.*;

public class controller {

    private final int max = 50;
    private ProudctsCatalog catalog;
    private Customer customer;



    private Vector<Order> orders = null;

    private Vector<OrderedProduct> products = null;

    public controller(ProudctsCatalog productsDatabase, Customer user) {
        catalog = productsDatabase;
        customer = user;
        orders = new Vector<>();
        products = new Vector<>();
    }

    public int addItem(String itemName, int quantity) {
        if (quantity >= 0) {
            for (int i = 0; i < ProudctsCatalog.getProducts().size(); i++) {
                if (Objects.equals(itemName, ProudctsCatalog.getProducts().get(i).getName())) {
                    quantity = quantity > max ? -1 : (quantity> ProudctsCatalog.getProducts().get(i).getQuantityInStock() ? -2 : quantity);
                    if(quantity<0){
                        return quantity;
                    }

                    double totalPrice = ProudctsCatalog.getProducts().get(i).getPrice() * quantity;
                    for (int j = 0; j < products.size(); j++) {
                        if(Objects.equals(products.get(j).getProduct().getName(), itemName)){
                            products.get(j).updateQuantity(products.get(j).getQuantity()+quantity);
                            return 0;
                        }
                    }
                    OrderedProduct temp = new OrderedProduct(ProudctsCatalog.getProducts().get(i), quantity, totalPrice);
                    products.add(temp);
                    return 0;
                }
            }
        }
        return -3;
    }

    public int updateOrderQty(String productName, int quantity) {
        if (quantity >= 0) {
            for (int i = 0; i < products.size(); i++) {
                if (Objects.equals(productName, products.get(i).getProduct().getName())) {
                    quantity = quantity > max ? -1 : (quantity> ProudctsCatalog.getProducts().get(i).getQuantityInStock() ? -2 : quantity);
                    if(quantity<0){
                        return quantity;
                    }
                    if(products.get(i).updateQuantity(quantity)) {
                        return 0;
                    }
                }
            }
        }
        return -3;
    }

    public boolean removeItem(String productName) {
        boolean flag = false;
        for (int i = 0; i < products.size(); i++) {
            if (Objects.equals(productName, products.get(i).getProduct().getName())) {
                products.remove(i);
                flag = true;
                break;
            }
        }
        return flag;
    }

    public int placeOrder(String address) {
        double total = 0;
        if(products.size()!=0){
            for (int i = 0; i < products.size(); i++) {
                total += products.get(i).getTotalPrice();
            }
            products.clear();
            Order order = new Order(products, customer, OrderStatus.CREATED, total);
            order.setAddress(address);
            orders.add(order);
            return order.getOrderID();
        }
        return 0;
    }


    public boolean pay(String num, int id) {
        cashPayment payOnDelivery = new cashPayment();
        if(payOnDelivery.Pay(num)){
            updateOrderStatus(id,OrderStatus.CLOSED);
            for (int i = 0; i < products.size(); i++) {
                ProudctsCatalog.updateProductQtyInStock(products.get(i).getProduct().getId(), products.get(i).getQuantity());
            }
            return true;
        }
        return false;
    }

    public void updateOrderStatus(int orderId, OrderStatus newStatus) {
        Order order = orders.get(0);
        order.setOrderStatus(newStatus);
    }

    public void cancelOrder(int orderId) {
        for (int i = 0; i < 1; i++) {
            if (orderId == orders.get(i).getOrderID()) {
                updateOrderStatus(orderId,OrderStatus.CANCELLED);
                orders.remove(i);
                break;
            }
        }

    }
    public Vector<Order> getOrders() {
        return orders;
    }
    public Vector<OrderedProduct> getProducts() {
        return products;
    }
}
