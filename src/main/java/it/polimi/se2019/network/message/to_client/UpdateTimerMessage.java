package it.polimi.se2019.network.message.to_client;

import it.polimi.se2019.view.client.RemoteView;

public class UpdateTimerMessage extends ToClientMessage {

    public  UpdateTimerMessage(Object payload) { super(payload);}

    @Override
    public void performAction(RemoteView remoteView) {
        remoteView.updateTimerLobby((int)payload);
    }
}
