package it.polimi.se2019.network.server;

import it.polimi.se2019.network.client.RMIClientInterface;
import it.polimi.se2019.network.message.Message;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * A generic class used by the View to connect to remote clients
 *
 * @author Gabriel Raul Marini
 * @author Simone Orlando
 */
public interface RMIServerInterface extends Remote {

    void callClient(Message msg) throws RemoteException;
    void registerClient(RMIClientInterface client, String user) throws RemoteException;
}
