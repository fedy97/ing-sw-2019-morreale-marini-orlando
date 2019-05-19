package it.polimi.se2019.network.message.to_server;

import it.polimi.se2019.controller.Controller;
import it.polimi.se2019.model.Game;
import it.polimi.se2019.model.board.Room;
import it.polimi.se2019.model.card.powerups.PowerUpCard;
import it.polimi.se2019.model.player.Player;
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
            int hashChosen = ((ArrayList<Integer>) payload).get(0);
            int hashGarbage = ((ArrayList<Integer>) payload).get(1);
            Controller c = Controller.getInstance();
            Player curr = c.getPlayerManager().getCurrentPlayer();
            //move the card not chosen from the user's power ups to the garbage deck
            Controller.getInstance().getDecksManager().addToGarbage(Deserializer.getPowerUp(hashGarbage, this.sender));
            //now we want to set the right generation spot to the player
            PowerUpCard p = Deserializer.getPowerUp(hashChosen, this.sender);
            Color powerupColor = HandyFunctions.stringToColor(p.getAmmoCube().name());
            for (Room r : Game.getInstance().getGameField().getRooms()) {
                if (r.hasGenerationSpot() && r.getGenSpot().getPlatformColor().equals(powerupColor)) {
                    curr.setCurrentPlatform(r.getGenSpot());
                }
            }
            Game.getInstance().notifyChanges();

        } catch (Exception e) {
            HandyFunctions.LOGGER.log(Level.SEVERE, "the power up is not valid");
        }
    }
}
