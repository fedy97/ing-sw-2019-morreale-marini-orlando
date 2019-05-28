package it.polimi.se2019.network.message.to_server;

import it.polimi.se2019.controller.Controller;
import it.polimi.se2019.controller.ControllerState;
import it.polimi.se2019.model.board.Platform;
import it.polimi.se2019.model.card.weapons.WeaponCard;
import it.polimi.se2019.network.message.to_client.AskToDiscardMessage;
import it.polimi.se2019.utils.Deserializer;
import it.polimi.se2019.utils.HandyFunctions;

import javax.naming.ldap.Control;
import java.util.logging.Level;

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

        if (actor.getState() == ControllerState.PROCESSING_ACTION_1)
            actor.getPlayerManager().move(dest);
        else if (actor.getState() == ControllerState.PROCESSING_ACTION_2) {
            actor.getPlayerManager().move(dest);
            if (dest.isGenerationSpot()) {
                try {
                    if (actor.getPlayerManager().getCurrentPlayer().getWeaponCards().size() == 3) {
                        actor.callView(new AskToDiscardMessage(null), sender);
                        WeaponCard card = actor.getChosenWeapons().take();

                        if (card.getName().equals("null")) {
                            actor.setState(ControllerState.IDLE);
                            return;
                        }
                        else
                            actor.getPlayerManager().getCurrentPlayer().removeWeaponCard(card);
                    }

                    actor.askFor(actor.getValidator().getGrabableWeapons(), "weapons");
                    actor.getPlayerManager().buyWeapon(actor.getChosenWeapons().take());
                } catch (Exception e) {
                    HandyFunctions.LOGGER.log(Level.WARNING, e.getMessage());
                    e.printStackTrace();
                }
            } else {
                actor.getPlayerManager().grabAmmoCard();
            }
        }
        actor.setState(ControllerState.IDLE);
    }
}

