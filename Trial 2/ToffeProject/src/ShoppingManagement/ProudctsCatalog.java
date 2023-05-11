package src.ShoppingManagement;

import src.ShoppingManagement.Product;
import src.UsersManagement.Customer;

import java.io.ObjectInputStream;
import java.util.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ProudctsCatalog {
    private static Vector<Product> products = null;
    private static String[] categories;

    public ProudctsCatalog() {
        if (products == null) {
            loadCatalog();
        }
    }

    public static void loadCatalog() {
        String fileName = "C:\\projects\\test\\src\\src\\catalog.txt";
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
                if (cnt == 5) {
                    Product product = new Product(productParts.get(0), productParts.get(1), Double.parseDouble(productParts.get(2)),
                            Integer.parseInt(productParts.get(3)), productParts.get(4));
                    products.add(product);
                    productParts.clear();
                    cnt = 0;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void print() {
        for (int i = 0; i < products.size(); i++) {
            System.out.println(products.get(i).getName() + ' ' + products.get(i).getId());
        }
    }

    public static void displayAll() {
        System.out.println("ID - Name - Quantity - Price\n");
        int cnt = 1;
        for (Product product : products) {
            System.out.println(cnt++ + " - " + product.getName() + " - " + product.getQuantityInStock() + " - " + product.getPrice());
        }
    }

    public static void displayByCategory(String category) {
        System.out.println("ID - Name - Quantity - Price\n");
        int cnt = 1;
        for (Product product : products) {
            if (product.getCategory().equals(category)) {
                System.out.println(cnt++ + " - " + product.getName() + " - " + product.getQuantityInStock() + " - " + product.getPrice());
            }
        }
    }


    public static Product search(String name) {
        System.out.println("ID - Name - Quantity - Price\n");
        int cnt = 1;
        for (Product product : products) {
            if (Objects.equals(product.getName(), name)) {
                System.out.println(cnt++ + " - " + product.getName() + " - " + product.getQuantityInStock() + " - " + product.getPrice());
                return product;
            }
        }
        return null;
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
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
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
    public static Vector<Product> getProducts() {
        return products;
    }

    public static String[] getCategories() {
        return categories;
    }

}
