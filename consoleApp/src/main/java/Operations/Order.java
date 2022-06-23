package Operations;

import Product.Product;

import java.util.List;

public class Order {
    private String orderName;
    private List<Product> productsList;

    public Order(String type, List<Product> productsList) {
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
