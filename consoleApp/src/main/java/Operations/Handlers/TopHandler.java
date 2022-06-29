package Operations.Handlers;

import Operations.Action;
import Operations.ActionType;
import Product.Product;
import utils.DBConnection.DBQuery;

import java.util.List;
import java.util.Map;

import static Operations.ActionType.TOP;

public class TopHandler extends ActionsHandler {

    private ActionType actionType = TOP;

    @Override
    public void executeAction(Action action) {

        if (actionType.getValue().equals(action.getType())) {
//            Map<String, String> options = PARSER.getSortOptions("price", "desc");
//            List<Product> top = COMPARATOR.sortProducts(action.getProductsList(), options);
            System.out.println("Here is our 5 most expensive products:");
//            for (int i = 0; i < 5; i++) {
//                System.out.println(top.get(i));
//            }
            new DBQuery().printTopFiveFromDB();
        } else next.executeAction(action);

    }
}
