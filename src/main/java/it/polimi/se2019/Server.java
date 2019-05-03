package it.polimi.se2019;

import it.polimi.se2019.network.client.RMIClient;
import it.polimi.se2019.network.client.RMIClientInterface;
import it.polimi.se2019.network.client.SocketClient;
import it.polimi.se2019.network.server.RMIServer;
import it.polimi.se2019.network.server.SocketServer;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Server {

    public static void main(String[] args) {
        new SocketServer(1100).start();
        new RMIServer(1099).start();

        new RMIClient(1999,"127.0.0.1").connect();
        new SocketClient("127.0.0.1").connect();

    }

    public static void addClient(RMIClientInterface client, String user) {}

    public static void addClient(Socket socket, ObjectInputStream input, ObjectOutputStream output, String user) {}
}
