package it.polimi.se2019.network.message.toserver;

import it.polimi.se2019.controller.Controller;
import it.polimi.se2019.controller.ControllerState;
import it.polimi.se2019.controller.PlayerManager;
import it.polimi.se2019.model.card.weapons.WeaponCard;
import it.polimi.se2019.network.message.toclient.ShowActionMenuMessage;
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
        Controller c = Controller.getInstance();

        if (!light.equals("null")) {
            WeaponCard weapon = Deserializer.getWeapon(light);
            PlayerManager manager = Controller.getInstance().getPlayerManager();
            try {
                manager.reload(weapon);
            } catch (Exception e) {
                CustomLogger.logException(this.getClass().getName(), e);
            }
            c.setWasRecharged(true);
        }
        if (c.getState() != ControllerState.PROCESSING_ACTION_1)
            c.callView(new ShowActionMenuMessage(null), sender);
    }
}
