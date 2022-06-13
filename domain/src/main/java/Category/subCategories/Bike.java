package Category.subCategories;

import Category.Category;

public class Bike extends Category {
    public Bike() {
        super("Bike");
    }
    public static Bike getInstance(){
        return new Bike();
    }
}
