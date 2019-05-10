package it.polimi.se2019.network.message.to_server;

import it.polimi.se2019.controller.Controller;
import it.polimi.se2019.controller.PlayerManager;
import it.polimi.se2019.utils.HandyFunctions;

import java.util.logging.Level;

public class CollectAmmoTileMessage extends ToServerMessage {

    public CollectAmmoTileMessage(Object payload) {
        super(payload);
    }

    @Override
    /**
     * Control if the action "move" is valid and move the character of the current player
     */
    public void performAction(Controller actor){
        PlayerManager manager = actor.getPlayerManager();

        if(actor.isValidAction("grabAmmo")) {
            manager.grabAmmoCard();
            actor.removeValidAction("grabAmmo");
        }
        else
            HandyFunctions.LOGGER.log(Level.WARNING, "This action is denied! The client is trying to perform illegal actions!");
    }
}
