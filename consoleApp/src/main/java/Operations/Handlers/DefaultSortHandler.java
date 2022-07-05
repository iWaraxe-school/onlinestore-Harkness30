package Operations.Handlers;

import Operations.Action;
import Operations.ActionType;

import static Operations.ActionType.*;

public class DefaultSortHandler extends ActionsHandler {
    private ActionType actionType = SORT;

    @Override
    public void executeAction(Action action) {

        if (actionType.getValue().equals(action.getType())) {
            request.executeGetRequest("/get?data=sort");
        } else next.executeAction(action);

    }
}
