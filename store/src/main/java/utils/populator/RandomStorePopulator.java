package utils.populator;

import Product.Product;
import com.github.javafaker.Faker;
import Product.ProductBuilder;
public class RandomStorePopulator {
    Faker faker = new Faker();

    public Product getRandomProduct() {
        Product temp =  new ProductBuilder()
                .setProductName(getName())
                .setProductRate(getRate())
                .setProductPrice(getPrice())
                .buildProduct();
        return temp;
    }

     String getName() {
        return faker.color().name() + " " + faker.animal().name();
    }

     int getRate() {
        return faker.random().nextInt(0, 5);
    }

     double getPrice() {
        return faker.random().nextInt(10, 100) * faker.random().nextDouble();
    }
}