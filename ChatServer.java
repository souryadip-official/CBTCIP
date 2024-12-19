import java.io.*;
import java.net.*;

class ChatServer {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(12345)) {
            System.out.println("Server started. Waiting for clients...");

            Socket socket = serverSocket.accept();
            System.out.println("Client connected.");

            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

            Thread readThread = new Thread(() -> {
                try {
                    String message;
                    while ((message = input.readLine()) != null) {
                        System.out.println("Client: " + message);
                    }
                } catch (IOException e) {
                    System.out.println("Connection closed by the client.");
                }
            });

            readThread.start();

            try (BufferedReader consoleInput = new BufferedReader(new InputStreamReader(System.in))) {
                while (true) {
                    String message = consoleInput.readLine();
                    if (message.equalsIgnoreCase("exit")) {
                        System.out.println("Server shutting down...");
                        break;
                    }
                    output.println(message);
                }
            }
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}