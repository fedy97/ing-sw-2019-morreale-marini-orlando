package it.polimi.se2019.network;

import it.polimi.se2019.network.client.RMIClient;
import it.polimi.se2019.network.server.RMIServer;


public class Test {
    public static void main(String args[]) {
        RMIServer server = new RMIServer(null, 1234);
        server.start();

        RMIClient client = new RMIClient(null, 1543);
        client.connect("127.0.0.1", 1234);

        client.callServer();
        server.callClient();
    }
}
