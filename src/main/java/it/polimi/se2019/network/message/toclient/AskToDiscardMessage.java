package it.polimi.se2019.network.message.toclient;

import it.polimi.se2019.view.client.RemoteView;

public class AskToDiscardMessage extends ToClientMessage {

    public AskToDiscardMessage(Object payload) {
        super(payload);
    }

    @Override
    public void performAction(RemoteView remoteView) {
        remoteView.switchWeapon();
    }

}
