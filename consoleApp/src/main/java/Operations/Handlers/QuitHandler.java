package Operations.Handlers;

import Operations.Action;
import Operations.ActionType;
import utils.DBConnection.DBConnector;

import static Operations.ActionType.QUIT;

public class QuitHandler extends ActionsHandler {

    private ActionType actionType = QUIT;

    @Override
    public void executeAction(Action action) {
        if (actionType.getValue().equals(action.getType())) {
            DBConnector.getInstance().closeConnection();
            System.exit(1);
        } else next.executeAction(action);
    }
}
