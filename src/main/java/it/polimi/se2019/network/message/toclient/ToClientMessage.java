package it.polimi.se2019.network.message.toclient;

import it.polimi.se2019.network.message.Message;
import it.polimi.se2019.view.client.RemoteView;

public abstract class ToClientMessage extends Message {

    public ToClientMessage(Object payload) {
        super(payload);
    }

    public abstract void performAction(RemoteView remoteView);
}
