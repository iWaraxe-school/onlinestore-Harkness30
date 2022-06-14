package utils.parserAndComparator;

import Product.Product;
import java.util.Comparator;


public class MyComparator implements Comparator<Product> {

    @Override
    public int compare(Product o1, Product o2) {
        return o1.getName().compareTo(o2.getName());
    }
/*
    public Comparator<Product> getComparator(String field){
        switch (field){
            case  "name": return (o1, o2) ->
        }
    }
*/
}
