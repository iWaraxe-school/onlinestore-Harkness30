package Operations.Handlers;

import Operations.Action;
import Operations.ActionType;
import utils.DBConnection.DBQuery;

import static Operations.ActionType.*;

public class DefaultSortHandler extends ActionsHandler {
    private ActionType actionType = SORT;

    @Override
    public void executeAction(Action action) {

        if (actionType.getValue().equals(action.getType())) {
            new DBQuery().printSortedListFromDB(PARSER.sortDefault());
        } else next.executeAction(action);

    }
}
