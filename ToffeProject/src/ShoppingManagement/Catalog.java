public class Catalog {
    private Product[] products;
    private String[] categories;

    public Catalog(Product[] products, String[] categories) {
        this.products = products;
        this.categories = categories;
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

    public Product search(int name) {
        for (Product product : products) {
            if (product.getName() == name) {
                return product;
            }
        }
        return null;
    }

    public void addItem(Product product) {
        Product[] newProducts = new Product[products.length + 1];
        System.arraycopy(products, 0, newProducts, 0, products.length);
        newProducts[newProducts.length - 1] = product;
        products = newProducts;
    }

    public void cancelItem(int productId) {
        for (int i = 0; i < products.length; i++) {
            if (products[i].getId() == productId) {
                Product[] newProducts = new Product[products.length - 1];
                System.arraycopy(products, 0, newProducts, 0, i);
                System.arraycopy(products, i + 1, newProducts, i, products.length - i - 1);
                products = newProducts;
                break;
            }
        }
    }

    public void updateItem(int productId, Product updatedProduct) {
        for (int i = 0; i < products.length; i++) {
            if (products[i].getId() == productId) {
                products[i] = updatedProduct;
                break;
            }
        }
    }

    public void addOffers(double discount) {
        for (Product product : products) {
            product.setPrice(product.getPrice() * (1 - discount));
        }
    }

    // Getters and Setters
    public Product[] getProducts() {
        return products;
    }

    public void setProducts(Product[] products) {
        this.products = products;
    }

    public String[] getCategories() {
        return categories;
    }

    public void setCategories(String[] categories) {
        this.categories = categories;
    }
}
