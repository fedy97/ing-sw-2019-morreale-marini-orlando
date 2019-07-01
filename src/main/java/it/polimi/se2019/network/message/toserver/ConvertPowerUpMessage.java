package it.polimi.se2019.network.message.toserver;

import it.polimi.se2019.controller.Controller;
import it.polimi.se2019.model.card.powerups.PowerUpCard;
import it.polimi.se2019.network.message.toclient.ShowActionMenuMessage;
import it.polimi.se2019.utils.Deserializer;
import it.polimi.se2019.utils.HandyFunctions;

import java.util.logging.Level;

public class ConvertPowerUpMessage extends ToServerMessage {
    public ConvertPowerUpMessage(Object payload) {
        super(payload);
    }

    @Override
    public void performAction() {
        Integer powerUpHash = (Integer) payload;
        PowerUpCard powerUp = Deserializer.getPowerUp(powerUpHash, sender);
        try {
            Controller.getInstance().getPlayerManager().convertPowerUpToAmmo(powerUp);
        } catch (Exception e) {
            HandyFunctions.LOGGER.log(Level.WARNING, e.getMessage());
        }
        Controller.getInstance().callView(new ShowActionMenuMessage(null), sender);
    }
}
