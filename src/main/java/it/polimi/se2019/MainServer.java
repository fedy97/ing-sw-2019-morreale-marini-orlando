package it.polimi.se2019;

import it.polimi.se2019.network.server.RMIServer;
import it.polimi.se2019.network.server.Server;
import it.polimi.se2019.view.server.VirtualView;

public class MainServer {

    public static void main(String[] arg) {

        VirtualView myView = new VirtualView();
        myView.start();


    }
}
