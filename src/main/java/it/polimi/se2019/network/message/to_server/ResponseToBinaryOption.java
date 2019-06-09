package it.polimi.se2019.network.message.to_server;

import it.polimi.se2019.controller.Controller;
import it.polimi.se2019.controller.ControllerState;
import it.polimi.se2019.model.card.powerups.PowerUpCard;
import it.polimi.se2019.model.card.weapons.WeaponCard;
import it.polimi.se2019.utils.Deserializer;


public class ResponseToBinaryOption extends ToServerMessage {
    public ResponseToBinaryOption(Object payload) {
        super(payload);
    }

    @Override
    /**
     */
    public void performAction(){
        Controller actor = Controller.getInstance();
        Boolean option = (Boolean) payload;
        actor.getChosenBinaryOption().add(option);
    }
}
