package it.polimi.se2019.view.server;


import it.polimi.se2019.Lobby;
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

    /**
     * @param virtualServer either RMIServer or SocketServer
     * @param user
     */
    public VirtualView(Server virtualServer, String user) {
        this.virtualServer = virtualServer;
        this.user = user;
        this.game = Lobby.getController().getGame();
        //controller observs (this)
        this.addObserver(Lobby.getController());
        //(this) observs model
        game.addObserver(this);
    }

    public String getUser() {
        return user;
    }

    /**
     * @param game who is being observed by (this)
     * @param message sent by the model(game)
     */
    @Override
    public void update(Observable game, Object message) {
        //TODO
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

    /**
     * @param targets players to show as targets
     */
    public void lightPlayers(List<Player> targets) {
        //TODO
    }

}
