package src;

import java.io.ObjectInputStream;
import java.util.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ProudctsCatalog {
    private Vector<Product> products;
    private String[] categories;

    public ProudctsCatalog(){
        products = new Vector<>();
    }

    public void loadCatalog(){
        String fileName = "C:\\projects\\test\\src\\src\\catalog.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            Vector<String> productParts = new Vector<>();
            while ((line = reader.readLine()) != null) {

                String[] parts = line.split(",");
                for (String part : parts) {
                    productParts.add(part.trim());
                }
                Product product = new Product(productParts.get(0),productParts.get(1),Double.parseDouble(productParts.get(2)),
                        Integer.parseInt(productParts.get(3)),productParts.get(4));
                products.add(product);
                productParts.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void print(){
        for (int i = 0; i < products.size(); i++) {
            System.out.println(products.get(i).getName() + ' ' + products.get(i).getId());
        }
    }
    public void displayAll() {
        for (Product product : products) {
            System.out.println(product.toString());
        }
    }

    public void displayByCategory(String category) {
        for (Product product : products) {
            if (product.getCategory().equals(category)) {
                System.out.println(product.toString());
            }
        }
    }

    public Product search(String name) {
        for (Product product : products) {
            if (Objects.equals(product.getName(), name)) {
                return product;
            }
        }
        return null;
    }

   /*  public void addItem(Product product) {
        Product[] newProducts = new Product[products.length + 1];
        System.arraycopy(products, 0, newProducts, 0, products.length);
        newProducts[newProducts.length - 1] = product;
        products = newProducts;
    }

   public void cancelItem(int productId) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() == productId) {
                Product[] newProducts = new Product[products.size() - 1];
                System.arraycopy(products, 0, newProducts, 0, i);
                System.arraycopy(products, i + 1, newProducts, i, products.size() - i - 1);
                products = newProducts;
                break;
            }
        }
    }

    public void updateItem(int productId, Product updatedProduct) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() == productId) {
                products.set(i, updatedProduct);
                break;
            }
        }
    }

    public void addOffers(double discount) {
        for (Product product : products) {
            product.setPrice(product.getPrice() * (1 - discount));
        }
    }*/

    // Getters and Setters
    public Vector<Product> getProducts() {
        return products;
    }

    public void setProducts(Vector<Product> products) {
        this.products = products;
    }

    public String[] getCategories() {
        return categories;
    }

    public void setCategories(String[] categories) {
        this.categories = categories;
    }
}
