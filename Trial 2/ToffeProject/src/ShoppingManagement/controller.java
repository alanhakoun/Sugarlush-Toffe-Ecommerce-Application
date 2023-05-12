package src.ShoppingManagement;

import src.UsersManagement.*;

import java.util.*;

public class controller {

    private final int max = 50;
    private ProudctsCatalog catalog;
    private Customer customer;
    private Vector<Order> orders;

    private Vector<OrderedProduct> products;

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

    public int placeOrder() {
        double total = 0;
        for (int i = 0; i < products.size(); i++) {
            total += products.get(i).getTotalPrice();
        }
        Order order = new Order(products, customer, OrderStatus.CREATED, total);
        orders.add(order);
        return order.getOrderID();
    }


    public boolean pay(String num, int id) {
        cashPayment payOnDelivery = new cashPayment();
        if(payOnDelivery.Pay(num)){
            updateOrderStatus(id,OrderStatus.READY_TO_SHIP);
            for (int i = 0; i < products.size(); i++) {
                ProudctsCatalog.updateProductQtyInStock(products.get(i).getProduct().getId(), products.get(i).getQuantity());
            }
            return true;
        }
        return false;
    }

    public void updateOrderStatus(int orderId, OrderStatus newStatus) {
        Order order = orders.get(orderId-1);
        order.setOrderStatus(newStatus);
    }

    public void cancelOrder(int orderId) {
        for (int i = 0; i < orders.size(); i++) {
            if (orderId == orders.get(i).getOrderID()) {
                updateOrderStatus(orderId,OrderStatus.CANCELLED);
                orders.remove(i);
                break;
            }
        }

    }

    public Vector<OrderedProduct> getProducts() {
        return products;
    }

    public void setProducts(Vector<OrderedProduct> products) {
        this.products = products;
    }

    public void setLoyaltyPoints(Customer customer, double orderAmount) {
        cashPayment payment = new cashPayment();
        double points = payment.calcLoyaltyPoints(orderAmount);
        customer.setLoyaltyPoints(customer.getLoyaltyPoints() + points);
    }
}
