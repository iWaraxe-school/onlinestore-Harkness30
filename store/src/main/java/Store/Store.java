package Store;

import Category.Category;
import Product.Product;
import utils.populator.ReflectionsRunner;

import java.util.ArrayList;
import java.util.List;

public class Store {
    private List<Category> categories = new ArrayList<>();
    private ReflectionsRunner runner;

    public Store(){
        this.runner = new ReflectionsRunner(this);
        runner.initStorageData();
    }
    public void addCategory(Category category) {
        categories.add(category);
    }

    public void printAll() {
        for (Category c : categories) {
            System.out.printf("\n\nCategory %s contains %d products:\n", c.getName(), Category.getAllProducts().size());
            Category.getAllProducts().forEach(Product::toString);
        }
    }
}
