package Store;

import Category.Category;
import utils.DBConnection.DBQuery;
import java.util.ArrayList;
import java.util.List;

public class Store {
    private static Store store;
    private List<Category> categories = new ArrayList<>();

    public void printAll() {
        new DBQuery().printAllProductsFromDB();
    }

    public static synchronized Store getInstance() {
        if (store == null) {
            store = new Store();
        }
        return store;
    }
}
