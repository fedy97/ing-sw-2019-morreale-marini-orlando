package it.polimi.se2019.network.message.to_client;

import it.polimi.se2019.view.client.RemoteView;

public class PingWaitingClientsMessage extends ToClientMessage {

    public PingWaitingClientsMessage(Object payload) {
        super(payload);
    }

    @Override
    public void performAction(RemoteView remoteView) {
        remoteView.receiveWaitingPingFromServer();
    }
}
