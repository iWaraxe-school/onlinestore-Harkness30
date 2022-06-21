package Operations.Handlers;

import Operations.Order;
import Operations.OrderType;

import static Operations.OrderType.QUIT;

public class QuitHandler extends OrderHandler {

    private OrderType orderType = QUIT;

    @Override
    public void executeOrder(Order order) {
        if (orderType.getValue().equals(order.getType())) {
            PARSER.cleanUpConfigs();
            System.exit(1);
        } else next.executeOrder(order);

    }
}
