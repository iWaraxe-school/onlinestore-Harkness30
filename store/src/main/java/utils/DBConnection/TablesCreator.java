package utils.DBConnection;

import java.sql.SQLException;
import java.sql.Statement;

public class TablesCreator {
    private DBConnector connector = DBConnector.getInstance();
    public void initTables(){
        createCategoriesTable();
        createProductsTable();
        createPurchasedProductsTable();
    }

    private void createCategoriesTable() {
        try {
            Statement statement = connector.getConnection().createStatement();
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS \"Categories\" \n" +
                    "(\"name\" TEXT NOT NULL UNIQUE);");
            statement.close();
        } catch (SQLException SQLex) {
            SQLex.printStackTrace();
        }
    }
    private void createProductsTable() {
        try {
            Statement statement = connector.getConnection().createStatement();
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS \"Products\" (\n" +
                    "\"productId\" INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                    "\"name\" TEXT NOT NULL,\n" +
                    "\"category\" TEXT NOT NULL,\n" +
                    "\"rate\" INTEGER,\n" +
                    "\"price\" REAL,\n" +
                    "FOREIGN KEY(category) REFERENCES \"Categories\");");
            statement.close();
        } catch (SQLException SQLex) {
            SQLex.printStackTrace();
        }
    }
    private void createPurchasedProductsTable() {
        try {
            Statement statement = connector.getConnection().createStatement();
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS \"PurchasedProducts\" (\n" +
                    "\"productId\" INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                    "\"name\" TEXT NOT NULL,\n" +
                    "\"category\" TEXT NOT NULL,\n" +
                    "\"rate\" INTEGER,\n" +
                    "\"price\" REAL,\n" +
                    "FOREIGN KEY(category) REFERENCES \"Categories\");");
            statement.close();
        } catch (SQLException SQLex) {
            SQLex.printStackTrace();
        }
    }

    public void dropTables(){
        try {
            Statement statement = connector.getConnection().createStatement();
            statement.executeUpdate("DROP TABLE Categories;");
            statement.executeUpdate("DROP TABLE Products;");
            statement.executeUpdate("DROP TABLE PurchasedProducts;");
            statement.close();
        } catch (SQLException SQLex) {
            SQLex.printStackTrace();
        }
    }
}
