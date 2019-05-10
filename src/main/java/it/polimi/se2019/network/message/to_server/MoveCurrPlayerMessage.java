package it.polimi.se2019.network.message.to_server;

import it.polimi.se2019.controller.Controller;
import it.polimi.se2019.model.board.Platform;
import it.polimi.se2019.utils.Deserializer;

/**
 * Message sent by the current player to move his character to another destination
 */
public class MoveCurrPlayerMessage extends ToServerMessage {
    public MoveCurrPlayerMessage(Object payload) {
        super(payload);
    }

    @Override
    /**
     * Control if the action "move" is valid and move the character of the current player
     */
    public void performAction(Controller actor) {
        Platform dest = Deserializer.getPlatform((String) payload);
        if (actor.getUserFromPlayer(actor.getPlayerManager().getCurrentPlayer()) == getSender() &&
                actor.isValidAction("move"))
            actor.getPlayerManager().move(dest);
    }
}
