import java.io.*;
import java.net.*;
import java.util.Scanner;

class ChatClient {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 12345)) {
            System.out.println("Connected to the server.");

            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

            Thread readThread = new Thread(() -> {
                try {
                    String message;
                    while ((message = input.readLine()) != null) {
                        System.out.println("Server: " + message);
                    }
                } catch (IOException e) {
                    System.out.println("Connection to the server was lost.");
                }
            });

            readThread.start();

            try (Scanner scanner = new Scanner(System.in)) {
                while (true) {
                    String message = scanner.nextLine();
                    if (message.equalsIgnoreCase("exit")) {
                        System.out.println("Exiting chat...");
                        break;
                    }
                    output.println(message);
                }
            }
        } catch (IOException e) {
            System.err.println("Unable to connect to the server. Please check if the server is running and the port is correct.");
        }
    }
}