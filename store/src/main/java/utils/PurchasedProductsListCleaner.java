package utils;

import Store.Store;
import utils.DBConnection.DBQuery;

import java.util.concurrent.TimeUnit;

public class PurchasedProductsListCleaner implements Runnable {
    private static final Integer TIME_LIMIT = 2;
    private Store store = Store.getInstance();

    @Override
    public void run() {
        while (true) {
            new DBQuery().cleanUpPurchasedProductsTable();
            try {
                TimeUnit.MINUTES.sleep(TIME_LIMIT);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("\nThe list of purchased products has just been cleared in " + Thread.currentThread().getName());
        }
    }
}
