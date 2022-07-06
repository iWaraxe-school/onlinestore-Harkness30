package Operations.Handlers;

import Operations.Action;
import Operations.ActionType;
import Product.Product;
import utils.DBConnection.DBQuery;
import static Operations.ActionType.*;
import static io.restassured.RestAssured.given;

public class OrderCreatingHandler extends ActionsHandler {
    private ActionType actionType = CREATE_ORDER;
    private Product orderedProduct;
    private static final String BASE_URI = "http://localhost:8081";
    private static final String USERNAME = "user";
    private static final String PASSWORD = "pass";

    @Override
    public void executeAction(Action action) {
        if (actionType.getValue().equals(action.getType())) {

            System.out.println("Enter the name of a product you need:\n");
            orderedProduct = findProductByName(scanner.nextLine());
            given()
                    .auth()
                    .basic(USERNAME, PASSWORD)
                    .baseUri(BASE_URI)
                    .body(orderedProduct.getName())
                    .post("/post");

        } else next.executeAction(action);
    }

    private Product findProductByName(String productName) {
    return new DBQuery().getProductFromDB(productName);
    }
}
