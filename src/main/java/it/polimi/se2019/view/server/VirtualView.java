package it.polimi.se2019.view.server;


import it.polimi.se2019.model.Game;
import it.polimi.se2019.model.player.Player;
import it.polimi.se2019.network.server.Server;
import it.polimi.se2019.utils.HandyFunctions;
import it.polimi.se2019.view.State;
import it.polimi.se2019.view.View;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Observable;


/**
 * @author Simone Orlando
 */
public class VirtualView extends View implements Runnable {
    private Game game;
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
    public void setCommunicationType() {
        //TODO
    }

    @Override
    public void startConnection() {
        //TODO
    }

    @Override
    public void setUserName() {
        //TODO
    }

    @Override
    public void waitGameStart() {
        //TODO
    }

    @Override
    public void menageTurn() {
        //TODO
    }


    public void getCurrentPlayer() {
        //TODO
    }

    @Override
    public void setState(State newState) {
        //TODO
    }

    @Override
    public void update(Observable game, Object arg) {
        //TODO
    }

    /**
     * @param targets players to show as targets
     */
    public void lightPlayers(List<Player> targets){
        //TODO
    }

    /**
     * @param game used to extract information about the game state and send it through
     *             the network
     */
    public void setGame(Game game) {
        this.game = game;
    }

}
