package it.polimi.se2019;

import it.polimi.se2019.model.player.Player;
import it.polimi.se2019.network.client.Client;
import it.polimi.se2019.network.client.RMIClient;
import it.polimi.se2019.network.message.SimpleMessage;
import it.polimi.se2019.network.server.RMIServer;
import it.polimi.se2019.utils.HandyFunctions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

public class Lobby {

    private static List<String> users;

    private static Map<String, Player> userPlayer;

    public static void main(String[] args) {
        users = new ArrayList<>();
        RMIServer rmiServer = new RMIServer(null, 1099);
        rmiServer.start();

        Client client1 = new RMIClient(null, 1234, "User1");
        Client client2 = new RMIClient(null, 1345, "User2");

        try {
            client1.connect("127.0.0.1", 1099);
            client2.connect("127.0.0.1", 1099);
            client1.callServer(new SimpleMessage(null));
            client2.callServer(new SimpleMessage(null));
        }catch(Exception e){
            HandyFunctions.LOGGER.log(Level.WARNING, e.toString());
        }

        if(rmiServer.isConnected("User1"))
            HandyFunctions.LOGGER.log(Level.INFO, "OK user 1");
        if(!rmiServer.isConnected("User3"))
            HandyFunctions.LOGGER.log(Level.INFO, "KO user 3");

    }


}
