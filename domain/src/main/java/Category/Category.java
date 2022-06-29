package Category;

import Product.Product;
import utils.DBConnection.DBQuery;

import java.util.ArrayList;
import java.util.List;

public class Category {

    private String name;
    private List<Product> products = new ArrayList<>();

    public Category(String name) {
        this.name = name;
    }



    public String getName() {
        return this.name;
    }


}
