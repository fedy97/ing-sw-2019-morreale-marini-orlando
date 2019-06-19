package it.polimi.se2019;

import it.polimi.se2019.controller.Controller;
import it.polimi.se2019.model.Game;
import it.polimi.se2019.network.server.RMIServer;
import it.polimi.se2019.network.server.SocketServer;
import it.polimi.se2019.utils.CustomLogger;
import it.polimi.se2019.utils.HandyFunctions;

import java.io.*;
import java.util.Scanner;
import java.util.logging.Level;

public class Lobby {
    private static final String LOCALHOST = "127.0.0.1";
    /**
     * Turn controller should be enough
     * private static List<String> users = new ArrayList<>();
     */
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

    }

    private static void loadController() {
        try {
            FileInputStream fi = new FileInputStream(new File("server.txt"));
            ObjectInputStream oi = new ObjectInputStream(fi);
            Controller instance = (Controller) oi.readObject();
            Game gameInstance = (Game) oi.readObject();
            Controller.setInstance(instance);
            Game.setInstance(gameInstance);
            controller = Controller.getInstance();
            oi.close();
            fi.close();
            Controller.getInstance().startPinging();
        } catch (FileNotFoundException ex3) {
            HandyFunctions.LOGGER.log(Level.INFO, "no loadable file found, starting new game...");
        } catch (IOException ex) {
            ex.printStackTrace();
            HandyFunctions.printConsole("failed IOEXXXXXX");
        } catch (ClassNotFoundException ex2) {
            HandyFunctions.printConsole("class not found");
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
