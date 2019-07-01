package it.polimi.se2019.network.message.toclient;

import it.polimi.se2019.view.client.RemoteView;

public class ShowActionMenuMessage extends ToClientMessage {

    public ShowActionMenuMessage(Object payload) {
        super(payload);
    }

    @Override
    public void performAction(RemoteView remoteView) {
        remoteView.showActionMenu();
    }
}
