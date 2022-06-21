package Operations.Handlers;

import Operations.Order;
import Operations.OrderType;
import Product.Product;

import java.util.List;
import java.util.Scanner;

import static Operations.OrderType.SORT_BY_EXACT_FIELD;

public class SortHandler extends OrderHandler {
    private OrderType orderType = SORT_BY_EXACT_FIELD;
    private Scanner scanner = new Scanner(System.in);
    private String field;
    private String sortingOrder;

    @Override
    public void executeOrder(Order order) {

        if (orderType.getValue().equals(order.getType())) {

            System.out.println("Select a field to sort by ( name / rate / price)");
            field = scanner.nextLine().toLowerCase();

            System.out.println("Select a sorting order ( asc / desc )");
            sortingOrder = scanner.nextLine().toLowerCase();

            options = PARSER.getSortOptions(field, sortingOrder);
            List<Product> sortedList = COMPARATOR.sortProducts(order.getProductsList(), options);
            System.out.printf("Here is our products, sorted by the '%s' in '%s' order:\n", field, order);
            System.out.println(sortedList);
        } else next.executeOrder(order);

    }

}
