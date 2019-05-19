package it.polimi.se2019.network.client;

import it.polimi.se2019.network.message.to_client.ToClientMessage;

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
    void disconnect() throws IOException;
    void interpretMessage(ToClientMessage msg) throws RemoteException;
    boolean isConnected() throws RemoteException;
}
