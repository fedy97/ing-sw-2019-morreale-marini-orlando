package it.polimi.se2019.network.message.to_server;

import it.polimi.se2019.controller.Controller;

public class UseWeaponMessage extends ToServerMessage {
    public UseWeaponMessage(Object payload) {
        super(payload);
    }

    @Override
    /**
     * Control if the action "move" is valid and move the character of the current player
     */
    public void performAction(Controller actor) {
        //TODO
    }
}
