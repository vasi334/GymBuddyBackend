package com.gymbuddy.backend_project;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;

public class MyEndpoint {

    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8081), 0);
        server.createContext("/myEndpoint", new MyHandler());
        server.setExecutor(null);
        server.start();
        System.out.println("Server started on port 8081");
    }

    static class MyHandler implements HttpHandler {
        private Map<String, String> dataStore = new HashMap<>();

        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String requestMethod = exchange.getRequestMethod();

            switch (requestMethod) {
                case "GET":
                    handleGetRequest(exchange);
                    break;
                case "POST":
                    handlePostRequest(exchange);
                    break;
                case "DELETE":
                    handleDeleteRequest(exchange);
                    break;
                default:
                    sendResponse(exchange, 405, "Method Not Allowed");
            }
        }

        private void handleGetRequest(HttpExchange exchange) throws IOException {
            // Handle the GET request (retrieve data, for example)
            String response = "Data: " + dataStore.toString();
            sendResponse(exchange, 200, response);
        }

        private void handlePostRequest(HttpExchange exchange) throws IOException {
            // Handle the POST request (create data)
            InputStream requestBody = exchange.getRequestBody();
            BufferedReader reader = new BufferedReader(new InputStreamReader(requestBody));
            StringBuilder requestBodyBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                requestBodyBuilder.append(line);
            }
            String requestData = requestBodyBuilder.toString();

            // Store the data (you might want to parse and validate the data)
            dataStore.put("key", requestData);

            sendResponse(exchange, 201, "Data created successfully");
        }

        private void handleDeleteRequest(HttpExchange exchange) throws IOException {
            // Handle the DELETE request (delete data)
            dataStore.clear();
            sendResponse(exchange, 200, "Data deleted successfully");
        }

        private void sendResponse(HttpExchange exchange, int statusCode, String response) throws IOException {
            exchange.sendResponseHeaders(statusCode, response.length());
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
}
