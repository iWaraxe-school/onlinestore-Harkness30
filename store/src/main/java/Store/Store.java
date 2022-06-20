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

    public List<Category> getCategories() {
        return categories;
    }

    public List<Product> getAllProducts(){
        List<Product> result = new ArrayList<>();
        for (Category c: categories){
            result.addAll(c.getProducts());
        }return result;
    }
    public void printAll() {
        for (Category c : categories) {
            System.out.printf("\n\nCategory %s contains %d products:\n", c.getName(), c.getProducts().size());
            System.out.println(c.getProducts());;
        }
    }
}
