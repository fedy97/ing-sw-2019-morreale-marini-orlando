package it.polimi.se2019.view.server;


import it.polimi.se2019.Lobby;
import it.polimi.se2019.controller.Controller;
import it.polimi.se2019.model.Game;
import it.polimi.se2019.model.player.Player;
import it.polimi.se2019.network.message.toclient.ToClientMessage;
import it.polimi.se2019.view.View;

import java.util.List;
import java.util.Observable;


public class VirtualView extends View {
    private Game game;
    private String user;

    /**
     * @param virtualServer either RMIServer or SocketServer
     * @param user          .
     */
    public VirtualView(String user) {
        this.user = user;
        this.game = Game.getInstance();
        //virtual view(this) observs model(Game)
        game.addObserver(this);
        //controller observs (this)
        this.addObserver(Controller.getInstance());
        Controller.getInstance().addVirtualView(this, user);
    }

    public String getUser() {
        return user;
    }

    /**
     * @param game    who is being observed by (this)
     * @param message sent by the model(game)
     */
    @Override
    public void update(Observable game, Object message) {
        //callView(new GameChangedMessage(message))
        callView((ToClientMessage) message);
    }

    @Override
    public void start() {
        //

    }

    public void startGame() {
//
    }

    @Override
    public void setCommunicationType() {
        //
    }

    @Override
    public void startConnection() {
        //
    }

    @Override
    public void setUserName() {
        //
    }

    @Override
    public void waitGameStart() {
        //
    }

    public void getCurrentPlayer() {
        //
    }

    /**
     * @param targets players to show as targets
     */
    public void lightPlayers(List<Player> targets) {
        //
    }

    /**
     * Common method across RMI and Socket to send requests to client
     *
     * @param msg to the destination client, the virtual view know his own user
     */
    public void callView(ToClientMessage msg) {
        if (Lobby.getRmiServer().isConnected(user))
            Lobby.getRmiServer().sendToClient(msg, user);
        if (Lobby.getSocketServer().isConnected(user))
            Lobby.getSocketServer().sendToClient(msg, user);
    }

}
