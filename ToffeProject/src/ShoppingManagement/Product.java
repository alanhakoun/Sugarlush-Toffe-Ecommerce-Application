package ShoppingManagement;
import java.util.*;

public class Product {
    static private int id;
    private String name;
    private String category;
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

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        Product.id = id;
    }


}
