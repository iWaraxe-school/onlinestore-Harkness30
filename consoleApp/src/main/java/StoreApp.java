
import Operations.Action;
import Operations.Handlers.*;
import Product.Product;
import Store.Store;
import utils.DBConnection.DBConnector;
import utils.DBConnection.TablesCreator;
import utils.PurchasedProductsListCleaner;
import utils.populator.DBFiller;
import utils.populator.ReflectionsRunner;

import java.util.List;
import java.util.Scanner;
public class StoreApp {

    private static final Store STORE = Store.getInstance();
    private static final ReflectionsRunner RUNNER = new ReflectionsRunner(STORE);
    private static final Scanner SCANNER = new Scanner(System.in);
    private static List<Product> PRODUCTS;

    public static void main(String[] args) {
        new TablesCreator().initTables();
        new DBFiller().filInTheStore();
        PRODUCTS = STORE.getAllProducts();
        STORE.printAll();
        new Thread(new PurchasedProductsListCleaner()).start(); // Independent thread,
                                                                // cleans the list of purchased products ich 2 mins

        System.out.println("There are " + PRODUCTS.size() + " products are available in the Store! " +
                "Select an action from available:\n\n" +
                "sort by field - (to see all amount of products, sorted by price/rate/name)\n" +
                "sort - (to see all amount of products, sorted by default settings)\n" +
                "top - (to see the list of top 5 most expensive products)\n" +
                "order - (to create a new order)\n" +
                "cart - (to see all purchased products)\n" +
                "quit - (to exit the Store)");

        while (true) {
            Action action = new Action(SCANNER.nextLine().trim().toLowerCase(), PRODUCTS);
            ActionsHandler chain = new DefaultSortHandler();
            chain.linkWith(new SortHandler())
                    .linkWith(new TopHandler())
                    .linkWith(new QuitHandler())
                    .linkWith(new CheckCartHandler())
                    .linkWith(new OrderCreatingHandler())
                    .linkWith(new ClosingHandler());
            chain.executeAction(action);
        }
    }


}
