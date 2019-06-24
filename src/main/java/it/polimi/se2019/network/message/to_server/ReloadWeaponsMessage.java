package it.polimi.se2019.network.message.to_server;

import it.polimi.se2019.controller.Controller;
import it.polimi.se2019.controller.PlayerManager;
import it.polimi.se2019.controller.validator.UserValidActions;
import it.polimi.se2019.model.card.weapons.WeaponCard;
import it.polimi.se2019.network.message.to_client.EnablePlayerActionsMessage;
import it.polimi.se2019.utils.CustomLogger;
import it.polimi.se2019.utils.Deserializer;

/**
 * Message sent by the client to notify the weapons it want to recharge (chosen from the Validator set)
 */
public class ReloadWeaponsMessage extends ToServerMessage {
    public ReloadWeaponsMessage(Object payload) {
        super(payload);
    }

    @Override
    public void performAction() {
        String light = (String) payload;

        if (!light.equals("null")) {
            WeaponCard weapon = Deserializer.getWeapon(light);
            PlayerManager manager = Controller.getInstance().getPlayerManager();
            EnablePlayerActionsMessage message = new EnablePlayerActionsMessage(UserValidActions.NO_BASIC.getActions());
            Controller.getInstance().callView(message, manager.getCurrentPlayer().getName());
            try {
                manager.reload(weapon);
            } catch (Exception e) {
                CustomLogger.logException(this.getClass().getName(), e);
            }
        }
    }
}
