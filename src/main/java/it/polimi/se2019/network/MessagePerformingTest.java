package it.polimi.se2019.network;

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

public class MessagePerformingTest {
    private static final String LOCALHOST = "127.0.0.1";

    public static void main(String[] args) {
        List<String> weapons = new ArrayList<String>();
        weapons.add("Weapon 1");
        weapons.add("Weapon 2");

        SocketServer socketServer = new SocketServer(1100);
        RMIServer rmiServer = new RMIServer(1099);
        socketServer.start();
        rmiServer.start();

        Client client2 = new RMIClient(null, 1345, "User2");
        Client client4 = new SocketClient(null, "User4");
        Client client3 = new SocketClient(null, "User3");
        Client client1 = new RMIClient(null, 1234, "User1");

        try {
            client3.connect(LOCALHOST, 1100);
            client1.connect(LOCALHOST, 1099);
            client2.connect(LOCALHOST, 1099);
            client4.connect(LOCALHOST, 1100);
        } catch (Exception e) {
            HandyFunctions.LOGGER.log(Level.WARNING, e.toString());
        }
    }
}
