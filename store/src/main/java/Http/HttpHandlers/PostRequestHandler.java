package Http.HttpHandlers;

import Operations.Ordering.OrderCreator;
import Product.Product;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.apache.commons.lang3.StringEscapeUtils;
import utils.DBConnection.DBQuery;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class PostRequestHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if ("POST".equals(exchange.getRequestMethod())) {
            handlePostRequest(exchange);
        }
    }

    private void handlePostRequest(HttpExchange exchange) throws IOException {
        InputStreamReader isr = new InputStreamReader(exchange.getRequestBody(), StandardCharsets.UTF_8);
        BufferedReader reader = new BufferedReader(isr);
        String request = reader.readLine();
        if (request.contains("=")) {
            request = request.split("=")[1].replace('+', ' ');
        }
        Product orderedProduct = new DBQuery().getProductFromDB(request);
        new Thread(new OrderCreator(orderedProduct)).start();

        String response = "Product's '" + request + "' purchasing is in progress. Soon you could see it in your Cart.";
        String htmlResponse = StringEscapeUtils.escapeHtml4(response);
        exchange.sendResponseHeaders(200, htmlResponse.length());
        OutputStream os = exchange.getResponseBody();
        os.write(htmlResponse.getBytes());
        os.close();
    }
}
