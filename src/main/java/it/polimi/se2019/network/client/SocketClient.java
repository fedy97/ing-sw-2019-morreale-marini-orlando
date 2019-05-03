package it.polimi.se2019.network.client;

import it.polimi.se2019.network.message.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Socket implementation of remote client
 */
public class SocketClient implements Client {
    private boolean connected;
    private Socket socket;
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;

    public SocketClient() {
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
        } catch (IOException e) {

        }

        connected = true;
    }

    /**
     * @param msg to be sent
     * @throws IOException if something went wrong
     */
    public void sendMessage(Message msg) throws IOException {
        objectOutputStream.writeObject(msg);

    }

    public void interpretMessage(Message msg){
       // msg.performAction();
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

    public boolean isConnected(){
        return connected;
    }


    public void callServer(Message msg){
       //TODO
    }
}
