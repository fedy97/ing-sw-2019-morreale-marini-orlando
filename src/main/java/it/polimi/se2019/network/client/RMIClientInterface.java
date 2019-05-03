package it.polimi.se2019.network.client;

import it.polimi.se2019.network.message.Message;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIClientInterface extends Remote {

    void rmiNotifyClient(Message msg) throws RemoteException;
}
