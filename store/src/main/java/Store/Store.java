package Store;

import Category.Category;
import Product.Product;
import utils.DBConnection.DBQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Store {
    private static Store store;
    private List<Category> categories = new ArrayList<>();

    private List<Product> purchasedProducts = new CopyOnWriteArrayList<>();

    public void addCategory(Category category) {
        categories.add(category);
    }

    public List<Product> getAllProducts() {
//        List<Product> result = new ArrayList<>();
//        for (Category c : categories) {
//            result.addAll(c.getProducts());
//        }
        return new DBQuery().getAllProductsFromDB();
    }

    public List<Product> getPurchasedProducts() {
        return purchasedProducts;
    }

    public void addPurchasedProduct(Product product) {
        this.purchasedProducts.add(product);
    }

    public void cleanUpPurchasedProductsList() {
        purchasedProducts.clear();
    }

    public void printAll() {
//        for (Category c : categories) {
//            System.out.printf("\n\nCategory %s contains %d products:\n", c.getName(), c.getProducts().size());
//            System.out.println(c.getProducts());
//        }
        System.out.println(getAllProducts());
    }

    public static synchronized Store getInstance() {
        if (store == null) {
            store = new Store();
        }
        return store;
    }
}
