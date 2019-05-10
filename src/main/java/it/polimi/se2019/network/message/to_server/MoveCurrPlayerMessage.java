package it.polimi.se2019.network.message.to_server;

import it.polimi.se2019.controller.Controller;
import it.polimi.se2019.model.board.Platform;
import it.polimi.se2019.utils.Deserializer;

import javax.naming.ldap.Control;

/**
 * Message sent by the current player to move his character to another platform
 */
public class MoveCurrPlayerMessage extends ToServerMessage {
    public MoveCurrPlayerMessage(Object payload) {
        super(payload);
    }

    @Override
    /**
     * Control if the action "move" is valid and move the character of the current player
     */
    public void performAction() {
        Platform dest = Deserializer.getPlatform((String) payload);
        Controller actor = Controller.getInstance();
        if (actor.getUserFromPlayer(actor.getPlayerManager().getCurrentPlayer()) == getSender() &&
                actor.isValidAction("move")) {
            actor.getPlayerManager().move(dest);
            actor.removeValidAction("move");
        }
    }
}
