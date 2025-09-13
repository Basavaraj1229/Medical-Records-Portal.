import java.io.*;
import java.net.*;
import java.nio.file.*;
import java.util.concurrent.*;
import com.sun.net.httpserver.*;
import com.sun.net.httpserver.HttpServer;

public class SimpleWebServer {
    private final int port;
    private final String webRoot;
    private HttpServer server;

    public SimpleWebServer(int port, String webRoot) {
        this.port = port;
        this.webRoot = webRoot;
    }

    public void start() throws IOException {
        server = HttpServer.create(new InetSocketAddress(port), 0);
        
        // Serve static files
        server.createContext("/", new StaticFileHandler());
        
        // Serve JSP files (simplified - will show source)
        server.createContext("/jsp", new JSPHandler());
        
        server.setExecutor(Executors.newFixedThreadPool(10));
        server.start();
        
        System.out.println("========================================");
        System.out.println("  Doctor-Patient Portal Demo Server");
        System.out.println("========================================");
        System.out.println("Server started on port " + port);
        System.out.println();
        System.out.println("Available URLs:");
        System.out.println("• Home Page: http://localhost:" + port + "/index.html");
        System.out.println("• JSP Demo: http://localhost:" + port + "/jsp/index.jsp");
        System.out.println("• Login Page: http://localhost:" + port + "/jsp/user_login.jsp");
        System.out.println("• Admin Login: http://localhost:" + port + "/jsp/admin_login.jsp");
        System.out.println();
        System.out.println("Note: This is a demo server. JSP files will show structure only.");
        System.out.println("For full functionality, use Apache Tomcat.");
        System.out.println("========================================");
    }

    class StaticFileHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String path = exchange.getRequestURI().getPath();
            if (path.equals("/")) {
                path = "/index.html";
            }
            
            File file = new File(webRoot + path);
            if (file.exists() && file.isFile()) {
                String contentType = getContentType(path);
                exchange.getResponseHeaders().set("Content-Type", contentType);
                exchange.sendResponseHeaders(200, file.length());
                
                try (FileInputStream fis = new FileInputStream(file);
                     OutputStream os = exchange.getResponseBody()) {
                    byte[] buffer = new byte[1024];
                    int count;
                    while ((count = fis.read(buffer)) != -1) {
                        os.write(buffer, 0, count);
                    }
                }
            } else {
                String response = "404 - File Not Found: " + path;
                exchange.sendResponseHeaders(404, response.length());
                exchange.getResponseBody().write(response.getBytes());
                exchange.getResponseBody().close();
            }
        }
    }
    
    class JSPHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String path = exchange.getRequestURI().getPath().replace("/jsp", "");
            File file = new File(webRoot + "/src/main/webapp" + path);
            
            if (file.exists() && file.isFile()) {
                exchange.getResponseHeaders().set("Content-Type", "text/html");
                String content = Files.readString(file.toPath());
                
                // Simple JSP simulation - show structure
                String demoContent = createDemoPage(path, content);
                
                exchange.sendResponseHeaders(200, demoContent.length());
                exchange.getResponseBody().write(demoContent.getBytes());
                exchange.getResponseBody().close();
            } else {
                String response = "404 - JSP File Not Found: " + path;
                exchange.sendResponseHeaders(404, response.length());
                exchange.getResponseBody().write(response.getBytes());
                exchange.getResponseBody().close();
            }
        }
    }
    
    private String createDemoPage(String jspPath, String originalContent) {
        return "<!DOCTYPE html>" +
               "<html><head><title>Doctor-Patient Portal - Demo</title>" +
               "<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css' rel='stylesheet'>" +
               "</head><body>" +
               "<div class='container mt-4'>" +
               "<div class='alert alert-info'>" +
               "<h4><i class='fas fa-info-circle'></i> Demo Mode</h4>" +
               "<p>This is a demonstration of the JSP file: <code>" + jspPath + "</code></p>" +
               "<p>For full functionality, deploy to Apache Tomcat with database connection.</p>" +
               "</div>" +
               "<div class='card'>" +
               "<div class='card-header'><h5>JSP Source Structure</h5></div>" +
               "<div class='card-body'>" +
               "<pre style='max-height: 400px; overflow-y: auto;'>" + 
               escapeHtml(originalContent.substring(0, Math.min(2000, originalContent.length()))) + 
               (originalContent.length() > 2000 ? "\\n... (truncated)" : "") +
               "</pre>" +
               "</div></div>" +
               "<div class='mt-3'>" +
               "<a href='/' class='btn btn-primary'>Back to Home</a>" +
               "<a href='/jsp/user_login.jsp' class='btn btn-secondary'>User Login</a>" +
               "<a href='/jsp/admin_login.jsp' class='btn btn-secondary'>Admin Login</a>" +
               "<a href='/jsp/doctor_login.jsp' class='btn btn-secondary'>Doctor Login</a>" +
               "</div>" +
               "</div></body></html>";
    }
    
    private String escapeHtml(String text) {
        return text.replace("&", "&amp;")
                  .replace("<", "&lt;")
                  .replace(">", "&gt;")
                  .replace("\"", "&quot;")
                  .replace("'", "&#39;");
    }
    
    private String getContentType(String path) {
        if (path.endsWith(".html") || path.endsWith(".htm")) return "text/html";
        if (path.endsWith(".css")) return "text/css";
        if (path.endsWith(".js")) return "application/javascript";
        if (path.endsWith(".png")) return "image/png";
        if (path.endsWith(".jpg") || path.endsWith(".jpeg")) return "image/jpeg";
        if (path.endsWith(".gif")) return "image/gif";
        return "text/plain";
    }

    public static void main(String[] args) {
        try {
            SimpleWebServer server = new SimpleWebServer(8081, ".");
            server.start();
            
            System.out.println("Press Enter to stop the server...");
            System.in.read();
            
            server.server.stop(0);
            System.out.println("Server stopped.");
        } catch (IOException e) {
            System.err.println("Error starting server: " + e.getMessage());
        }
    }
}
