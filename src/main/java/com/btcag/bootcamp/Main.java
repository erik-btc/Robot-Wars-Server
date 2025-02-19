package com.btcag.bootcamp;

import com.btcag.bootcamp.DatabaseEntities.PostService;
import com.btcag.bootcamp.DatabaseEntities.Robot;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Scanner;

@SpringBootApplication
public class Main {
    public static void main(String[] args) throws IOException {
        SpringApplication.run(Main.class, args);

        Scanner scanner = new Scanner(System.in);
        ObjectMapper objectMapper = new ObjectMapper();

        int toDo = 0;
        while (toDo != 5) {
            toDo = InputService.getActionInput(scanner);
            scanner.nextLine();
            switch (toDo) {
                //Roboter erstellen
                case 1:
                    Robot robot2Post = InputService.getStatsForRobot(scanner);
                    postRobot(objectMapper, robot2Post);
                    break;





                case 5:
                    break;
            }
        }
    }

    private static void postRobot(ObjectMapper objectMapper, Robot robot2Post) throws IOException {
        String json = objectMapper.writeValueAsString(robot2Post);
        // Send the POST request with the Robot object as JSON
        URL url = new URL("http://localhost:8080/queue");  // Change to your server's URL
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

        PostService.postRobot(robot2Post);


    }
}