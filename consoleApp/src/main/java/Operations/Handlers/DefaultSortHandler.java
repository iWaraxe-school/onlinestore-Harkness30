package Operations.Handlers;

import Operations.Action;
import Operations.ActionType;
import Product.Product;

import java.util.List;

import static Operations.ActionType.*;

public class DefaultSortHandler extends ActionsHandler {
    private ActionType actionType = SORT;

    @Override
    public void executeAction(Action action) {

        if (actionType.getValue().equals(action.getType())) {
            List<Product> sorted = COMPARATOR.sortProducts(action.getProductsList(), PARSER.sortDefault());
            System.out.println(sorted);
        } else next.executeAction(action);

    }
}
