package it.polimi.se2019;

import it.polimi.se2019.network.server.RMIServer;
import it.polimi.se2019.network.server.Server;
import it.polimi.se2019.utils.HandyFunctions;
import it.polimi.se2019.view.server.VirtualView;

import java.rmi.RemoteException;
import java.util.logging.Level;

public class MainServer {

    public static void main(String[] arg) {
        int port = 9999;
        Server server = new RMIServer(port);
        try {
            server.start();
            VirtualView test = new VirtualView();
            test.setServer(server);
            test.start();
        }
        catch (RemoteException e) {
            HandyFunctions.LOGGER.log(Level.WARNING, e.toString());
        }
    }
}
