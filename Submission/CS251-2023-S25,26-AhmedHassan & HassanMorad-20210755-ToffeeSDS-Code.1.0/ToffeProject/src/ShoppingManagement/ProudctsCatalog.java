package src.ShoppingManagement;

import java.util.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;
import java.io.BufferedReader;
import java.io.FileReader;

/**
 * Represents the collection of all products that are available for purchase in the database and update the database after each order.
 */
public class ProudctsCatalog {
    private static Vector<Product> products = null;

    public ProudctsCatalog() {
        if (products == null) {
            products = new Vector<>();
            loadCatalog();
        }
    }

    /**
     *
     * load the catalog data from text file.
     */
    public static void loadCatalog() {
        String fileName = "catalog.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            Vector<String> productParts = new Vector<>();
            int cnt = 0;
            while ((line = reader.readLine()) != null) {

                String[] parts = line.split(",");
                for (String part : parts) {
                    productParts.add(part.trim());
                }
                cnt++;
                if (cnt == 6) {
                    Product product = new Product(productParts.get(0), productParts.get(1), Double.parseDouble(productParts.get(2)),
                            Integer.parseInt(productParts.get(3)), productParts.get(4), productParts.get(5));
                    products.add(product);
                    productParts.clear();
                    cnt = 0;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * searches for a product in the catalog.
     * @param name name to search about.
     * @return product found.
     */
    public static Product search(String name) {
        for (Product product : products) {
            if (Objects.equals(product.getName(), name)) {
                return product;
            }
        }
        return null;
    }
    /**
     * updates the product quantity in stock when added to cart/ordered and rewrites the catalog file.
     * @param editedProductId product ID chosen
     * @param quantity to be edited with.
     */
    public static void updateProductQtyInStock(int editedProductId, int quantity) {
        if (products == null) {
            loadCatalog();
        }
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() == editedProductId) {
                if (products.get(i).getQuantityInStock() - quantity < 0) {
                    break;
                }
                products.get(i).setQuantityInStock(products.get(i).getQuantityInStock() - quantity);
                String fileName = "catalog.txt";
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
                    for (int j = 0; j < products.size(); j++) {
                        writer.write(products.get(j).getName() + ',');
                        writer.newLine();
                        writer.write(products.get(j).getDescription() + ',');
                        writer.newLine();
                        writer.write("" + (products.get(j).getPrice()) + ',');
                        writer.newLine();
                        writer.write("" + (products.get(j).getQuantityInStock()) + ',');
                        writer.newLine();
                        writer.write(products.get(j).getCategory() + ',');
                        writer.newLine();
                        writer.write(products.get(j).getBrand() + ',');
                        writer.newLine();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }

    /** Getters and Setters*/
    public static Vector<Product> getProducts() {
        return products;
    }
}

