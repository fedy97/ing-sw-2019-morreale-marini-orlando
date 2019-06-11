package it.polimi.se2019;

import it.polimi.se2019.controller.Controller;
import it.polimi.se2019.model.enumeration.Character;
import it.polimi.se2019.network.server.RMIServer;
import it.polimi.se2019.network.server.SocketServer;
import it.polimi.se2019.utils.HandyFunctions;

public class Lobby {
    private static final String LOCALHOST = "127.0.0.1";
    /** Turn controller should be enough
    private static List<String> users = new ArrayList<>();*/
    private static RMIServer rmiServer = new RMIServer(HandyFunctions.parserSettings.getRmiServerPort());
    private static SocketServer socketServer = new SocketServer(HandyFunctions.parserSettings.getSocketServerPort());
    private static Controller controller = Controller.getInstance();

    public static void main(String[] args) {
        rmiServer.start();
        socketServer.start();
    }

    public static void addUser(String user) {
        controller.getTurnController().addUser(user);
    }

    public static RMIServer getRmiServer() {
        return rmiServer;
    }

    public static SocketServer getSocketServer() {
        return socketServer;
    }
}
