package it.polimi.se2019.network.server;

import it.polimi.se2019.network.client.RMIClientInterface;
import it.polimi.se2019.network.message.Message;
import it.polimi.se2019.utils.HandyFunctions;
import it.polimi.se2019.Server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;


/**
 * RMI implementation of Server
 *
 * @author Gabriel Raul Marini
 */
public class RMIServer implements ServerInterface, RMIServerInterface {
    private RMIServerInterface stub;
    private int port;

    public RMIServer(int port) {
        this.port = port;
    }

    /**
     * Start the RMI server
     */
    public void start() {
        try {
            exportRemoteObject();
        } catch (Exception e) {
            HandyFunctions.LOGGER.log(Level.WARNING, e.toString());
        }
        HandyFunctions.LOGGER.log(Level.INFO, "opening rmi connections...");
    }

    /**
     * Export the remote reference of this in order to allow remote calling procedures
     */
    private void exportRemoteObject() {
        try {
            stub = (RMIServerInterface) UnicastRemoteObject.exportObject(this, port);

            // Bind the remote object's stub in the registry
            Registry registry = LocateRegistry.createRegistry(port);
            registry.bind("FakeView", stub);
        } catch (Exception e) {
            HandyFunctions.LOGGER.log(Level.WARNING, e.toString());
        }
    }

    @Override
    public void registerClient(RMIClientInterface client, String user) throws RemoteException {
        Server.addClient(client, user);
    }

    @Override
    public void callClient(Message msg) throws RemoteException {

    }
}
