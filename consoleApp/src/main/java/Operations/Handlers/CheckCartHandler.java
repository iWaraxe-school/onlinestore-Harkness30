package Operations.Handlers;

import Operations.Action;
import Operations.ActionType;


import static Operations.ActionType.*;

public class CheckCartHandler extends ActionsHandler {
    private final ActionType actionType = CART;

    @Override
    public void executeAction(Action action) {
        if (actionType.getValue().equals(action.getType())) {
            request.executeGetRequest("/get?data=cart");
        } else next.executeAction(action);
    }
}
