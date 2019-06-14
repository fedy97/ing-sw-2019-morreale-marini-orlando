package it.polimi.se2019.network.message.to_server;

import it.polimi.se2019.controller.Controller;
import it.polimi.se2019.controller.PlayerManager;
import it.polimi.se2019.model.card.weapons.WeaponCard;
import it.polimi.se2019.utils.Deserializer;
import it.polimi.se2019.utils.HandyFunctions;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

/**
 * Message sent by the client to notify the weapons it want to recharge (chosen from the Validator set)
 */
public class ReloadWeaponsMessage extends ToServerMessage {
    public ReloadWeaponsMessage(Object payload) {
        super(payload);
    }

    @Override
    public void performAction() {
        //TODO change list to a single hash
        Controller actor = Controller.getInstance();
        List<String> lightVersion = (ArrayList<String>) payload;
        List<WeaponCard> weapons = new ArrayList<>();
        for (String weapon : lightVersion)
            weapons.add(Deserializer.getWeapon(weapon));

        PlayerManager manager = actor.getPlayerManager();

        if (actor.isValidAction("reload")) {
            try {
                manager.reload(weapons);
                actor.removeValidAction("reload");
            } catch (Exception e) {
                HandyFunctions.LOGGER.log(Level.WARNING, "Not enough ammo! The client tried to reload weapons outside" +
                        "the validator set! ");
            }
        } else {
            HandyFunctions.LOGGER.log(Level.WARNING, "Client is trying to perform denied actions!");
        }
    }
}
