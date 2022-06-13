package Category.subCategories;

import Category.Category;

public class Milk extends Category {
    public Milk() {
        super("Milk");
    }

    public static Milk getInstance(){
        return new Milk();
    }
}
