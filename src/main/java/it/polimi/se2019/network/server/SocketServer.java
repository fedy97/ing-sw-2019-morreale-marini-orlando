package it.polimi.se2019.network.server;

import it.polimi.se2019.Lobby;
import it.polimi.se2019.network.message.Message;
import it.polimi.se2019.utils.HandyFunctions;
import it.polimi.se2019.view.VirtualView;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;

/**
 * Socket implementation of the server
 *
 * @author Gabriel Raul Marini
 */
public class SocketServer implements Server {
    private ArrayList<Socket> connection = new ArrayList<>();
    private ServerSocket serverSocket;
    private VirtualView actor;
    private boolean isAvailable;
    private int port;

    public SocketServer(VirtualView actor, int port) {
        this.port = port;
        this.actor = actor;
        isAvailable = false;
    }

    @Override
    public void start() {
        try {
            serverSocket = new ServerSocket(port);
            HandyFunctions.LOGGER.log(Level.INFO, "Socket Server is ready");
            new Thread() {
                public void run() {
                    while (true) {
                        try {
                            Socket socket = serverSocket.accept();
                            connection.add(socket); //accetta client
                            HandyFunctions.printConsole("added socket client successfully");
                            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                            output.flush();
                            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());

                        } catch (IOException e) {
                            HandyFunctions.LOGGER.log(Level.SEVERE, "error accepting client in socketserver");
                        }
                    }
                }
            }.start();
        } catch (IOException ex) {
            HandyFunctions.LOGGER.log(Level.SEVERE, "error starting socket server");
        }
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
    public void registerClient(String host, int port, String username) {
        //TODO
    }

    public void interpretMessage(Message msg, String user) {
        msg.performAction(actor);
    }

    @Override
    public boolean isConnected(String user) {
        return false;
    }

    @Override
    public boolean isAvailable() {
        return isAvailable;
    }
}
