package it.polimi.se2019.network.client;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIClientInterface extends Remote {

    void testMethod() throws RemoteException;

    void callServer() throws RemoteException;
}
