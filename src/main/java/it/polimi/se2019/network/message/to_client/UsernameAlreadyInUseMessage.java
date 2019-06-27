package it.polimi.se2019.network.message.to_client;

import it.polimi.se2019.view.client.RemoteView;

public class UsernameAlreadyInUseMessage extends ToClientMessage {
    public UsernameAlreadyInUseMessage(Object payload) {
        super(payload);
    }

    @Override
    public void performAction(RemoteView remoteView) {
            remoteView.showUsernameAlreadyInUse((String) payload);

    }
}
