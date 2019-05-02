package it.polimi.se2019.network.server;

import it.polimi.se2019.network.message.Message;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * A generic class used by the View to connect to remote clients
 *
 * @author Gabriel Raul Marini
 * @author Simone Orlando
 */
public interface Server extends Remote {

    /**
     * Initialize the server parameters in order to connect to the client
     */
    void start() throws RemoteException;

    void stop() throws RemoteException;

    /**
     * @return if the server is ready to accept connections
     */
    boolean isAvailable() throws RemoteException;

    void interpretMessage(Message msg) throws RemoteException;

    void callClient(Message msg) throws RemoteException;

    void registerClient(String host, int port) throws RemoteException;
}
