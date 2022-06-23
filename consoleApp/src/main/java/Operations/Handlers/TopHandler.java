package Operations.Handlers;

import Operations.Order;
import Operations.OrderType;
import Product.Product;

import java.util.List;
import java.util.Map;

import static Operations.OrderType.TOP;

public class TopHandler extends OrderHandler {

    private OrderType orderType = TOP;

    @Override
    public void executeOrder(Order order) {

        if (orderType.getValue().equals(order.getType())) {
            Map<String, String> options = PARSER.getSortOptions("price", "desc");
            List<Product> top = COMPARATOR.sortProducts(order.getProductsList(), options);
            System.out.println("Here is our 5 most expensive products:");
            for (int i = 0; i < 5; i++) {
                System.out.println(top.get(i));
            }
        } else next.executeOrder(order);

    }
}
