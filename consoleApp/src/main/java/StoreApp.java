
import Category.Category;
import Operations.Operations;
import Product.Product;
import Store.Store;
import java.util.List;
import java.util.Scanner;

public class StoreApp {

    private static  final Store STORE = new Store();
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final List<Product> PRODUCTS = Category.getAllProducts();

    public static void main(String[] args) {

        System.out.println("There are " + PRODUCTS.size() + " products are available in the Store! " +
                "Select an action from available:\n\n" +
                "sort - (to see all amount of products, sorted by price/rate/name)\n" +
                "top - (to see the list of top 5 most expensive products)\n" +
                "quit - (to exit the Store)");

        while(true){
            switch (SCANNER.nextLine().toLowerCase()) {
                case "sort" -> Operations.sort(SCANNER, PRODUCTS);
                case "top"  -> Operations.top(PRODUCTS);
                case "quit" -> Operations.quit();
            }
        }

    }
}
