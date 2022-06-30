package Http.HttpHandlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

public class RootHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String response = "<h1>Server start success if you see this message</h1>" + "<h1>Port: "
        + exchange.getLocalAddress().getPort() + "</h1>";
        response+="<a href='http://localhost:8081/go?get=categories'>Categories</a>\n";
        response+="<a href='http://localhost:8081/go?get=products'>List of Products</a>\n";
        response+="<a href='http://localhost:8081/go?get=cart'>Your Cart</a>\n";
        exchange.sendResponseHeaders(200, response.length());
        OutputStream output = exchange.getResponseBody();
        output.write(response.getBytes());
        output.close();
    }
}
