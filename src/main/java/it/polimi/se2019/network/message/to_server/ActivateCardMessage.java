package it.polimi.se2019.network.message.to_server;

import it.polimi.se2019.controller.Controller;
import it.polimi.se2019.model.card.powerups.PowerUpCard;
import it.polimi.se2019.model.card.powerups.TagbackGrenade;
import it.polimi.se2019.model.card.weapons.WeaponCard;
import it.polimi.se2019.network.message.to_client.ShowActionMenuMessage;
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
        //TODO
        if (Deserializer.getPowerUp(Integer.parseInt(id), sender) != null) {
            PowerUpCard powerUp = Deserializer.getPowerUp(Integer.parseInt(id), sender);

            if (powerUp.getName().equals("granata venom")) {
                TagbackGrenade tagback = (TagbackGrenade) powerUp;
                tagback.setUserTarget(sender);
                System.out.println(sender);
                actor.processPowerUp(tagback);
            } else
                actor.processPowerUp(powerUp);
            actor.callView(new ShowActionMenuMessage(null), sender);
        }

        if (Deserializer.getWeapon(id) != null) {
            WeaponCard weapon = Deserializer.getWeapon(id);
            actor.processWeaponCard(weapon);
        }
    }
}
