package utils.DBConnection;

import Product.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DBQuery {

    Connection connection = DBConnector.getInstance().getConnection();

    public List<Product> getAllProductsFromDB(){
        List<Product> prods = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM Products");
            System.out.println(result);
            while (result.next()) {
                Product temp = new ProductBuilder()
                        .setProductName(result.getString("name"))
                        .setProductRate(result.getInt("rate"))
                        .setProductPrice(result.getDouble("price"))
                        .buildProduct();
                prods.add(temp);
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return prods;
    }
}
