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
        if(productID.quantityInStock <= newQuantity) {
            this.quantity = newQuantity;
        } else {
            System.out.println("Not enough quantity in stock!");
        }
    }

    public void updateTotalPrice() {
        int newPrice = quantity*productID.totalPrice;
        this.totalPrice = newPrice;
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