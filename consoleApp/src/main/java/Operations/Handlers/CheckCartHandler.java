package Operations.Handlers;

import Operations.Action;
import Operations.ActionType;
import Store.Store;

import static Operations.ActionType.*;

public class CheckCartHandler extends ActionsHandler{
    private final ActionType actionType = CART;
    @Override
    public void executeAction(Action action) {
        if (actionType.getValue().equals(action.getType())) {
            System.out.println(Store.getInstance().getPurchasedProducts());
    } else next.executeAction(action);
    }
}
