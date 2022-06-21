package Operations.Handlers;

import Operations.Order;
import utils.parserAndComparator.MyComparator;
import utils.parserAndComparator.XMLParser;

import java.util.Map;

public abstract class OrderHandler {
    static final XMLParser PARSER = new XMLParser();
    static final MyComparator COMPARATOR = new MyComparator();
    static Map<String, String> options;

    OrderHandler next;

    public OrderHandler linkWith(OrderHandler next){
        this.next = next;
        return next;
    }

    public abstract void executeOrder (Order order);
}
