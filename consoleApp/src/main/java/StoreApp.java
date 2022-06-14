
import utils.parserAndComparator.XMLParser;
import utils.populator.ReflectionsRunner;
import Store.Store;

import java.util.Map;
import java.util.Scanner;

public class StoreApp {

    public static void main(String[] args) {
/*
        Store store = new Store();
        ReflectionsRunner runner = new ReflectionsRunner(store);
        runner.initStorageData();
        store.printAll();
*/
        XMLParser parser = new XMLParser();
        Scanner scanner = new Scanner(System.in);
        parser.setOptions(scanner.nextLine(), scanner.nextLine());
        Map<String, String> options = parser.getSortOptions();
        System.out.println(options.size());
    }

}
