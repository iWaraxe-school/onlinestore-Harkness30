package Operations.Handlers;

import Operations.Order;
import Operations.OrderType;
import Product.Product;

import java.util.List;

import static Operations.OrderType.*;

public class DefaultSortHandler extends OrderHandler {
    private OrderType orderType = SORT;

    @Override
    public void executeOrder(Order order) {

        if (orderType.getValue().equals(order.getType())) {
            List<Product> sorted = COMPARATOR.sortProducts(order.getProductsList(), PARSER.sortDefault());
            System.out.println(sorted);
        } else next.executeOrder(order);

    }
}
