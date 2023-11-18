package com.gymbuddy.backend_project;

import com.mysql.cj.callback.MysqlCallbackHandler;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class EndpointSali {
    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8081),0);
        server.createContext("/endpointSali",new MyHandler());
        server.setExecutor(null);
        server.start();
        System.out.println("Server started on port 8081");
    }

    static class MyHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException{
            String response = "Acesta este endpoint-ul pentru sali";
            exchange.sendResponseHeaders(200,response.length());
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }

    }
}
