package it.polimi.se2019.network.message.to_server;

import it.polimi.se2019.controller.Controller;
import it.polimi.se2019.controller.ControllerState;
import it.polimi.se2019.model.Game;
import it.polimi.se2019.model.board.Room;
import it.polimi.se2019.model.card.powerups.PowerUpCard;
import it.polimi.se2019.model.player.Player;
import it.polimi.se2019.network.message.to_client.ShowActionMenuMessage;
import it.polimi.se2019.utils.Deserializer;
import it.polimi.se2019.utils.HandyFunctions;

import java.awt.*;
import java.util.ArrayList;
import java.util.logging.Level;

public class SendInitPowerUpMessage extends ToServerMessage {

    public SendInitPowerUpMessage(Object payload) {
        super(payload);
    }

    @Override
    public void performAction() {
        try {
            int hashGarbage = ((ArrayList<Integer>) payload).get(1);
            Controller c = Controller.getInstance();
            Player curr = c.getGame().getPlayer(sender);
            PowerUpCard toDiscard = Deserializer.getPowerUp(hashGarbage, sender);
            //move the card not chosen from the user's power ups to the garbage deck
            Controller.getInstance().getDecksManager().addToGarbage(toDiscard);
            curr.removePowerUpCard(toDiscard);
            //now we want to set the right generation spot to the player
            Color powerupColor = HandyFunctions.stringToColor(toDiscard.getAmmoCube().name());
            for (Room r : Game.getInstance().getGameField().getRooms()) {
                if (r.hasGenerationSpot() && r.getGenSpot().getPlatformColor().equals(powerupColor))
                    curr.setCurrentPlatform(r.getGenSpot());
            }
            c.callView(new ShowActionMenuMessage(null), sender);
            Game.getInstance().notifyChanges();
            //now the current player has to choose an action
        } catch (Exception e) {
            HandyFunctions.LOGGER.log(Level.SEVERE, "the power up is not valid");
        }
    }
}
