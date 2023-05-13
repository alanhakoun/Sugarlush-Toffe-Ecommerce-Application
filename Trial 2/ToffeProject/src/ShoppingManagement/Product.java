package src.ShoppingManagement;
import java.util.*;

public class Product {
    private int id;
    static private int cnt = 1;
    private String name;
    private String category;
    private String description;



    private String brand;
    private double price;
    private int quantityInStock;


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

