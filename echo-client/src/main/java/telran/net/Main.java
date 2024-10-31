package telran.net;

import telran.view.*;

public class Main {
    public static EchoClient echoClient;

    public static void main(String[] args) {
        Item[] items = {
            Item.of("Start session", Main::startSession),
            Item.of("Finish session", Main::exit, true)
        };
        Menu menu = new Menu("Echo application", items);
        menu.perform(new StandardInputOutput());
    }

    public static void startSession(InputOutput io) {
        String host = io.readString("Enter hostname");
        int port = io.readNumberRange("Enter port", "Wrong port", 3000, 50000).intValue();
        if (echoClient != null) {
            echoClient.close();
        }
        echoClient = new EchoClient(host, port);
        Menu menu = new Menu(
                "Run session", 
                Item.of("Enter string", Main::stringProcessing),
                Item.ofExit()
        );
        menu.perform(io);
    }

    public static void exit(InputOutput io) {
        if (echoClient != null) {
            echoClient.close();
        }  
    }

    public static void stringProcessing(InputOutput io) {
        String string = io.readString("Enter any string");
        String response = echoClient.sendAndReceive(string);
        io.writeString(response);
    }
}