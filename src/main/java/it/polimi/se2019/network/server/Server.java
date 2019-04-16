package it.polimi.se2019.network.server;

/**
 * A generic class used by the View to connect to remote clients
 *
 * @author Gabriel Raul Marini
 */
public abstract class Server {
    protected int port;
    protected boolean connected;

    public Server() {
        connected = false;
    }

    public Server(int port) {
        this.port = port;
        connected = false;
    }

    /**
     * Initialize the server parameters in order to connect to the client
     */
    public abstract void start();
}
