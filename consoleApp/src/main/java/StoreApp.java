import Category.Category;
import Category.subCategoies.Bike;
import Category.subCategoies.Milk;
import Category.subCategoies.Phone;
import Store.Store;
import org.reflections.Reflections;
import utils.RandomStorePopulator;
import static org.reflections.ReflectionUtils.*;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Set;

public class StoreApp {
    public static void main(String[] args) {
        RandomStorePopulator pop = new RandomStorePopulator();

        Category bike = new Bike();
        Category phone = new Phone();
        Category milk = new Milk();

        for (int a = 0; a < 5; a++) {
            bike.addProduct(pop.getRandomProduct());
        }
        for (int b = 0; b < 5; b++) {
            phone.addProduct(pop.getRandomProduct());
        }
        for (int c = 0; c < 5; c++) {
            milk.addProduct(pop.getRandomProduct());
        }

        Store store = new Store();
        store.addCategories(List.of(bike, phone, milk));

        store.printAll();
    }
}
