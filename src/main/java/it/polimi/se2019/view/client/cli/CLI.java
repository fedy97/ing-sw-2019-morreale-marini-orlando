package it.polimi.se2019.view.client.cli;

import it.polimi.se2019.Action;
import it.polimi.se2019.network.client.Client;
import it.polimi.se2019.network.client.RMIClient;
import it.polimi.se2019.network.client.SocketClient;
import it.polimi.se2019.network.message.Message;
import it.polimi.se2019.network.message.SimpleMessage;
import it.polimi.se2019.utils.HandyFunctions;
import it.polimi.se2019.view.MyTurnState;
import it.polimi.se2019.view.State;
import it.polimi.se2019.view.View;
import it.polimi.se2019.view.client.RemoteView;

import java.rmi.RemoteException;

/**
 * @author Simone Orlando
 */
public class CLI extends RemoteView {

    private Client client;
    private CliReader reader;
    private int connectionChosen;

    public CLI() {
        reader = new CliReader();
    }

    @Override
    public void start() {
        CliPrinter.welcomeMessage();
        setComunicationType();
        setUserName();
        startConnection();
        waitGameStart();
        menageTurn();
    }

    @Override
    protected void setComunicationType() {
        HandyFunctions.printConsole("\n\nChoose a connection type:\n<1> Socket\n<2> Rmi\n: ");
        connectionChosen = reader.getInt();
    }

    @Override
    protected void startConnection() {
        if (connectionChosen == 1) {
            client = new SocketClient(this, userName);
        }
        else {
            client = new RMIClient(this, 8888, userName);
        }
        try {
            client.connect("127.0.0.1", 9999);
        }
        catch (RemoteException e) {

        }
    }

    @Override
    protected void setUserName() {
        HandyFunctions.printConsole("Username: ");
        userName = reader.getString();
    }

    @Override
    protected void waitGameStart() {
        HandyFunctions.printConsole("Waiting for other players...\n");
    }

    @Override
    protected void menageTurn() {

    }

    @Override
    protected void getCurrentPlayer() {
        return;
    }

    @Override
    protected void setState(State newState) {
        state = newState;
    }

}
