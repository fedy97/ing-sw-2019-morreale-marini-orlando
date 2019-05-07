package it.polimi.se2019;

import it.polimi.se2019.network.client.Client;
import it.polimi.se2019.network.client.RMIClient;
import it.polimi.se2019.network.client.SocketClient;
import it.polimi.se2019.network.message.ShowWeaponsMessage;
import it.polimi.se2019.network.server.RMIServer;
import it.polimi.se2019.network.server.SocketServer;
import it.polimi.se2019.utils.HandyFunctions;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class Lobby {

    private static final String LOCALHOST = "127.0.0.1";
    private static List<String> users = new ArrayList<>();

    public static void main(String[] args) {
        RMIServer rmiServer = new RMIServer(1099);
        SocketServer socketServer = new SocketServer(1100);
        rmiServer.start();
        socketServer.start();
    }

    public static void addUser(String user){
        users.add(user);
    }


}
