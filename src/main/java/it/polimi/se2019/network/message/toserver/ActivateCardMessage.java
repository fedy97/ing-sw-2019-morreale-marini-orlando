package it.polimi.se2019.network.message.toserver;

import it.polimi.se2019.controller.Controller;
import it.polimi.se2019.model.card.powerups.PowerUpCard;
import it.polimi.se2019.model.card.powerups.TagbackGrenade;
import it.polimi.se2019.model.card.weapons.WeaponCard;
import it.polimi.se2019.network.message.toclient.ShowActionMenuMessage;
import it.polimi.se2019.utils.Deserializer;


public class ActivateCardMessage extends ToServerMessage {
    public ActivateCardMessage(Object payload) {
        super(payload);
    }

    @Override
    /**
     */
    public void performAction() {
        Controller actor = Controller.getInstance();
        String id = (String) payload;

        if (Deserializer.getPowerUp(Integer.parseInt(id), sender) != null) {
            PowerUpCard powerUp = Deserializer.getPowerUp(Integer.parseInt(id), sender);

            if (powerUp.getName().equals("granata venom")) {
                TagbackGrenade tagback = (TagbackGrenade) powerUp;
                tagback.setUserTarget(sender);
                actor.processPowerUp(tagback);
            } else
                actor.processPowerUp(powerUp);
            if (powerUp.getName().equals("raggio cinetico") || powerUp.getName().equals("teletrasporto"))
                actor.callView(new ShowActionMenuMessage(null), sender);
        }

        if (Deserializer.getWeapon(id) != null) {
            WeaponCard weapon = Deserializer.getWeapon(id);
            actor.processWeaponCard(weapon);
        }
    }
}
