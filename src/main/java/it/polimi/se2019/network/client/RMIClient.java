package it.polimi.se2019.network.client;

import it.polimi.se2019.network.message.to_client.ToClientMessage;
import it.polimi.se2019.network.message.to_server.ToServerMessage;
import it.polimi.se2019.network.server.Server;
import it.polimi.se2019.utils.HandyFunctions;
import it.polimi.se2019.view.client.RemoteView;

import java.net.InetAddress;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;

/**
 * RMI implementation of remote client
 *
 * @author Gabriel Raul Marini
 */
public class RMIClient implements Client, Observer {
    private Server stub;
    private RemoteView actor;
    private boolean connected;
    private int port;
    private Registry registry;
    private String user;

    public RMIClient(RemoteView actor, int port, String user) {
        this.actor = actor;
        this.port = port;
        this.user = user;

        try {
            registry = LocateRegistry.createRegistry(port);
        } catch (RemoteException e) {
            HandyFunctions.LOGGER.log(Level.WARNING, e.toString());
        }

        connected = false;
    }

    /**
     * Connect to the remote object on the server and register itself as skeleton calling
     * remote method registerClient() of the RMIServer
     *
     * @param host address of the server
     * @param port to bind in order to retrieve the stub
     */
    public void connect(String host, int port) {
        try {
            Registry remoteRegistry = LocateRegistry.getRegistry(host, port);
            stub = (Server) remoteRegistry.lookup("FakeView");
        } catch (Exception e) {
            HandyFunctions.LOGGER.log(Level.WARNING, e.toString());
        }

        exportRemoteObject();

        try {
            stub.registerClient(InetAddress.getLocalHost().getHostAddress(), this.port, user);
        } catch (Exception e) {
            HandyFunctions.LOGGER.log(Level.WARNING, e.toString());
        }

        connected = true;
        HandyFunctions.LOGGER.log(Level.FINE, "Client is connected!");
    }

    /**
     * Export the remote reference of RemoteView used to call his methods
     */
    private void exportRemoteObject() {
        try {
            Client skeleton = (Client) UnicastRemoteObject.exportObject(this, port);
            registry.bind("RemoteView", skeleton);
        } catch (Exception e) {
            HandyFunctions.LOGGER.log(Level.WARNING, e.toString());
        }
    }

    public void disconnect() {
        stub = null;
        try {
            registry.unbind("RemoteView");
        } catch (Exception e) {
            HandyFunctions.LOGGER.log(Level.WARNING, e.toString());
        }
        connected = false;
    }

    /**
     * @return if the client is connected to the server stub
     */
    public boolean isConnected() {
        return connected;
    }

    @Override
    public void interpretMessage(ToClientMessage msg) {
        try {
            msg.performAction(actor);
        } catch (Exception e) {
            e.printStackTrace();
            HandyFunctions.LOGGER.log(Level.WARNING, e.toString());
        }
    }

    /**
     * @param msg containing the set of action to be performed on the corresponding
     *            server side virtual view
     */
    private void callServer(ToServerMessage msg) {
        try {
            stub.interpretMessage(msg);
        } catch (Exception e) {
            e.printStackTrace();
            HandyFunctions.LOGGER.log(Level.INFO, e.toString());
        }
    }

    /**
     * it recives the updates from GUI or CLI
     * @param o GUI/CLI
     * @param arg the message sent from gui or cli
     */
    @Override
    public void update(Observable o, Object arg) {
        callServer((ToServerMessage) arg);
    }
}
