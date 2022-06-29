package Operations.Handlers;

import Operations.Action;
import Operations.ActionType;
import utils.DBConnection.DBQuery;

import java.util.Map;
import java.util.Scanner;

import static Operations.ActionType.SORT_BY_EXACT_FIELD;

public class SortHandler extends ActionsHandler {
    private ActionType actionType = SORT_BY_EXACT_FIELD;
    private Scanner scanner = new Scanner(System.in);
    private String field;
    private String sortingOrder;

    @Override
    public void executeAction(Action action) {

        if (actionType.getValue().equals(action.getType())) {

            System.out.println("Select a field to sort by ( name / rate / price)");
            field = scanner.nextLine().toLowerCase();

            System.out.println("Select a sorting order ( asc / desc )");
            sortingOrder = scanner.nextLine().toLowerCase();

            System.out.printf("Here is our products, sorted by the '%s' in '%s' order:\n", field, sortingOrder);
            new DBQuery().printSortedListFromDB(Map.of(field, sortingOrder));
        } else next.executeAction(action);

    }

}
