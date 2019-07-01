package it.polimi.se2019.network.message.toclient;

import it.polimi.se2019.view.client.RemoteView;

public class EnablePlayerActionsMessage extends ToClientMessage {

    public EnablePlayerActionsMessage(Object payload) {
        super(payload);
    }

    @Override
    public void performAction(RemoteView remoteView) {
        remoteView.setValidActions((boolean[])payload);
    }
}


