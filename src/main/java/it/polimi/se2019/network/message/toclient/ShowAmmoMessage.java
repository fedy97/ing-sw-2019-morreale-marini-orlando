package it.polimi.se2019.network.message.toclient;

import it.polimi.se2019.view.client.RemoteView;

public class ShowAmmoMessage extends ToClientMessage {

    public ShowAmmoMessage(Object payload) {
        super(payload);
    }

    @Override
    public void performAction(RemoteView remoteView) {
        remoteView.showAmmoToDiscard();
    }
}
