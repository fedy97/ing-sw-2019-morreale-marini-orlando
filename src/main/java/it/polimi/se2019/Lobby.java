package it.polimi.se2019;

import it.polimi.se2019.controller.Controller;
import it.polimi.se2019.model.Game;
import it.polimi.se2019.network.server.RMIServer;
import it.polimi.se2019.network.server.SocketServer;
import it.polimi.se2019.utils.CustomLogger;
import it.polimi.se2019.utils.HandyFunctions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.ObjectInputStream;
import java.util.Scanner;
import java.util.logging.Level;

public class Lobby {
    private static RMIServer rmiServer = new RMIServer(HandyFunctions.parserSettings.getRmiServerPort());
    private static SocketServer socketServer = new SocketServer(HandyFunctions.parserSettings.getSocketServerPort());
    private static Controller controller = Controller.getInstance();

    public static void main(String[] args) {
        HandyFunctions.printConsole("press 1 to load server from file, something else otherwise: ");
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        if (s.equals("1")) {
            loadController();
            rmiServer.start();
            socketServer.start();
        } else {
            rmiServer.start();
            socketServer.start();
        }
     //   controller.startWaitingLobbyPing();

    }

    private static void loadController() {

        try (FileInputStream fi = new FileInputStream(new File("server.txt"));
             ObjectInputStream oi = new ObjectInputStream(fi)) {

            Controller instance = (Controller) oi.readObject();
            if (!instance.isGameIsActive()) {
                HandyFunctions.LOGGER.log(Level.INFO, "the game has ended! restarting...");
                return;
            }
            Game gameInstance = (Game) oi.readObject();
            Controller.setInstance(instance);
            Controller.getInstance().setServerReloaded(true);
            Game.setInstance(gameInstance);
            controller = Controller.getInstance();
            CustomLogger.logInfo(Lobby.class.getName(), "Server was reloaded successfully");
            Controller.getInstance().startPinging();
        } catch (FileNotFoundException ex3) {
            CustomLogger.logInfo(Lobby.class.getName(), "no loadable file found, starting new game...");
        } catch (Exception ex) {
            CustomLogger.logException(Lobby.class.getName(), ex);
        }

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
