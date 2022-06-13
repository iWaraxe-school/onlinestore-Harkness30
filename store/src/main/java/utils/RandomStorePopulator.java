package utils;

import Product.Product;
import com.github.javafaker.Faker;

public class RandomStorePopulator {
    Faker faker = new Faker();

    public Product getRandomProduct() {
        return new Product(getName(), getRate(), getPrice());
    }

    private String getName() {
        String name = faker.color().name() + " " + faker.animal().name();
        return name;
    }

    private int getRate() {
        return faker.random().nextInt(0, 5);
    }

    private double getPrice() {
        return faker.random().nextInt(10, 100) * faker.random().nextDouble();
    }
}
