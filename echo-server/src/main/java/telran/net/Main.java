package telran.net;

import java.net.*;
import java.io.*;

public class Main {

    private static final int PORT = 5000;
    
    @SuppressWarnings("resource")
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(PORT);
        while (true) {
            Socket socket = serverSocket.accept();
            runSession(socket);
        }
    }
                
    private static void runSession(Socket socket) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            PrintStream writer = new PrintStream(socket.getOutputStream());
            String line = "";
            while ((line = reader.readLine()) != null) {
                writer.printf("Echo server on %s, port: %d sends back %s\n", socket.getLocalAddress().getHostAddress(), socket.getLocalPort(), line);
            }    
        } catch (Exception e) {
            System.err.println("Client closed normaly");
        }
    }
}