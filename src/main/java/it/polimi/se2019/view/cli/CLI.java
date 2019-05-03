package it.polimi.se2019.view.cli;

import it.polimi.se2019.network.client.Client;
import it.polimi.se2019.network.client.RMIClient;
import it.polimi.se2019.network.client.SocketClient;
import it.polimi.se2019.network.message.Message;
import it.polimi.se2019.view.RemoteView;
import it.polimi.se2019.view.State;
import it.polimi.se2019.view.View;

import java.rmi.RemoteException;

/**
 * @author Simone Orlando
 */
public class CLI extends RemoteView {

    private State cliState;
    private String userName;
    private Client client;
    private CliReader reader;

    public CLI(int comunicationType) {

    }

    public void start() {

    }

    protected void setComunicationType() {

    }

    protected void startConnection() {

    }

    protected void setUserName() {

    }

    protected void waitGameStart() {

    }

    protected void menageTurn() {

    }

    protected void getCurrentPlayer() {
        return;
    }

    protected void setState(State newState) {

    }

}
