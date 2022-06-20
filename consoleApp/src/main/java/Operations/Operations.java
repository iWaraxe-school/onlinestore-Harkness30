package Operations;

import Product.Product;
import utils.parserAndComparator.MyComparator;
import utils.parserAndComparator.XMLParser;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Operations {

    private static final XMLParser PARSER = new XMLParser();
    private static MyComparator comparator = new MyComparator();
    private static Map<String, String> options;

    public static void sortXML(List<Product> products){
        List<Product> sorted = comparator.sortProducts(products, PARSER.sortDefault());
        System.out.println(sorted);
    }
    public static void sort(Scanner scanner, List<Product> products) {

        String field;
        String order;

        System.out.println("Select a field to sort by ( name / rate / price)");
        field = scanner.nextLine().toLowerCase();

        System.out.println("Select a sorting order ( asc / desc )");
        order = scanner.nextLine().toLowerCase();

        options = PARSER.getSortOptions(field, order);
        List<Product> sortedList = comparator.sortProducts(products, options);
        System.out.printf("Here is our products, sorted by the '%s' in '%s' order:\n", field, order);

        for (Product product : sortedList) {
            System.out.println(product);
        }
    }

    public static void top(List<Product> products) {

        Map<String, String> options = PARSER.getSortOptions("price", "desc");
        List<Product> topFive = comparator.sortProducts(products, options);
        System.out.println("Here is our 5 most expensive products:");

        for (int i = 0; i < 5; i++) {
            System.out.println(topFive.get(i).toString());
        }
    }
    public static void quit(){
        PARSER.cleanUpConfigs();
        System.exit(1);
    }
}
