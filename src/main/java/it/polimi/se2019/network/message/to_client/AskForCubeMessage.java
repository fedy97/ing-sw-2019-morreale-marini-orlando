package it.polimi.se2019.network.message.to_client;

import it.polimi.se2019.view.client.RemoteView;

public class AskForCubeMessage extends ToClientMessage {
    public AskForCubeMessage(Object payload){
        super(payload);
    }

    @Override
    public void performAction(RemoteView remoteView){
        //TODO method in remote view
    }
}
