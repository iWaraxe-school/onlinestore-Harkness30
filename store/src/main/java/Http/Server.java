package Http;

import Http.HttpHandlers.GetRequestHandler;
import Http.HttpHandlers.PostRequestHandler;
import Http.HttpHandlers.RootHandler;
import com.sun.net.httpserver.BasicAuthenticator;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Server {
    private static final int PORT = 8081;
    private static Server server;
    private HttpServer httpServer;

    public void startServer() throws IOException {
        httpServer = HttpServer.create(new InetSocketAddress(PORT), 0);
        httpServer.createContext("/", new RootHandler()).setAuthenticator(new BasicAuthenticator("Auth") {
            @Override
            public boolean checkCredentials(String username, String password) {
                return "user".equals(username) && "pass".equals(password);
            }
        });
        httpServer.createContext("/get", new GetRequestHandler()).setAuthenticator(new BasicAuthenticator("Auth") {
            @Override
            public boolean checkCredentials(String username, String password) {
                return "user".equals(username) && "pass".equals(password);
            }
        });
        httpServer.createContext("/post", new PostRequestHandler()).setAuthenticator(new BasicAuthenticator("Auth") {
            @Override
            public boolean checkCredentials(String username, String password) {
                return "user".equals(username) && "pass".equals(password);
            }
        });
        httpServer.setExecutor(null);
        httpServer.start();

    }

    public void stopServer() {
        httpServer.stop(0);
    }

    public static Server getInstance() {
        if (server == null) {
            server = new Server();
        }
        return server;
    }

}
