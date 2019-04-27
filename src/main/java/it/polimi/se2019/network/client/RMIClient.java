package it.polimi.se2019.network.client;

import it.polimi.se2019.view.RemoteView;
import it.polimi.se2019.view.View;

import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * RMI implementation of remote client
 * WARNING!!!! Implementation to be discussed
 *
 * @author Gabriel Raul Marini
 */
public class RMIClient implements Client {
    private View stub;
    private RemoteView skeleton;
    private boolean connected;
    private int port;
    private Registry registry;

    public RMIClient(RemoteView skeleton, int port) {
        this.skeleton = skeleton;
        this.port = port;

        try {
            registry = LocateRegistry.createRegistry(port);
        } catch (RemoteException e) {
            //TODO
        }

        connected = false;
    }

    /**
     * Register the skeleton of the client in order to interact with the remote view
     *
     * @param host address of the server
     */
    public void connect(String host) {
        try {
            Registry remoteRegistry = LocateRegistry.getRegistry(host, 1099);
            stub = (View) remoteRegistry.lookup("View");
        } catch (RemoteException e) {
            //TODO
        } catch (NotBoundException e) {
            //TODO
        }
    }

    /**
     * Export the remote reference of RemoteView used to call his methods
     *
     * @throws RemoteException
     * @throws AlreadyBoundException if the skeleton was already registered
     */
    private void exportRemoteObject() throws RemoteException, AlreadyBoundException {
        skeleton = (RemoteView) UnicastRemoteObject.exportObject(skeleton, port);
        registry.bind("RemoteView", skeleton);
    }

    public void disconnect() {
        stub = null;
        try {
            registry.unbind("RemoteView");
        } catch (RemoteException e) {
            //TODO
        } catch (NotBoundException e) {
            //TODO
        }
        connected = false;
    }

    /**
     * @return if the client is connected to the server stub
     */
    public boolean isConnected() {
        return connected;
    }
}
