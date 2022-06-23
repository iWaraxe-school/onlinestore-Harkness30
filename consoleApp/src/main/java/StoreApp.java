
import Operations.*;
import Operations.Handlers.*;
import Product.Product;
import Store.Store;
import utils.populator.ReflectionsRunner;

import java.util.List;
import java.util.Scanner;

public class StoreApp {

    private static final Store STORE = Store.getInstance();
    private static final ReflectionsRunner RUNNER = new ReflectionsRunner(STORE);
    private static final Scanner SCANNER = new Scanner(System.in);
    private static List<Product> PRODUCTS;

    public static void main(String[] args) {
        RUNNER.initStorageData();
        PRODUCTS = STORE.getAllProducts();
        STORE.printAll();


        System.out.println("There are " + PRODUCTS.size() + " products are available in the Store! " +
                "Select an action from available:\n\n" +
                "sort by field - (to see all amount of products, sorted by price/rate/name)\n" +
                "sort - (to see all amount of products, sorted by default settings)\n" +
                "top - (to see the list of top 5 most expensive products)\n" +
                "quit - (to exit the Store)");

        while (true) {
            Order order = new Order(SCANNER.nextLine().trim().toLowerCase(), PRODUCTS);
            OrderHandler chain = new DefaultSortHandler();
            chain.linkWith(new SortHandler())
                    .linkWith(new TopHandler())
                    .linkWith(new QuitHandler())
                    .linkWith(new ClosingHandler());
            chain.executeOrder(order);
        }
    }


}
