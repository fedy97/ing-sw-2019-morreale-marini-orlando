package it.polimi.se2019.network.message.to_server;

import it.polimi.se2019.controller.Controller;
import it.polimi.se2019.model.card.weapons.WeaponCard;
import it.polimi.se2019.utils.Deserializer;

public class ChosenEffectMessage extends ToServerMessage {

    public ChosenEffectMessage(Object payload) {
        super(payload);
    }

    @Override
    public void performAction() {
        Controller.getInstance().getChosenEffect().add((Integer) payload);
    }
}
