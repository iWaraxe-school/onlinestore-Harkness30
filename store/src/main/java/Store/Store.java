package Store;

public class Store {
    private static Store store;

    public static synchronized Store getInstance() {
        if (store == null) {
            store = new Store();
        }
        return store;
    }
}
