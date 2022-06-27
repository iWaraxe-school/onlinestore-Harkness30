package Operations;

import Product.Product;
import java.util.List;

public class Action {
    private String orderName;
    private List<Product> productsList;

    public Action(String type, List<Product> productsList) {
        this.orderName = type;
        this.productsList = productsList;
    }

    public String getType() {
        return orderName;
    }

    public List<Product> getProductsList() {
        return productsList;
    }
}
