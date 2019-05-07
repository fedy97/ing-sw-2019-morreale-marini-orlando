package it.polimi.se2019;

import it.polimi.se2019.network.client.Client;
import it.polimi.se2019.network.client.RMIClient;
import it.polimi.se2019.network.client.SocketClient;
import it.polimi.se2019.network.message.SimpleMessage;
import it.polimi.se2019.network.server.RMIServer;
import it.polimi.se2019.network.server.SocketServer;
import it.polimi.se2019.utils.HandyFunctions;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class Lobby {

    private static final String LOCALHOST = "127.0.0.1";
    private static List<String> users;

    public static void main(String[] args) {
        users = new ArrayList<>();
        RMIServer rmiServer = new RMIServer(1099);
        SocketServer socketServer = new SocketServer(1100);
        rmiServer.start();
        socketServer.start();
        /*
        Client client3 = new SocketClient(null, "User3");
        Client client1 = new RMIClient(null, 1234, "User1");
        Client client2 = new RMIClient(null, 1345, "User2");
        Client client4 = new SocketClient(null, "User4");

        try {
            client3.connect(LOCALHOST, 1100);
            client1.connect(LOCALHOST, 1099);
            client2.connect(LOCALHOST, 1099);
            client4.connect(LOCALHOST, 1100);
            client1.callServer(new SimpleMessage(null));
            client2.callServer(new SimpleMessage(null));
            client3.callServer(new SimpleMessage(null));
            client4.callServer(new SimpleMessage(null));
            socketServer.sendToClient(new SimpleMessage(null), "User3");
            socketServer.sendToClient(new SimpleMessage(null), "User4");
            rmiServer.sendToClient(new SimpleMessage(null), "User1");
        } catch (Exception e) {
            HandyFunctions.LOGGER.log(Level.WARNING, e.toString());
        }*/


    }

    public static void addUser(String user){
        users.add(user);
    }


}
