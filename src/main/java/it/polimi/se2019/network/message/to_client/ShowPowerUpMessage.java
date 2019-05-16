package it.polimi.se2019.network.message.to_client;

import it.polimi.se2019.view.client.RemoteView;

public class ShowPowerUpMessage extends ToClientMessage {
    public ShowPowerUpMessage(Object payload) {
        super(payload);
    }

    @Override
    /**
     * Control if the action "move" is valid and move the character of the current player
     */
    public void performAction(RemoteView actor) {
        //TODO
    }
}