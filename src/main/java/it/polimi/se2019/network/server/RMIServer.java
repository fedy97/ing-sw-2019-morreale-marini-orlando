package it.polimi.se2019.network.server;

import it.polimi.se2019.Lobby;
import it.polimi.se2019.controller.Controller;
import it.polimi.se2019.model.Game;
import it.polimi.se2019.network.client.Client;
import it.polimi.se2019.network.message.to_client.ToClientMessage;
import it.polimi.se2019.network.message.to_server.ReconnectedClientMessage;
import it.polimi.se2019.network.message.to_server.ResponseToPingMessage;
import it.polimi.se2019.network.message.to_server.ToServerMessage;
import it.polimi.se2019.utils.HandyFunctions;
import it.polimi.se2019.view.server.VirtualView;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;


/**
 * RMI implementation of Server
 *
 * @author Gabriel Raul Marini
 */
public class RMIServer implements Server {
    private Map<String, Client> skeletons;
    private Map<String, VirtualView> clientActor;
    private boolean available;
    private int port;

    public RMIServer(int port) {
        this.port = port;
        skeletons = new HashMap<>();
        clientActor = new HashMap<>();
    }

    @Override
    /**
     * Start the RMI server
     */
    public void start() {
        try {
            exportRemoteObject();
        } catch (Exception e) {
            HandyFunctions.LOGGER.log(Level.WARNING, e.toString());
        }
        available = true;
        HandyFunctions.LOGGER.log(Level.INFO, "RMI Server is ready");
    }

    /**
     * Export the remote reference of this in order to allow remote calling procedures
     */
    private void exportRemoteObject() {
        try {
            Server stub = (Server) UnicastRemoteObject.exportObject(this, port);

            // Bind the remote object's stub in the registry
            Registry registry = LocateRegistry.createRegistry(port);
            registry.bind("FakeView", stub);
        } catch (Exception e) {
            HandyFunctions.LOGGER.log(Level.WARNING, e.toString());
        }
    }

    @Override
    /**
     * Unbind the remote object View and stop the communication
     */
    public void stop() {
        try {
            Registry registry = LocateRegistry.getRegistry(port);
            registry.unbind("FakeView");
        } catch (Exception e) {
            HandyFunctions.LOGGER.log(Level.WARNING, e.toString());
        }

        available = false;
        skeletons = null;
    }

    @Override
    /**
     * Register the skeleton of the client in order to interact with the remote view
     *
     * @param host address of the client
     * @param port the port on which the client registry has been exported
     * @param username associated to the client
     */
    public void registerClient(String host, int port, String username) {
        try {
            Registry registry = LocateRegistry.getRegistry(host, port);
            boolean newConnection = true;
            VirtualView virtualView;
            if (!Controller.getInstance().getUserView().containsKey(username)) {
                virtualView = new VirtualView(this, username);
                clientActor.put(username, virtualView);
                Lobby.addUser(username);
            } else if (clientActor.containsKey(username)){
                virtualView = clientActor.get(username);
                Game.getInstance().addObserver(virtualView);
                Game.getInstance().getPlayer(username).setConnected(true);
                newConnection = false;
            }
            else {
                virtualView = Controller.getInstance().getUserView().get(username);
                Game.getInstance().addObserver(virtualView);
                Game.getInstance().getPlayer(username).setConnected(true);
                Lobby.getSocketServer().getActors().remove(username);
                Lobby.getSocketServer().getConnections().remove(username);
                clientActor.put(username, virtualView);
                newConnection = false;
            }
            skeletons.put(username, (Client) registry.lookup("RemoteView"));
            virtualView.viewSetChanged();
            if (newConnection) {
                virtualView.notifyObservers("new client connected");
                HandyFunctions.checkForAtLeast2Players(virtualView);
            } else {
                virtualView.notifyObservers(new ReconnectedClientMessage(username));
            }
            HandyFunctions.LOGGER.log(Level.INFO, username + " connected to the RMI server!");
        } catch (Exception e) {
            HandyFunctions.LOGGER.log(Level.WARNING, e.toString());
        }
    }

    @Override
    /**
     * Method used to perform action on server side virtual view
     * @param msg info packet containing commands to perform on virtual view
     * @param user requesting server decoding service
     */
    public void interpretMessage(ToServerMessage msg) {
        clientActor.get(msg.getSender()).viewSetChanged();
        clientActor.get(msg.getSender()).notifyObservers(msg);
    }

    @Override
    /**
     * Server side invocation of client
     * @param msg to be sent with action requesting
     * @param username of client side destination
     */
    public void sendToClient(ToClientMessage msg, String username) {
        try {
            skeletons.get(username).interpretMessage(msg);
        } catch (Exception e) {
            //HandyFunctions.LOGGER.log(Level.WARNING, e.toString());
        }
    }

    @Override
    /**
     * @return if the server is ready to receive connections
     */
    public boolean isAvailable() {
        return available;
    }

    @Override
    /**
     * @param user client to be checked
     * @return if the user is actually connected to the server
     */
    public boolean isConnected(String user) {
        return skeletons.containsKey(user);
    }

    public Map<String, VirtualView> getClientActor() {
        return clientActor;
    }

    public Map<String, Client> getSkeletons() {
        return skeletons;
    }
}
