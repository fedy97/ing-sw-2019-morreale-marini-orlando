package it.polimi.se2019.network.server;

import it.polimi.se2019.network.message.Message;
import it.polimi.se2019.view.VirtualView;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * Socket implementation of the server
 *
 * @author Gabriel Raul Marini
 */
public class SocketServer implements Server {
    private Socket[] connection;
    private ServerSocket serverSocket;
    private VirtualView actor;
    private boolean isAvailable;
    private int port;

    public SocketServer(int port) {
        this.port = port;
        isAvailable = false;
    }

    @Override
    public void start() {
        //TODO
    }

    @Override
    /**
     * Close the streams and the sockets
     */
    public void stop() {
        //TODO
    }

    @Override
    public void sendToClient(Message msg, String user) {
        //TODO
    }

    @Override
    /**
     * @return
     */
    public boolean isAvailable() {
        return isAvailable;
    }

    @Override
    public void registerClient(String host, int port, String username){
        //TODO
    }

    public void interpretMessage(Message msg) {
        msg.performAction(actor);
    }
}
