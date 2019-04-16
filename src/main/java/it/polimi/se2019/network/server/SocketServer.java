package it.polimi.se2019.network.server;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * Socket implementation of the server
 *
 * @author Gabriel Raul Marini
 */
public class SocketServer extends Server {
    private Socket connection;
    private ServerSocket serverSocket;

    public SocketServer(int port) {
        super(port);
    }

    public void start() {
        //TODO
    }

    /**
     * Close the streams and the sockets
     */
    public void close() {
        //TODO
    }
}
