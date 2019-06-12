package it.polimi.se2019.network.message.to_client;

import it.polimi.se2019.view.client.RemoteView;

public class UpdateTimerTurnMessage extends ToClientMessage {

    public UpdateTimerTurnMessage(Object payload) {
        super(payload);
    }

    @Override
    public void performAction(RemoteView remoteView) {
        remoteView.updateTimerTurn((int) payload);
    }
}
