package it.polimi.se2019.network.client;

/**
 * @author Simone Orlando
 */
public abstract class Client {

    private String ip;
    private int port;
    private boolean connected;

    public Client() {
        connected = false;
    }

    public Client(String ip, int port) {
        this.ip = ip;
        this.port = port;
        connected = false;
    }

    public void setIp(String ip) {
        //TODO
    }

    public void setPort(int port) {
        //TODO
    }

    public abstract void connect();

    public abstract void send();

    public abstract void receive();

    public abstract void disconnect();
}
