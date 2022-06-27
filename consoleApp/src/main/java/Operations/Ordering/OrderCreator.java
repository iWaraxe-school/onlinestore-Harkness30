package Operations.Ordering;

import Product.Product;
import Store.Store;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class OrderCreator implements Runnable {

    private final Store STORE = Store.getInstance();
    private final Product PRODUCT;
    private Integer pause = new Random().nextInt(0, 30);

    public OrderCreator(Product product) {
        this.PRODUCT = product;
    }

    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(pause);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        STORE.addPurchasedProduct(PRODUCT);
        System.out.printf( "\n%s '%s' was successfully purchased after %d seconds\n",
                Thread.currentThread().getName(), PRODUCT.getName(), pause);
    }


}
