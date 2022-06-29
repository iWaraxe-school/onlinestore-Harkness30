package utils.DBConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {
    private static DBConnector connector;
    private final String DB_URL = "jdbc:sqlite:store/src/main/resources/db/DBConnector";
    private final String DB_Driver = "org.sqlite.JDBC";
    private Connection connection = connect();

    public Connection getConnection() {
        return connection;
    }

    private DBConnector(){}
    public static synchronized DBConnector getInstance() {
        if (connector == null) {
            connector = new DBConnector();
        }
        return connector;
    }

    public  Connection connect() {
        try {
            Class.forName(DB_Driver);
            connection = DriverManager.getConnection(DB_URL);
            System.out.println("База Подключена!");
            return connection;

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Драйвер не найден");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Ошибка Sql");
        }
        return  null;
    }

    public void closeConnection() {
        try {
            new TablesCreator().dropTables();
            connection.close();
            System.out.println("Connection closed");
        } catch (SQLException SQLex) {
            SQLex.printStackTrace();
        } finally {
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
