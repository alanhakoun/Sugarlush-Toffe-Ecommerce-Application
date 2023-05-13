package src.ShoppingManagement;

import java.util.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;
import java.io.BufferedReader;
import java.io.FileReader;

public class ProudctsCatalog {
    private static Vector<Product> products = null;

    public ProudctsCatalog() {
        if (products == null) {
            products = new Vector<>();
            loadCatalog();
        }
    }

    public static void loadCatalog() {
        String fileName = "C:\\Users\\alanh\\Documents\\GitHub\\SW-Assignment-2\\Trial 2\\ToffeProject\\src\\ShoppingManagement\\catalog.txt";
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

    public static void displayAll() {
        System.out.println("ID - Name - Quantity - Price - Category - Brand\n");
        for (Product product : products) {
            System.out.println(product.getId() + " - " + product.getName() + " - " + product.getQuantityInStock() + " - " + product.getPrice() + " - " + product.getCategory() + " - " + product.getBrand());
            System.out.println("    Description: " + product.getDescription());
        }
    }

    public static void displayByCategory(String category) {
        System.out.println("ID - Name - Quantity - Price - Category - Brand\n");
        for (Product product : products) {
            if (product.getCategory().equals(category)) {
                System.out.println(product.getId() + " - " + product.getName() + " - " + product.getQuantityInStock() + " - " + product.getPrice() + " - " + product.getCategory() + " - " + product.getBrand());
                System.out.println("    Description: " + product.getDescription());
            }
        }
    }


    public static Product searchByName(String name) {
        System.out.println("ID - Name - Quantity - Price - Category - Brand\n");
        for (Product product : products) {
            if (Objects.equals(product.getName(), name)) {
                System.out.println(product.getId() + " - " + product.getName() + " - " + product.getQuantityInStock() + " - " + product.getPrice() + " - " + product.getCategory() + " - " + product.getBrand());
                System.out.println("    Description: " + product.getDescription());
                return product;
            }
        }
        return null;
    }

    public static Product search(String name) {
        for (Product product : products) {
            if (Objects.equals(product.getName(), name)) {
                return product;
            }
        }
        return null;
    }

    public static void searchByBrand(String brand) {
        System.out.println("ID - Name - Quantity - Price - Category - Brand\n");
        for (Product product : products) {
            if (Objects.equals(product.getBrand(), brand)) {
                System.out.println(product.getId() + " - " + product.getName() + " - " + product.getQuantityInStock() + " - " + product.getPrice() + " - " + product.getCategory() + " - " + product.getBrand());
                System.out.println("    Description: " + product.getDescription());
            }
        }
    }

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
                String fileName = "C:\\Users\\alanh\\Documents\\GitHub\\SW-Assignment-2\\Trial 2\\ToffeProject\\src\\ShoppingManagement\\catalog.txt";
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

    // Getters and Setters
    public static Vector<Product> getProducts() {
        return products;
    }
}

