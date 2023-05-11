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

            for (int i = 0; i < catalog.getProducts().size(); i++) {
                if (itemName == catalog.getProducts().get(i).getName()) {
                    quantity = quantity > max ? -1 : (quantity>catalog.getProducts().get(i).getQuantityInStock() ? -2 : quantity);
                    if(quantity<0){
                        return quantity;
                    }
                    double totalPrice = catalog.getProducts().get(i).getPrice() * quantity;
                    OrderedProduct temp = new OrderedProduct(catalog.getProducts().get(i), quantity, totalPrice);
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
                if (productName == products.get(i).getProduct().getName()) {
                    quantity = quantity > max ? -1 : (quantity>catalog.getProducts().get(i).getQuantityInStock() ? -2 : quantity);
                    if(quantity<0){
                        return quantity;
                    }
                    products.get(i).updateQuantity(quantity);
                    return 0;
                }
            }
        }
        return -3;
    }

    public boolean removeItem(String productName) {
        boolean flag = false;
        for (int i = 0; i < products.size(); i++) {
            if (productName == products.get(i).getProduct().getName()) {
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
            catalog.updateProductQtyInStock(products.get(i).getProduct().getId(), products.get(i).getQuantity());
            total += products.get(i).getTotalPrice();
        }
        Order order = new Order(products, customer, OrderStatus.CREATED, total);
        orders.add(order);
        return order.getOrderID();
    }


    public boolean pay(String num, int id) {

    }

    public void updateOrderStatus(int orderId, OrderStatus newStatus) {
        Order order = orders.get(orderId);
        order.setOrderStatus(newStatus);
    }

    public void cancelOrder(int orderId) {
        for (int i = 0; i < orders.size(); i++) {
            if (orderId == orders.get(i).getOrderID()) {
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
