package it.polimi.se2019.network.server;

import it.polimi.se2019.network.message.Message;
import it.polimi.se2019.utils.HandyFunctions;
import it.polimi.se2019.view.server.VirtualView;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

/**
 * Socket implementation of the server
 *
 * @author Gabriel Raul Marini
 */
public class SocketServer implements Server {
    private Map<String, Socket> connections;
    private ServerSocket serverSocket;
    private Map<String, VirtualView> actors;
    private boolean available;
    private int port;

    public SocketServer(int port) {
        connections = new HashMap<>();
        this.port = port;
        actors = new HashMap<>();
        available = false;
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
                            Thread.sleep(500);

                            String user = new BufferedReader(new InputStreamReader(socket.getInputStream())).readLine();

                            connections.put(user, socket);
                            actors.put(user, new VirtualView());
                            HandyFunctions.LOGGER.log(Level.INFO, user + "connected to the socket server!");

                            new Thread() {

                                public void run() {
                                    try {
                                        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                                        Message msg;

                                        while (true) {
                                            msg = (Message) in.readObject();

                                            if (msg != null) {
                                                interpretMessage(msg, user);
                                            }

                                            Thread.sleep(100);
                                        }
                                    } catch (Exception e) {
                                        HandyFunctions.LOGGER.log(Level.SEVERE, e.toString());
                                    }
                                }
                            }.start();

                        } catch (Exception e) {
                            HandyFunctions.LOGGER.log(Level.SEVERE, e.toString());
                        }
                    }
                }
            }.start();
        } catch (IOException ex) {
            HandyFunctions.LOGGER.log(Level.SEVERE, "Error starting socket server");
        }
    }

    @Override
    /**
     * Close the streams and the sockets
     */
    public void stop() {
        serverSocket = null;
        available = false;
    }

    @Override
    public void sendToClient(Message msg, String user) {
        try {
            ObjectOutputStream outStream = new ObjectOutputStream(connections.get(user).getOutputStream());
            outStream.writeObject(msg);
        } catch (IOException e) {
            HandyFunctions.LOGGER.log(Level.WARNING, e.toString());
        }
    }


    @Override
    public void registerClient(String host, int port, String username) {
        //TODO
    }

    public void interpretMessage(Message msg, String user) {
        msg.performAction(actors.get(user));
        HandyFunctions.LOGGER.log(Level.INFO, user + "required the socket server to interpret a message!");
    }

    @Override
    public boolean isConnected(String user) {
        return connections.containsKey(user);
    }

    @Override
    public boolean isAvailable() {
        return available;
    }

    public VirtualView getVirtualView(String user) {
        return actors.get(user);
    }
}
