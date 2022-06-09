package Product;

public class Product {
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

    public void setName(String name) {
        this.name = name;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void getProductInformation() {
        try {Thread.sleep(1000);}
        catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.printf("""
                 Product name: \"%s\" \s
                \t\t rate: %d \s
                \t\t price: %.2f
                """, this.name, this.rate, this.price);
    }

}
