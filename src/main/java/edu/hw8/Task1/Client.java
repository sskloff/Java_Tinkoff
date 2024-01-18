package edu.hw8.Task1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

    private Client() {
    }

    private static final String IP = "localhost";
    private static final int PORT = 12345;

    @SuppressWarnings("RegexpSinglelineJava")
    public static void run() {
        try (
            Socket socket = new Socket(IP, PORT);
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in))
        ) {
            while (true) {
                System.out.println("Enter keyword or \"#\" to exit the server");
                String input = consoleReader.readLine();
                writer.println(input);
                String response = reader.readLine();
                if ("#".equals(input)) {
                    break;
                }
                System.out.println("Response: " + response);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
