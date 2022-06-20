package utils.populator;

import Category.Category;
import Product.Product;
import Store.Store;
import org.reflections.Reflections;
import org.reflections.scanners.Scanners;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ReflectionsRunner {
    private Store store;

    public ReflectionsRunner(Store store) {
        this.store = store;
    }

    private Map<Category, Integer> getCategoryProductMap() {

        Map<Category, Integer> categories = new HashMap<>();
        Reflections ref = new Reflections("Category.subCategories");

        Set<Class<?>> subTypes = ref.get(Scanners.SubTypes.of(Category.class).asClass());

        for (Class<?> type : subTypes) {
            try {
                Category temp = (Category) type.getDeclaredMethod("getInstance").invoke(new Object());
                categories.put(temp, 5);
            } catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException exception) {
                exception.printStackTrace();
            }
        }
        return categories;
    }

    public void initStorageData() {
        RandomStorePopulator populator = new RandomStorePopulator();
        Map<Category, Integer> categoryProductMapToAdd = getCategoryProductMap();
        for (Map.Entry<Category, Integer> entry : categoryProductMapToAdd.entrySet()) {
            for (int i = 0; i < entry.getValue(); i++) {
                Product product = populator.getRandomProduct();
                entry.getKey().addProduct(product);
            }
            store.addCategory(entry.getKey());
        }
    }
}
