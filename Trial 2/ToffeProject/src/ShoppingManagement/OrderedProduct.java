package src.ShoppingManagement;

/**
 * Represents the cart details for an order therefore Acts as a container that saves the product and its quantity to be ordered.
 */
public class OrderedProduct {

    private Product product;
    private int quantity;
    private double totalPrice;

    /** constructor sets cart details.
     * @param productID product ID to be added.
     * @param quantity  quantity required from each product.
     * @param price price of each product depending on quantity.
     */
    public OrderedProduct(Product productID, int quantity, double price) {
        this.product = productID;
        this.quantity = quantity;
        this.totalPrice = price;
    }

    /**
     * updates the quantity of the product needed .
     * @param newQuantity new number of quantity required.
     * @return true if quantity available , false otherwise.
     */
    public boolean updateQuantity(int newQuantity) {
        if(product.getQuantityInStock() >= newQuantity) {
            this.quantity = newQuantity;
            totalPrice = newQuantity*this.product.getPrice();
            return true;
        }
        return false;
    }
    /** Getters and Setters*/
    public Product getProduct() {
        return product;
    }

    public double getTotalPrice() {
        return this.totalPrice;
    }

    public int getQuantity() {
        return quantity;
    }
}