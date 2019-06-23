package it.polimi.se2019.network.message.to_client;

import it.polimi.se2019.view.client.RemoteView;

public class ResetTimerMessage extends ToClientMessage {

    public ResetTimerMessage(Object payload) {
        super(payload);
    }

    @Override
    public void performAction(RemoteView remoteView) {
        remoteView.resetTimer();
    }
}
