package utils.populator;

import Category.Category;
import Product.Product;
import org.reflections.Reflections;
import org.reflections.scanners.Scanners;
import utils.DBConnection.DBConnector;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DBFiller {
   private Connection connection = DBConnector.getInstance().getConnection();
    Set<Category> categorySet = new HashSet<>();

    public void filInTheStore(){
        setCategoriesTable();
        setProductsTable();
    }
    private void setCategoriesTable() {

        Reflections ref = new Reflections("Category.subCategories");
        Set<Class<?>> subTypes = ref.get(Scanners.SubTypes.of(Category.class).asClass());
        for (Class<?> type : subTypes) {
            try {
                Category temp = (Category) type.getDeclaredMethod("getInstance").invoke(new Object());
                categorySet.add(temp);
                String st = "INSERT INTO Categories (name) VALUES (?)";
                PreparedStatement statement = connection.prepareStatement(st);
                statement.setString(1, temp.getName());
                statement.executeUpdate();
                statement.close();
                System.out.println("Category " + temp.getName() + " added");
            } catch (InvocationTargetException | IllegalAccessException |SQLException | NoSuchMethodException exception) {
                exception.printStackTrace();
            }
        }
    }

    private void setProductsTable() {
        RandomStorePopulator populator = new RandomStorePopulator();
        for (Category category: categorySet){
            for (int i=0; i<5; i++){
                try {
                    String st = "INSERT INTO Products (name, rate, price, category) VALUES (?, ?, ?, ?)";
                    PreparedStatement statement = connection.prepareStatement(st);
                    statement.setString(1, populator.getName());
                    statement.setInt(2, populator.getRate());
                    statement.setDouble(3, populator.getPrice());
                    statement.setString(4, category.getName());
                    statement.executeUpdate();
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
