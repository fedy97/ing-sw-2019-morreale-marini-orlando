package it.polimi.se2019.network.client;

import it.polimi.se2019.network.message.Message;
import it.polimi.se2019.network.server.RMIServerInterface;
import it.polimi.se2019.utils.HandyFunctions;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;

/**
 * RMI implementation of remote client
 *
 * @author Gabriel Raul Marini
 */
public class RMIClient implements Client, RMIClientInterface {
    private RMIServerInterface stub;
    private RMIClientInterface skeleton;
    private int port;
    private String host;
    private Registry registry;

    public RMIClient(int port, String host) {
        this.host = host;
        this.port = port;

        try {
            registry = LocateRegistry.createRegistry(port);
        } catch (RemoteException e) {
            HandyFunctions.LOGGER.log(Level.WARNING, e.toString());
        }
    }

    /**
     * Connect to the remote object on the server and register itself as skeleton calling
     * remote method registerClient() of the RMIServer
     */
    public void connect() {
        try {
            Registry remoteRegistry = LocateRegistry.getRegistry(host, 1099);
            stub = (RMIServerInterface) remoteRegistry.lookup("FakeView");
        } catch (Exception e) {
            HandyFunctions.LOGGER.log(Level.WARNING, e.toString());
        }

        exportRemoteObject();

        try {
            stub.registerClient(skeleton, "ciao");
            HandyFunctions.LOGGER.log(Level.INFO, "RMI Client registered itself to the server!");
        } catch (Exception e) {
            HandyFunctions.LOGGER.log(Level.WARNING, e.toString());
        }
    }

    /**
     * Export the remote reference of RemoteView used to call his methods
     */
    private void exportRemoteObject() {
        try {
            skeleton = (RMIClientInterface) UnicastRemoteObject.exportObject(this, port);
            registry.bind("RemoteView", skeleton);
        } catch (Exception e) {
            HandyFunctions.LOGGER.log(Level.WARNING, e.toString());
        }
    }

    @Override
    public void rmiNotifyClient(Message msg) throws RemoteException {
        if (msg != null){
            HandyFunctions.LOGGER.log(Level.INFO, "ping from server");
        }

    }

    @Override
    public void sendFromClient(Message msg) {
        new Thread(() -> {
            try {
                stub.callClient(msg);

            } catch (RemoteException e) {
                HandyFunctions.LOGGER.log(Level.SEVERE, e.getMessage(), e);
            }
        }).start();
    }
}
