package it.polimi.se2019.view.server;

import it.polimi.se2019.Action;
import it.polimi.se2019.network.server.RMIServer;
import it.polimi.se2019.network.server.Server;
import it.polimi.se2019.network.server.SocketServer;
import it.polimi.se2019.utils.HandyFunctions;
import it.polimi.se2019.view.State;
import it.polimi.se2019.view.View;

import java.rmi.RemoteException;

/**
 * @author Simone Orlando
 */
public class VirtualView extends View implements Runnable{

    public VirtualView() {
    }

    @Override
    public void run() {
        start();
    }

    @Override
    public void start() {
        startConnection();
    }

    @Override
    protected void setComunicationType() {

    }

    @Override
    protected void startConnection() {
        //TODO
    }

    @Override
    protected void setUserName() {

    }

    @Override
    protected void waitGameStart() {

    }

    @Override
    protected void menageTurn() {

    }

    @Override
    protected void getCurrentPlayer() {

    }

    @Override
    protected void setState(State newState) {

    }

}
