package Category.subCategories;

import Category.Category;

public class Milk extends Category {
    private static Milk milk;
    private Milk() {
        super("Milk");
    }

    public static synchronized Milk getInstance(){
        if (milk == null){
         milk = new Milk();
        }
        return milk;
    }
}
