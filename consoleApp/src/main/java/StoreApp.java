
import Http.Server;
import Operations.Action;
import Operations.Handlers.*;
import Store.Store;
import utils.DBConnection.DBQuery;
import utils.DBConnection.TablesCreator;
import utils.PurchasedProductsListCleaner;
import utils.populator.DBFiller;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class StoreApp {
    private static final Store STORE = Store.getInstance();
    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        new TablesCreator().initTables();       // creating tables for Categories, Products and Purchased Products
        new DBFiller().filInTheStore();         // categories and products tables are filling in by fake data
        STORE.printAll();
        new Thread(new PurchasedProductsListCleaner()).start(); // Independent thread,
                                                                // cleans the list of purchased products ich 2 mins
        try {
            new Server();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Select an action from available:\n\n" +
                "sort by field - (to see all amount of products, sorted by price/rate/name)\n" +
                "sort - (to see all amount of products, sorted by default settings)\n" +
                "top - (to see the list of top 5 most expensive products)\n" +
                "order - (to create a new order)\n" +
                "cart - (to see all purchased products)\n" +
                "quit - (to exit the Store)");
//        HttpClient client = HttpClient.newHttpClient();
//        HttpRequest request = HttpRequest.newBuilder()
//                .uri(URI.create("http://localhost:8081"))
//                .version(HttpClient.Version.HTTP_1_1)
//                .GET()
//                .build();
//        try {
//            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
//            System.out.println(response.statusCode());
//            System.out.println(response.body());
//        } catch (IOException | InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//        System.out.println();

        while (true) {
            Action action = new Action(SCANNER.nextLine().trim().toLowerCase());
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
