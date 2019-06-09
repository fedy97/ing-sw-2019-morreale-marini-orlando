package it.polimi.se2019.network.message.to_client;

import it.polimi.se2019.view.client.RemoteView;

public class SendBinaryOption extends ToClientMessage {
    public SendBinaryOption(Object payload){
        super(payload);
    }

    @Override
    public void performAction(RemoteView remoteView){
        //TODO method in remote view
    }
}
