package it.polimi.se2019.network.server;

import java.rmi.Remote;

/**
 * RMI implementation of Server
 *
 * @author Gabriel Raul Marini
 */
public class RMIServer extends Server implements Remote {
    private Object skeleton;

    public void start() {
        //TODO
    }

    /**
     * Export the remote reference of the object used to call remote methods
     */
    private void exportRemoteObject() {
        //TODO
    }

}
