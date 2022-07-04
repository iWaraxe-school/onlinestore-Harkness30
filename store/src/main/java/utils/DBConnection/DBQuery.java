package utils.DBConnection;

import Product.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DBQuery {

    Connection connection = DBConnector.getInstance().getConnection();

    public void printAllProductsFromDB() {
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM Products");
            while (result.next()) {
                System.out.printf("""                                               
                                 Product name: "%s" \s
                                \t\t rate: %d \s
                                \t\t price: %.2f
                                """,
                        result.getString("name"),
                        result.getInt("rate"),
                        result.getDouble("price"));
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public String getAllProductsFromDB() {
        List<Product> prods = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM Products");
            while (result.next()) {
            prods.add(new ProductBuilder()
                    .setProductName(result.getString("name"))
                    .setProductRate(result.getInt("rate"))
                    .setProductPrice(result.getDouble("price"))
                    .buildProduct());
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return prods.toString()
                .replace(',', ' ')
                .replace('[', ' ')
                .replace(']', ' ');
    }
    public String getOrderableProductsFromDB() {
        String orderButton = "";
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM Products");
            while (result.next()) {
                 orderButton += String.format("<form action = 'http://localhost:8081/post' method = 'post'>" +
                        "        <input type='submit' name='%s' value='%s' />\n" +
                        "    </form>", result.getString("name"), result.getString("name")) + "\n";
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  orderButton;
    }
    public String getCategoriesFromDB() {
        String resultSTR = "";
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM Categories");
            while (result.next()) {
                resultSTR+= "Category name: " + result.getString("name")+"\n";
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSTR;
    }
    public String getPurchasedProductsFromDB() {
        String resultSTR = "";
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM PurchasedProducts");
            while (result.next()) {
                resultSTR+= "Product name: " + result.getString("name")+"\n"+
                        "rate: " + result.getInt("rate")+"\n"+
                        "price: " + result.getDouble("price")+"\n";
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSTR;
    }
    /**
     * StringBuffer collects all order options from config.XML to sql query
     * For example, with default config:
     * String order = "SELECT * FROM Products ORDER BY  name asc, rate asc, price desc;"
     */
    public String getSortedListFromDB(Map<String, String> map) {
        List<Product> sorted = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            StringBuffer buffer = new StringBuffer("SELECT * FROM Products ORDER BY ;");
            for (Map.Entry<String, String> entry : map.entrySet()) {
                String order = " " + entry.getKey() + " " + entry.getValue() + ",";
                buffer.insert(buffer.indexOf(";"), order);
            }
            String order = buffer.deleteCharAt(buffer.length() - 2).toString();
            ResultSet result = statement.executeQuery(order);
            while (result.next()) {
                sorted.add(new ProductBuilder()
                        .setProductName(result.getString("name"))
                        .setProductRate(result.getInt("rate"))
                        .setProductPrice(result.getDouble("price"))
                        .buildProduct()
                );
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sorted.toString()
                .replace(',', ' ')
                .replace('[', ' ')
                .replace(']', ' ');
    }

    public String getTopFiveFromDB() {
        String request = "";
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(
                    "SELECT * FROM Products\n" + "ORDER BY\n price DESC;");
            for (int i = 0; i < 5; i++) {
                request += String.format("""
                                 Product name: %s \s
                                \t\t rate: %d \s
                                \t\t price: %.2f
                                """,
                        result.getString("name"),
                        result.getInt("rate"),
                        result.getDouble("price"));
                result.next();
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return request;
    }

    public Product getProductFromDB(String name) {
        Product prod = null;
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM Products WHERE name = '" + name + "';");
            prod = new ProductBuilder()
                    .setProductName(result.getString("name"))
                    .setProductRate(result.getInt("rate"))
                    .setProductPrice(result.getDouble("price"))
                    .buildProduct();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return prod;
    }

    public void addPurchasedProduct(String name) {
        try {
            String st = "INSERT INTO PurchasedProducts SELECT * FROM Products WHERE name = '" + name + "';";
            Statement statement = connection.createStatement();
            statement.executeUpdate(st);
            statement.close();

        } catch (SQLException SQLex) {
            SQLex.printStackTrace();
        }
    }

    public void cleanUpPurchasedProductsTable() {
        try {
            String st = "DELETE FROM PurchasedProducts;";
            Statement statement = connection.createStatement();
            statement.executeUpdate(st);
            statement.close();

        } catch (SQLException SQLex) {
            SQLex.printStackTrace();
        }
    }

    public void printCartFromDB() {
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM PurchasedProducts;");
            while (result.next()) {
                System.out.printf("""                                               
                                 Product name: "%s" \s
                                \t\t rate: %d \s
                                \t\t price: %.2f
                                """, result.getString("name"),
                        result.getInt("rate"),
                        result.getDouble("price"));
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
