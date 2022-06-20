package Product;

public class Product implements Comparable<Product>{
    private String name;
    private int rate;
    private double price;

    public Product(String name, int rate, double price) {
        this.name = name;
        this.rate = rate;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getRate() {
        return rate;
    }

    @Override
    public String toString() {
        return String.format("""
                 Product name: "%s" \s
                \t\t rate: %d \s
                \t\t price: %.2f
                """, this.name, this.rate, this.price);
    }

    @Override
    public int compareTo(Product o) {
        return this.compareTo(o);
    }
}
