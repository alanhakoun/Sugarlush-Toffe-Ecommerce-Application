package src.ShoppingManagement;
import java.util.*;

/**
 * Contain the items that are available for purchase. Each product has a unique id, name, description, quantity in stock and price.
 */
public class Product {
    private int id;
    static private int cnt = 1;
    private String name;
    private String category;
    private String description;
    private String brand;
    private double price;
    private int quantityInStock;

    /** constructor sets product details.
     * @param name product name
     * @param description product content descrption
     * @param price product price
     * @param quantityInStock product quantity available in stock
     * @param category product category : sealed / loose
     * @param brand product brand name
     */
    public Product( String name , String description, double price, int quantityInStock,String category,String brand) {
        this.id = cnt;
        cnt++;
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantityInStock = quantityInStock;
        this.category = category;
        this.brand = brand;
    }

    /** Getters and Setters*/
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

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
    public void setQuantityInStock(int quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    public String getDescription() {
        return description;
    }
    public String getBrand() {
        return brand;
    }


}

