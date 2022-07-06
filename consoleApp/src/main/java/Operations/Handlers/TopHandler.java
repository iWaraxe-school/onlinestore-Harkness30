package Operations.Handlers;

import Operations.Action;
import Operations.ActionType;


import static Operations.ActionType.TOP;

public class TopHandler extends ActionsHandler {

    private ActionType actionType = TOP;

    @Override
    public void executeAction(Action action) {

        if (actionType.getValue().equals(action.getType())) {
            System.out.println("Here is our 5 most expensive products:");
            request.executeGetRequest("/get?data=top");
        } else next.executeAction(action);

    }
}
