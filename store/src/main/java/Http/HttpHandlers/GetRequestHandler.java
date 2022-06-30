package Http.HttpHandlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.apache.commons.lang3.StringEscapeUtils;
import utils.DBConnection.DBQuery;

import java.io.IOException;
import java.io.OutputStream;

public class GetRequestHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) {
        String params;
        if ("GET".equals(exchange.getRequestMethod())) {
            params = parseGetRequest(exchange);
            handleGetRequest(exchange, params);
        } else if ("POST".equals(exchange.getRequestMethod())) {
            params = parsePostRequest(exchange);
        }

    }

    private String parseGetRequest(HttpExchange exchange) {
        return exchange
                .getRequestURI()
                .toString()
                .split("\\?")[1]
                .split("=")[1];
    }

    private String parsePostRequest(HttpExchange exchange) {
        String o = null;
        return o;
    }

    private void handleGetRequest(HttpExchange exchange, String params) {
        OutputStream output = exchange.getResponseBody();
        String result = null;
        DBQuery q = new DBQuery();
        switch (params.toLowerCase()) {
            case "categories" -> result = q.getCategoriesFromDB();
            case "cart" -> result = q.getPurchasedProductsFromDB();
            case "products" -> result = q.getAllProductsFromDB();
        }
        String htmlResponse = StringEscapeUtils.escapeHtml4(result);

        try {
            exchange.sendResponseHeaders(200, htmlResponse.length());
            output.write(htmlResponse.getBytes());
            output.flush();
            output.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
