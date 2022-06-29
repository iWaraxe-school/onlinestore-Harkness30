package Operations.Handlers;

import Operations.Action;
import Operations.ActionType;
import Store.Store;
import utils.DBConnection.DBQuery;

import static Operations.ActionType.*;

public class CheckCartHandler extends ActionsHandler{
    private final ActionType actionType = CART;
    @Override
    public void executeAction(Action action) {
        if (actionType.getValue().equals(action.getType())) {
            new DBQuery().printCartFromDB();
    } else next.executeAction(action);
    }
}
