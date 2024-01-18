package edu.hw8.Task1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private final Socket clientSocket;

    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try (
            BufferedReader reader = new BufferedReader(
                new InputStreamReader(clientSocket.getInputStream())
            );
            PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true)
        ) {
            String keyword;
            while ((keyword = reader.readLine()) != null) {
                if ("#".equals(keyword)) {
                    break;
                }
                writer.println(Dictionary.KEYWORDS.getOrDefault(keyword, "Нет совпадений"));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
