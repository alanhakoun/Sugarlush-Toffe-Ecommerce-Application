package ShoppingManagement;

public class Product {
    static private int pid;
    private String name;
    private String description;
    private double price;
    private int quantityInStock;

    public Product(String name, String description, double price, int quantityInStock) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantityInStock = quantityInStock;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }

}
