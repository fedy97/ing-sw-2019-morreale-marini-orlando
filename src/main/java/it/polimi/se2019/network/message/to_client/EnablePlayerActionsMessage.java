package it.polimi.se2019.network.message.to_client;

import it.polimi.se2019.view.client.RemoteView;

import java.util.List;

public class EnablePlayerActionsMessage extends ToClientMessage {

    public EnablePlayerActionsMessage(Object payload) {
        super(payload);
    }

    @Override
    public void performAction(RemoteView remoteView) {
        remoteView.setValidActions((boolean[])payload);
    }
}


