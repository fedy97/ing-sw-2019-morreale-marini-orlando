package it.polimi.se2019.network.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIServerInterface extends Remote {

    void testMethod() throws RemoteException;

    void callClient() throws RemoteException;

    void registerClient(String ip, int port) throws RemoteException;
}
