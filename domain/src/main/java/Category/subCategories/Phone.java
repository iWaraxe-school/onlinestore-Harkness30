package Category.subCategories;

import Category.Category;

public class Phone extends Category {

 public   Phone() {
        super("Phone");
    }public static Phone getInstance(){
        return new Phone();
    }
}

