package it.polimi.se2019.network.server;

import it.polimi.se2019.network.Message;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIServerInterface extends Remote {

    void testMethod() throws RemoteException;

    void callClient(Message msg) throws RemoteException;

    void registerClient(String ip, int port) throws RemoteException;
}
