package src.ShoppingManagement;
public class OrderedProduct {

    private Product product;



    private int quantity;
    private double totalPrice;

    public OrderedProduct(Product productID, int quantity, double price) {
        this.product = productID;
        this.quantity = quantity;
        this.totalPrice = price;
    }

    public void updateQuantity(int newQuantity) {
        if(product.getQuantityInStock() <= newQuantity) {
            this.quantity = newQuantity;
        } else {
            System.out.println("Not enough quantity in stock!");
        }
    }

    public void updateTotalPrice() {
        this.totalPrice = quantity* product.getPrice();
    }
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public double getTotalPrice() {
        return this.totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}