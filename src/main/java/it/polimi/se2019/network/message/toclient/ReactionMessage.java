package it.polimi.se2019.network.message.toclient;

import it.polimi.se2019.view.client.RemoteView;

public class ReactionMessage extends ToClientMessage {

    public ReactionMessage(Object payload) {
        super(payload);
    }

    @Override
    public void performAction(RemoteView remoteView) {
        //remoteView.showMessage("payload" is attacking you, want to play a power up?);
    }
}
