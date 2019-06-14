package it.polimi.se2019.network.client;

import it.polimi.se2019.network.message.to_client.ToClientMessage;
import it.polimi.se2019.network.message.to_server.ToServerMessage;
import it.polimi.se2019.utils.HandyFunctions;
import it.polimi.se2019.view.client.RemoteView;

import java.io.*;
import java.net.Socket;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;

/**
 * Socket implementation of remote client
 */
public class SocketClient implements Client, Observer {
    private boolean connected;
    private RemoteView actor;
    private Socket socket;
    private String user;
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;

    public SocketClient(RemoteView actor, String user) {
        this.actor = actor;
        this.user = user;
        connected = false;
    }

    /**
     * @param ip   address of the server
     * @param port of the server
     */
    public void connect(String ip, int port) {

        try {
            socket = new Socket(ip, port);
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            objectOutputStream.writeObject(user);
            objectOutputStream.flush();

            new Thread(() -> {
                try {
                    while (connected) {
                        ToClientMessage msg;
                        msg = (ToClientMessage) objectInputStream.readObject();
                        if (msg != null)
                            interpretMessage(msg);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    HandyFunctions.LOGGER.log(Level.WARNING, e.toString());
                }
            }).start();

        } catch (IOException e) {
            HandyFunctions.LOGGER.log(Level.SEVERE, "Error connecting to the server socket");
        }

        connected = true;
    }

    /**
     * @param msg containing action to be performed on RemoteView
     */
    public void interpretMessage(ToClientMessage msg) {
        msg.performAction(actor);
    }

    /**
     * Close the socket and disconnect the client
     *
     * @throws IOException if operations went wrong
     */
    public void disconnect() throws IOException {
        objectInputStream.close();
        objectInputStream.close();
        socket.close();
        connected = false;
    }

    /**
     * @return if the client is still connected to the socket
     */
    public boolean isConnected() {
        return connected;
    }

    /**
     * @param msg to be sent to the server socket
     */
    private void callServer(ToServerMessage msg) {
        try {
            objectOutputStream.writeObject(msg);
            objectOutputStream.flush();
            //objectOutputStream.reset();
        } catch (IOException e) {
            HandyFunctions.LOGGER.log(Level.SEVERE, "Error sending message to the server using socket");
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        callServer((ToServerMessage) arg);
    }
}
