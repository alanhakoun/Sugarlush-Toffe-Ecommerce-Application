package src.ShoppingManagement;

import src.UsersManagement.*;

import java.util.*;

/**
 * Represents the Application manager class.It’s the class that controls the shopping process that begins with adding an item to the cart and ends with paying and shipping this order to its customer and closing the order. It is also connected to the catalog to update it after the order is completed.
 */
public class controller {

    private final int max = 50;
    private ProudctsCatalog catalog;
    private Customer customer;
    private Vector<Order> orders = null;
    private Vector<OrderedProduct> products = null;

    /** constructor sets the databases to be used.
     * @param productsDatabase catalog.
     * @param user users.
     */
    public controller(ProudctsCatalog productsDatabase, Customer user) {
        catalog = productsDatabase;
        customer = user;
        orders = new Vector<>();
        products = new Vector<>();
    }

    /**
     * adds item to cart.
     * @param itemName product name to be added.
     * @param quantity required quantity of the product.
     * @return
     */
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

    /**
     * updates the quantity of an item in an order.
     * @param productName product name to be upadted.
     * @param quantity new required quantity of the product.
     * @return cases to GUI class.
     */
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

    /**
     * removes a product from an order.
     * @param productName  product name to be removed.
     * @return flag
     */
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

    /**
     * places an order
     * @param address teh order would be shipped to.
     * @return cases to GUI class.
     */
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

    /**
     * calls the payment method from cash payment.
     * @param num the phone number used for the payment
     * @param id order ID paying for.
     * @return
     */
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

    /**
     * updates Order status in the enum.
     * @param orderId order ID statues to br changed.
     * @param newStatus to update to.
     */
    public void updateOrderStatus(int orderId, OrderStatus newStatus) {
        Order order = orders.get(0);
        order.setOrderStatus(newStatus);
    }

    /**
     * cancels an order
     * @param orderId order ID to be canceled.
     */
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
