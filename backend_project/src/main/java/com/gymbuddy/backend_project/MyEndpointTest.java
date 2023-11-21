package com.gymbuddy.backend_project;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MyEndpointTest {
    public static void main(String[] args) throws IOException {
        // Test GET request
        sendGetRequest("http://localhost:8081/myEndpoint");

        // Test POST request
        sendPostRequest("http://localhost:8081/myEndpoint", "SomeData");

        // Test DELETE request
        sendDeleteRequest("http://localhost:8081/myEndpoint");
    }

    private static void sendGetRequest(String url) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();
        System.out.println("GET Response Code: " + responseCode);

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }

        reader.close();
        connection.disconnect();
    }

    private static void sendPostRequest(String url, String data) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);

        connection.getOutputStream().write(data.getBytes());

        int responseCode = connection.getResponseCode();
        System.out.println("POST Response Code: " + responseCode);

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }

        reader.close();
        connection.disconnect();
    }

    private static void sendDeleteRequest(String url) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod("DELETE");

        int responseCode = connection.getResponseCode();
        System.out.println("DELETE Response Code: " + responseCode);

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }

        reader.close();
        connection.disconnect();
    }
}

