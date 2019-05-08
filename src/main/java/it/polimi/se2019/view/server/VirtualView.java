package it.polimi.se2019.view.server;


import it.polimi.se2019.model.Game;
import it.polimi.se2019.model.player.Player;
import it.polimi.se2019.network.server.Server;
import it.polimi.se2019.view.State;
import it.polimi.se2019.view.View;

import java.util.List;
import java.util.Observable;
import java.util.Observer;


public class VirtualView extends View {
    private Game game;
    private Server virtualServer;
    private String user;

    /*
        rmi or socket server that contains all clients, now we need to extract
        the specific connection of the specific client
     */

    /**
     * @param virtualServer either RMIServer or SocketServer, we need to be more specific
     * @param user
     */
    public VirtualView(Server virtualServer, String user) {
        this.virtualServer = virtualServer;
        this.user = user;
        //TODO metterere il controller ad osservare (this)
    }

    public String getUser() {
        return user;
    }

    @Override
    public void start() {

    }

    public void startGame() {

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
    public void lightPlayers(List<Player> targets) {
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
