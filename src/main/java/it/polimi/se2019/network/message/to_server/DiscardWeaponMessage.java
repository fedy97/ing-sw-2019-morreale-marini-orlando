package it.polimi.se2019.network.message.to_server;

import it.polimi.se2019.controller.Controller;
import it.polimi.se2019.model.card.weapons.WeaponCard;
import it.polimi.se2019.utils.Deserializer;

public class DiscardWeaponMessage extends ToServerMessage {

    public DiscardWeaponMessage(Object payload) {
        super(payload);
    }

    @Override
    public void performAction() {
        String lightWeapon = (String) payload;
        WeaponCard chosen = Deserializer.getWeapon(lightWeapon);
        Controller.getInstance().getChosenWeapons().add(chosen);
    }
}
