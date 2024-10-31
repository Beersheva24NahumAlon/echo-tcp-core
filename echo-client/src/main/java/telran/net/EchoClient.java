package telran.net;

import java.net.*;
import java.io.*;

public class EchoClient {
    private Socket socket;
    private BufferedReader reader;
    private PrintStream writer;

    public EchoClient(String host, int port) {
        try {
            socket = new Socket(host, port);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintStream(socket.getOutputStream());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String sendAndReceive(String string) {
        try {
            writer.println(string);
            return reader.readLine(); 
        } catch (Exception e) {
            throw new RuntimeException(e);
        }  
    }

    public void close() {
        try {
            writer.close();
            reader.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
