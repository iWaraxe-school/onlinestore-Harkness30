package Category.subCategories;

import Category.Category;

public class Bike extends Category {
    private static Bike bike;
    private Bike() {
        super("Bike");
    }
    public static synchronized Bike getInstance(){
        if (bike ==null){
            bike = new Bike();
        }
        return bike;
    }
}
