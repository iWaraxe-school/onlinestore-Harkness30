package Operations.Handlers;

import Operations.Action;
import Operations.ActionType;
import Product.Product;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import utils.DBConnection.DBQuery;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static Operations.ActionType.*;
import static io.restassured.RestAssured.given;

public class OrderCreatingHandler extends ActionsHandler {
    private ActionType actionType = CREATE_ORDER;
    private Product orderedProduct;

    @Override
    public void executeAction(Action action) {
        if (actionType.getValue().equals(action.getType())) {

            System.out.println("Enter the name of a product you need:\n");
            orderedProduct = findProductByName(scanner.nextLine());
            HttpClient client = HttpClient.newHttpClient();
            given().baseUri("http://localhost:8081")
                    .body(orderedProduct.getName())
                    .post("/post");

//            new Thread(new OrderCreator(orderedProduct)).start();   // creating new thread for each order
        } else next.executeAction(action);
    }

    private Product findProductByName(String productName) {
    return new DBQuery().getProductFromDB(productName);
    }
}
