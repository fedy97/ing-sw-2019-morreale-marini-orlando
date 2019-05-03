package it.polimi.se2019.network.server;

import it.polimi.se2019.Server;
import it.polimi.se2019.utils.HandyFunctions;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;

/**
 * Socket implementation of the server
 *
 * @author Gabriel Raul Marini
 */
public class SocketServer implements ServerInterface {
    private Socket connection;
    private ServerSocket serverSocket;
    private int port;

    public SocketServer(int port) {
        this.port = port;
    }

    @Override
    public void start() {
        try {
            serverSocket = new ServerSocket(port);
            HandyFunctions.LOGGER.log(Level.INFO, "Opening socket connections...");

            new Thread() {
                public void run() {
                    while (true) {
                        Socket socket;
                        try {
                            socket = serverSocket.accept(); //accetta client
                            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                            output.flush();
                            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
                            String username = (String) input.readObject();
                            Server.addClient(socket, input, output, username);
                        } catch (IOException e) {
                        } catch (ClassNotFoundException e) {
                        }

                    }
                }
            }.start();
        } catch (IOException e) {
            //nothing
        }
    }
}

