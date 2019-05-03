package it.polimi.se2019.network.client;

import it.polimi.se2019.network.message.Message;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interface of the remote client to be implemented with Socket or RMI
 *
 * @author Gabriel Raul Marini
 */
public interface Client {

    void connect() throws RemoteException;
    void sendFromClient(Message msg) throws RemoteException;
}
