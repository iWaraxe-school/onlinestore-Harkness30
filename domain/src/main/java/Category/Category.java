package Category;

import Product.Product;

import java.util.ArrayList;
import java.util.List;

public class Category {

    private String name;
    private List<Product> products = new ArrayList<>();

    protected Category(String name) {
        this.name = name;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public String getName() {
        return this.name;
    }

    public List<Product> getAllProducts() {
        return products;
    }
}
