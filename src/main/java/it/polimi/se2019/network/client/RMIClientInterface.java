package it.polimi.se2019.network.client;

import it.polimi.se2019.network.Message;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIClientInterface extends Remote {

    void testMethod() throws RemoteException;

    void callServer(Message msg) throws RemoteException;
}
