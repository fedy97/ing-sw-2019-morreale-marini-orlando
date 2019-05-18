package it.polimi.se2019.network.message.to_server;

import it.polimi.se2019.controller.Controller;
import it.polimi.se2019.exceptions.InvalidCardException;
import it.polimi.se2019.model.Game;
import it.polimi.se2019.model.board.Room;
import it.polimi.se2019.model.card.powerups.PowerUpCard;
import it.polimi.se2019.model.player.Player;
import it.polimi.se2019.utils.Deserializer;
import it.polimi.se2019.utils.HandyFunctions;

import java.awt.*;
import java.util.logging.Level;

public class SendInitPowerUpMessage extends ToServerMessage {

    public SendInitPowerUpMessage(Object payload) {
        super(payload);
    }

    @Override
    public void performAction() {
        try {
            int hash = (int) payload;
            Controller c = Controller.getInstance();
            Player curr = c.getPlayerManager().getCurrentPlayer();
            //will be removed the other power up initially added anyway in the Game class
            //TODO the card removed will be deleted, not good
            HandyFunctions.printLineConsole("ciao");
            curr.removePowerUpCard(Deserializer.getOtherPowerUp(Integer.toString(hash),this.sender));
            //now we want to set the right generation spot to the player
            PowerUpCard p = Deserializer.getPowerUp(Integer.toString(hash), this.sender);
            Color powerupColor = HandyFunctions.stringToColor(p.getAmmoCube().name());
            for(Room r : Game.getInstance().getGameField().getRooms()) {
                if(r.hasGenerationSpot() && r.getGenSpot().getPlatformColor().equals(powerupColor)){
                    curr.setCurrentPlatform(r.getGenSpot());
                }
            }
            Game.getInstance().notifyChanges();

        } catch (Exception e) {
            HandyFunctions.LOGGER.log(Level.SEVERE, "the power up is not valid");
        }
    }
}
