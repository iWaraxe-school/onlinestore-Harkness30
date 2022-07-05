package Http.HttpHandlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import utils.DBConnection.DBQuery;

import java.io.IOException;
import java.io.OutputStream;

public class RootHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String response = "<h1>Welcome to the 'Unnamed' store</h1>";
        response+="<b><a href='http://localhost:8081/get?data=categories'>Categories</a></b><br>";
        response+="<b><a href='http://localhost:8081/get?data=products'>List of Products</a></b><br>";
        response+="<b><a href='http://localhost:8081/get?data=sort'>Sorted list of Products</a></b><br>";
        response+="<b><a href='http://localhost:8081/get?data=top'>Top 5 most expensive Products</a></b><br>";
        response+="<b><a href='http://localhost:8081/get?data=cart'>Your Cart</a></b><br>";
        response+="<details>";
        response+="<summary>Make order</summary>";
        response+=new DBQuery().getOrderableProductsFromDB();
        response+="</details>";
        exchange.sendResponseHeaders(200, response.length());
        OutputStream output = exchange.getResponseBody();
        output.write(response.getBytes());
        output.close();
    }
}
