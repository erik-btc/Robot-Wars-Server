package com.btcag.bootcamp;

import com.btcag.bootcamp.DatabaseEntities.Connection;
import com.btcag.bootcamp.DatabaseEntities.PostService;
import com.btcag.bootcamp.DatabaseEntities.Robots;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.Session;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

@SpringBootApplication
public class Main {
    public static void main(String[] args) throws IOException {
        SpringApplication.run(Main.class, args);
    }

    private static void postRobot(ObjectMapper objectMapper, Robots robots2Post) throws IOException {
        String json = objectMapper.writeValueAsString(robots2Post);
        // Send the POST request with the Robot object as JSON
        URL url = new URL("http://localhost:8080/database/robots/add");  // Change to your server's URL
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        connection.setRequestProperty("Content-Type", "application/json");

        // Write the JSON data to the request body
        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = json.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        // Get the response from the server
        int statusCode = connection.getResponseCode();
        System.out.println("Response Code: " + statusCode);

    }


    private static void deleteRobot(String id) throws IOException {
        Session session = Connection.getSession();
        session.beginTransaction();
        Robots robot = session.createQuery( "select r from Robots r where r.id = '"+id+"'", Robots.class).getSingleResult();

        System.out.println(robot);
        session.createQuery("delete from Robots r where r.id = :id").setParameter("id", id).executeUpdate();

        session.getTransaction().commit();

    }
}