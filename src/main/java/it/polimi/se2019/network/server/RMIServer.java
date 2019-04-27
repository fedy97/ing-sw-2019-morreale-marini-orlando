package it.polimi.se2019.network.server;

import it.polimi.se2019.view.RemoteView;
import it.polimi.se2019.view.View;

import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * RMI implementation of Server
 * <p>
 * WARNING!!!! Implementation to be discussed
 *
 * @author Gabriel Raul Marini
 */
public class RMIServer extends Server implements Remote {
    private View stub;
    private RemoteView skeleton;

    public RMIServer(View stub, int port) {
        this.port = port;
        this.stub = stub;
        connected = false;
    }

    /**
     * Start the RMI server using the View as stub
     */
    public void start() {
        try {
            exportRemoteObject();
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (AlreadyBoundException e) {
            e.printStackTrace();
        }
        connected = true;
    }

    /**
     * Export the remote reference of the object used to call remote methods
     *
     * @throws RemoteException
     * @throws AlreadyBoundException if the stub was already registered
     */
    private void exportRemoteObject() throws RemoteException, AlreadyBoundException {
        stub = (View) UnicastRemoteObject.exportObject(stub, port);

        // Bind the remote object's stub in the registry
        Registry registry = LocateRegistry.getRegistry();
        registry.bind("View", stub);
    }

    /**
     * Unbind the remote object View and stop the communication
     */
    public void stop() {
        try {
            Registry registry = LocateRegistry.getRegistry();
            registry.unbind("View");
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }
        connected = false;
        skeleton = null;
    }

    /**
     * Register the skeleton of the client in order to interact with the remote view
     *
     * @param host address of the client
     * @param port the port on which the client registry has been exported
     */
    public void registerClient(String host, int port) {
        try {
            Registry registry = LocateRegistry.getRegistry(host, port);
            skeleton = (RemoteView) registry.lookup("RemoteView");
        } catch (RemoteException e) {
            //TODO
        } catch (NotBoundException e) {
            //TODO
        }
    }

}
