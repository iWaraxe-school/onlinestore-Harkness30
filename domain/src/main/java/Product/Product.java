package Product;

public class Product implements Comparable<Product>{
    private String name;
    private int rate;
    private double price;

    public void setName(String name) {
        this.name = name;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return String.format("""
                 Product name: %s \s
                \t\t rate: %d \s
                \t\t price: %.2f
                """, this.name, this.rate, this.price);
    }

    @Override
    public int compareTo(Product o) {
        return this.compareTo(o);
    }

}
