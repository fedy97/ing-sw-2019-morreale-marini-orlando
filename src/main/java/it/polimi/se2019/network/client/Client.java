package it.polimi.se2019.network.client;

import it.polimi.se2019.network.Message;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interface of the remote client to be implemented with Socket or RMI
 *
 * @author Gabriel Raul Marini
 */
public interface Client extends Remote {

    void connect(String host, int port)throws RemoteException;
    void disconnect() throws IOException, RemoteException;
    void callServer(Message msg) throws RemoteException;
    void testMethod() throws RemoteException;
    boolean isConnected() throws RemoteException;
}
