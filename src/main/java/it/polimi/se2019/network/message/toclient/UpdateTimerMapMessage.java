package it.polimi.se2019.network.message.toclient;

import it.polimi.se2019.view.client.RemoteView;

public class UpdateTimerMapMessage extends ToClientMessage {

    public UpdateTimerMapMessage(Object payload) {super(payload);}

    @Override
    public void performAction(RemoteView remoteView) {
        remoteView.updateTimerMap((int) payload);
    }
}
