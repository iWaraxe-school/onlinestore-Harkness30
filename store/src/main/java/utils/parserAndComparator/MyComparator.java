package utils.parserAndComparator;

import Product.Product;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;


public class MyComparator implements Comparator<Product> {

    @Override
    public int compare(Product o1, Product o2) {
        return o1.compareTo(o2);
    }

    public Comparator<Product> getComparator(String field) {
        return switch (field) {
            case "name" -> Comparator.comparing(Product::getName);
            case "rate" -> Comparator.comparing(Product::getRate);
            case "price" -> Comparator.comparing(Product::getPrice);
            default -> Comparator.comparing(Product::toString);
        };
    }

    public List<Product> sortProducts(List<Product> products, Map<String, String> sortType){

        List<Product> result = new ArrayList<>(products);
        for(Map.Entry<String, String> mapEntry: sortType.entrySet())
        {
            if(mapEntry.getValue().equals(SortingOrders.ASC.getValue())){
                result.sort(getComparator(mapEntry.getKey()));
            }else {
                result.sort(getComparator(mapEntry.getKey()).reversed());
            }
        }
        return result;
    }

}
