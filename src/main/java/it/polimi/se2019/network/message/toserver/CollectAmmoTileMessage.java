package it.polimi.se2019.network.message.toserver;

import it.polimi.se2019.controller.Controller;
import it.polimi.se2019.controller.PlayerManager;

public class CollectAmmoTileMessage extends ToServerMessage {
    public CollectAmmoTileMessage(Object payload) {
        super(payload);
    }

    @Override
    public void performAction() {
        Controller actor = Controller.getInstance();
        PlayerManager manager = actor.getPlayerManager();
        manager.grabAmmoCard();
    }
}
