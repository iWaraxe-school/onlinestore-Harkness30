
import utils.ReflectionsRunner;
import Store.Store;

public class StoreApp {

    public static void main(String[] args) {

        Store store = new Store();
        ReflectionsRunner runner = new ReflectionsRunner(store);
        runner.initStorageData();
        store.printAll();
    }
}
