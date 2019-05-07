package it.polimi.se2019.network.message;

import it.polimi.se2019.view.client.RemoteView;

public abstract class ToClientMessage extends Message {

    public ToClientMessage(Object payload) {
        super(payload);
    }

    public abstract void performAction(RemoteView remoteView);
}
