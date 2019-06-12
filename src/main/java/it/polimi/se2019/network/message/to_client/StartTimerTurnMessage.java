package it.polimi.se2019.network.message.to_client;

import it.polimi.se2019.view.client.RemoteView;

public class StartTimerTurnMessage extends ToClientMessage {
    private String curr;

    public StartTimerTurnMessage(Object payload, String curr) {
        super(payload);
        this.curr = curr;
    }

    @Override
    public void performAction(RemoteView remoteView) {
        remoteView.startTimerTurn((int) payload, curr);
    }
}
