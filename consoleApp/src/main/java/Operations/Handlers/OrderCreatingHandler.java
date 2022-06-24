package Operations.Handlers;

import Operations.Action;
import Operations.ActionType;
import Operations.Ordering.OrderCreator;
import Product.Product;

import java.util.List;
import java.util.Optional;

import static Operations.ActionType.*;

public class OrderCreatingHandler extends ActionsHandler {
    private ActionType actionType = CREATE_ORDER;
    List<Product> products;
    private Product orderedProduct;

    @Override
    public void executeAction(Action action) {
        if (actionType.getValue().equals(action.getType())) {

            products = action.getProductsList();
            System.out.println("Enter the name of a product you need:\n");
            orderedProduct = findProductByName(scanner.nextLine());
            new Thread(new OrderCreator(orderedProduct)).start();   // creating new thread for each order
        } else next.executeAction(action);
    }

    private Product findProductByName(String productName) {
        Optional<Product> result = products
                .stream()
                .filter(product -> product.getName().equals(productName))
                .findAny();
        if (result.isEmpty()) {
            System.out.println("Product not found! Choose available product from list.");
        }
        return result.get();
    }
}
