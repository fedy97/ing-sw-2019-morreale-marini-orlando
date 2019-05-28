package it.polimi.se2019.network.message.to_client;

import it.polimi.se2019.view.client.RemoteView;

public class EnablePlayerMessage extends ToClientMessage {

    public EnablePlayerMessage(Object payload) {
        super(payload);
    }

    @Override
    public void performAction(RemoteView remoteView){
        Boolean isCurr = (Boolean) payload;
        if (isCurr) remoteView.enableActions();
        else remoteView.disableActions();
    }

}
