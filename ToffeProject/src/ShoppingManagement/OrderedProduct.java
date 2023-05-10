package ShoppingManagement;
public class OrderedProduct {

    private Product productID;
    private int quantity;
    private double totalPrice;

    public OrderedProduct(Product productID, int quantity, double price) {
        this.productID = productID;
        this.quantity = quantity;
        this.totalPrice = price;
    }

    public void updateQuantity(int newQuantity) {
        if(productID.getQuantityInStock() <= newQuantity) {
            this.quantity = newQuantity;
        } else {
            System.out.println("Not enough quantity in stock!");
        }
    }

    public void updateTotalPrice() {
        this.totalPrice = quantity*productID.getPrice();
    }
    public Product getProductID() {
        return productID;
    }

    public void setProduct(Product product) {
        this.productID = product;
    }

    public double getTotalPrice() {
        return this.totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}