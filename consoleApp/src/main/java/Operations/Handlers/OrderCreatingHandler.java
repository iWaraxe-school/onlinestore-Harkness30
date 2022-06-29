package Operations.Handlers;

import Operations.Action;
import Operations.ActionType;
import Operations.Ordering.OrderCreator;
import Product.Product;
import utils.DBConnection.DBQuery;

import static Operations.ActionType.*;

public class OrderCreatingHandler extends ActionsHandler {
    private ActionType actionType = CREATE_ORDER;
    private Product orderedProduct;

    @Override
    public void executeAction(Action action) {
        if (actionType.getValue().equals(action.getType())) {

            System.out.println("Enter the name of a product you need:\n");
            orderedProduct = findProductByName(scanner.nextLine());
            new Thread(new OrderCreator(orderedProduct)).start();   // creating new thread for each order
        } else next.executeAction(action);
    }

    private Product findProductByName(String productName) {
    return new DBQuery().getProductFromDB(productName);
    }
}
