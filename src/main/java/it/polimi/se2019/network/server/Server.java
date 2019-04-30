package it.polimi.se2019.network.server;

/**
 * A generic class used by the View to connect to remote clients
 *
 * @author Gabriel Raul Marini
 */
public abstract class Server {
    protected int port;
    protected boolean available;

    public Server() {
        available = false;
    }

    public Server(int port) {
        this.port = port;
        available = false;
    }

    /**
     * Initialize the server parameters in order to connect to the client
     */
    public abstract void start();

    /**
     * @return if the server is ready to accept connections
     */
    public boolean isAvailable(){
        return available;
    }
}
