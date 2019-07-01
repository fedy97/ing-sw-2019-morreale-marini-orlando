package it.polimi.se2019.network.server;

import it.polimi.se2019.network.message.toclient.ToClientMessage;
import it.polimi.se2019.network.message.toserver.ToServerMessage;

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

    /**
     * @param msg  to be interpreted in order to perform action on the actor
     * @throws RemoteException
     */
    void interpretMessage(ToServerMessage msg) throws RemoteException;

    void sendToClient(ToClientMessage msg, String username) throws RemoteException;

    void registerClient(String host, int port, String user) throws RemoteException;

    boolean isConnected(String user) throws RemoteException;

    boolean isUsed() throws RemoteException;

    void setUsed(boolean isused) throws RemoteException;

}
