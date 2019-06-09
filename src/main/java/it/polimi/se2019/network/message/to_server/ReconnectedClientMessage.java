package it.polimi.se2019.network.message.to_server;

import it.polimi.se2019.controller.Controller;
import it.polimi.se2019.network.message.to_client.ShowReconnectedGameBoard;


public class ReconnectedClientMessage extends ToServerMessage {
    public ReconnectedClientMessage(Object payload) {
        super(payload);
    }

    @Override
    /**
     */
    public void performAction() {
        Controller actor = Controller.getInstance();
        String user = (String) payload;
        //actor.broadcastMessage(user + " reconnected!");
        actor.callView(new ShowReconnectedGameBoard(actor.getConfigMap(), actor.getGame().getLightVersion(), actor.findCharactersInGame(), actor.getGame().getPlayer(user).getCharacter().name()), user);
    }
}
