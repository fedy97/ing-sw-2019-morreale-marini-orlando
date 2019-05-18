package it.polimi.se2019.network.message.to_server;

import it.polimi.se2019.controller.Controller;
import it.polimi.se2019.model.card.weapons.WeaponCard;
import it.polimi.se2019.utils.Deserializer;

import java.util.ArrayList;
import java.util.List;

/**
 * !!!WORK IN PROGRESS
 */
public class CollectWeaponsMessage extends ToServerMessage {

    public CollectWeaponsMessage(Object payload) {
        super(payload);
    }

    @Override
    public void performAction() {
        List<String> lightVersion = (ArrayList<String>) payload;
        Controller actor = Controller.getInstance();
        List<WeaponCard> weapons = new ArrayList<>();
        for (String weapon : lightVersion)
            weapons.add(Deserializer.getWeapon(weapon));
        //TODO

    }
}
