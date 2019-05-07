package it.polimi.se2019.view.client.cli;

import it.polimi.se2019.network.client.RMIClient;
import it.polimi.se2019.network.client.SocketClient;
import it.polimi.se2019.utils.HandyFunctions;
import it.polimi.se2019.view.State;
import it.polimi.se2019.view.client.RemoteView;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Observable;
import java.util.logging.Level;

/**
 * @author Simone Orlando
 */
public class CLI extends RemoteView {

    private CliReader reader;
    private int connectionChosen;
    private int port;

    public CLI() {
        reader = new CliReader(5);
    }

    @Override
    public void start() {
        CliPrinter.welcomeMessage();
        setCommunicationType();
        setUserName();
        startConnection();
        waitGameStart();
    }

    @Override
    public void startGame() {
        HandyFunctions.printConsole("The game is started!\n");
    }

    @Override
    public void setCommunicationType() {
        HandyFunctions.printConsole("\n\n");
        CliPrinter.boxConnectionMessage();
        connectionChosen = reader.getInt();
        HandyFunctions.printConsole("Port: ");
        port = reader.getInt();
    }

    @Override
    public void startConnection() {
        try {
            if (connectionChosen == 1) {
                SocketClient client = new SocketClient(this, userName);
                client.connect("127.0.0.1", 1100);
            } else {
                RMIClient client = new RMIClient(this, port, userName);
                client.connect("127.0.0.1", 1099);
            }
        }
        catch (Exception e) {
            HandyFunctions.LOGGER.log(Level.WARNING, e.toString());
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
        //TODO
    }

    @Override
    public void setState(State newState) {
        state = newState;
    }

    @Override
    public void update(Observable obs, Object obj){
        //TODO
    }

    @Override
    public void lightWeapons(List<String> weapons){
        //TODO
    }
}
