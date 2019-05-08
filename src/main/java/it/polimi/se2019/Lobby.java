package it.polimi.se2019;

import it.polimi.se2019.controller.Controller;
import it.polimi.se2019.model.Game;
import it.polimi.se2019.network.client.RMIClient;
import it.polimi.se2019.network.server.RMIServer;
import it.polimi.se2019.network.server.SocketServer;
import it.polimi.se2019.utils.HandyFunctions;
import it.polimi.se2019.view.client.cli.CLI;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class Lobby {
    private static final String LOCALHOST = "127.0.0.1";
    private static List<String> users = new ArrayList<>();
    private static RMIServer rmiServer = new RMIServer(1099);
    private static SocketServer socketServer = new SocketServer(1100);
    private static Controller controller = new Controller(new Game());
    public static void main(String[] args) {

        rmiServer.start();
        socketServer.start();

        /*String stdUser = "user1";

        RMIClient c1 = new RMIClient(new CLI(), 1234, stdUser);

        try {
            c1.connect(LOCALHOST, 1099);
        } catch (Exception e) {
            HandyFunctions.LOGGER.log(Level.WARNING, e.toString());
        }*/

    }

    public static List<String> getUsers() {
        return users;
    }

    /**
     * @return the controller, useful I need it in VirtualView without passing it,
     * we could alternatively use singleton pattern for this
     */
    public static Controller getController() {
        return controller;
    }

    public static void addUser(String user) {
        users.add(user);
    }

    public static RMIServer getRmiServer() {
        return rmiServer;
    }

    public static SocketServer getSocketServer() {
        return socketServer;
    }
}
