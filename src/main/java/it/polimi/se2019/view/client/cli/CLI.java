package it.polimi.se2019.view.client.cli;

import it.polimi.se2019.network.client.Client;
import it.polimi.se2019.network.client.RMIClient;
import it.polimi.se2019.network.client.SocketClient;
import it.polimi.se2019.utils.HandyFunctions;
import it.polimi.se2019.view.State;
import it.polimi.se2019.view.client.RemoteView;

import java.rmi.RemoteException;

/**
 * @author Simone Orlando
 */
public class CLI extends RemoteView {

    private Client client;
    private CliReader reader;
    private int connectionChosen;
    private int port;

    public CLI() {
        reader = new CliReader(5);
    }

    @Override
    public void start() {
        CliPrinter.welcomeMessage();
        setComunicationType();
        setUserName();
        startConnection();
        waitGameStart();
    }

    @Override
    public void startGame() {
        HandyFunctions.printConsole("The game is started!\n");
    }

    @Override
    public void setComunicationType() {
        HandyFunctions.printConsole("\n\nChoose a connection type:\n<1> Socket\n<2> Rmi\n: ");
        connectionChosen = reader.getInt();
        HandyFunctions.printConsole("Port: ");
        port = reader.getInt();
    }

    @Override
    public void startConnection() {
        if (connectionChosen == 1) {
            client = new SocketClient(this, userName);
        }
        else {
            client = new RMIClient(this, port, userName);
        }
        try {
            client.connect("127.0.0.1", 9999);
        }
        catch (RemoteException e) {

        }
    }

    @Override
    public void setUserName() {
        HandyFunctions.printConsole("Username: ");
        userName = reader.getString();
    }

    @Override
    public void waitGameStart() {
        HandyFunctions.printConsole("Waiting for other players...\n");
    }

    @Override
    public void menageTurn() {

    }

    @Override
    public void setState(State newState) {
        state = newState;
    }

}
