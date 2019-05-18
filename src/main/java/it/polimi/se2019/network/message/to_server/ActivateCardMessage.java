package it.polimi.se2019.network.message.to_server;

import it.polimi.se2019.controller.Controller;
import it.polimi.se2019.controller.ControllerState;
import it.polimi.se2019.model.card.powerups.PowerUpCard;
import it.polimi.se2019.model.card.weapons.WeaponCard;
import it.polimi.se2019.utils.Deserializer;


public class ActivateCardMessage extends ToServerMessage {
    public ActivateCardMessage(Object payload) {
        super(payload);
    }

    @Override
    /**
     */
    public void performAction(){
        Controller actor = Controller.getInstance();
        int id = (int) payload;
        //TODO
        if(Deserializer.getPowerUp(id, sender) != null) {
            PowerUpCard powerUp = Deserializer.getPowerUp(id, sender);
            actor.setState(ControllerState.PROCESSING_POWERUP);
            actor.addStages(powerUp.getStages());
            actor.processPowerUp(powerUp);
        }

        if(Deserializer.getWeapon(id) != null) {
            WeaponCard weapon = Deserializer.getWeapon(id);
            actor.setState(ControllerState.PROCESSING_WEAPON);
            //actor.addStages(weapon);
            actor.processWeaponCard(weapon);
        }
    }
}
