package it.polimi.se2019.network.client;

import it.polimi.se2019.network.message.Message;
import it.polimi.se2019.utils.HandyFunctions;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.rmi.RemoteException;
import java.util.logging.Level;

/**
 * Socket implementation of remote client
 */
public class SocketClient implements Client {
    private boolean connected;
    private String host;
    private Socket socket;
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;

    public SocketClient(String host) {
        this.host = host;
        connected = false;
    }

    /**
     * @param msg to be sent
     */
    public void sendFromClient(Message msg) {
        try {
            objectOutputStream.writeObject(msg);
            objectOutputStream.flush();
        } catch (IOException ex) {
            HandyFunctions.LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }

    @Override
    public void connect() {
        try {
            socket = new Socket(host, 1100);
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            connected = true;
            //now the client listen to the socketServer
            HandyFunctions.LOGGER.log(Level.INFO, "Socket Client connected itself to the server");
            new Thread(() -> {
                while (true) {
                    try {
                        Message message = (Message) objectInputStream.readObject();
                        if (message != null) {
                            HandyFunctions.LOGGER.log(Level.INFO, "Arrived ping from server");
                        }
                    } catch (IOException e) {
                        HandyFunctions.LOGGER.log(Level.SEVERE, e.getMessage(), e);
                        break;
                    } catch (ClassNotFoundException e) {
                    }
                }
            }).start();
        } catch (IOException e) {
            HandyFunctions.LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }
    }
}
