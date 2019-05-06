package it.polimi.se2019.view.server;


import it.polimi.se2019.network.server.Server;
import it.polimi.se2019.utils.HandyFunctions;
import it.polimi.se2019.view.State;
import it.polimi.se2019.view.View;

import java.rmi.RemoteException;


/**
 * @author Simone Orlando
 */
public class VirtualView extends View implements Runnable{

    private Server server;

    public VirtualView() {

    }

    public void setServer(Server server) {
        this.server = server;
    }

    @Override
    public void run() {
        start();
    }

    @Override
    public void start() {
        startConnection();
        waitGameStart();
        startGame();
        menageTurn();
    }

    public void startGame() {
        HandyFunctions.printConsole("The game is started!\n");
    }

    @Override
    public void setComunicationType() {

    }

    @Override
    public void startConnection() {
    }

    @Override
    public void setUserName() {

    }

    @Override
    public void waitGameStart() {

    }

    @Override
    public void menageTurn() {

    }


    public void getCurrentPlayer() {

    }

    @Override
    public void setState(State newState) {

    }

}
