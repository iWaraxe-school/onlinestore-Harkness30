package Category.subCategories;

import Category.Category;

public class Phone extends Category {
    private static Phone phone;
    private Phone() {
        super("Phone");
    }

    public static synchronized Phone getInstance() {
        if (phone==null){
            phone = new Phone();
        }
        return phone;
    }
}

