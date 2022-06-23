package Product;

public class ProductBuilder {
    private String name;
    private int rate;
    private double price;

    public ProductBuilder setProductName(String name){
        this.name = name;
        return this;
    }
    public ProductBuilder setProductRate(int rate){
        this.rate = rate;
        return this;
    }
    public ProductBuilder setProductPrice(double price){
        this.price=price;
        return this;
    }
    public Product buildProduct(){
        Product prod = new Product();
        prod.setName(name);
        prod.setRate(rate);
        prod.setPrice(price);
        return prod;
    }

}
