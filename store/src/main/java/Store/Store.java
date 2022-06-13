package Store;

import Category.Category;
import Product.Product;

import java.util.ArrayList;
import java.util.List;

public class Store {
    private List<Category> categories = new ArrayList<>();

    public void addCategory(Category category) {
        categories.add(category);
    }

    public void addCategories(List<Category> list) {
        categories.addAll(list);
    }

    public List<Category> getAllCategories() {
        return categories;
    }

    public void printAll() {
        for (Category c : categories) {
            System.out.printf("\n\nCategory %s contains %d products:\n", c.getName(), c.getAllProducts().size());
            c.getAllProducts().forEach(Product::getProductInformation);
        }
    }
}
