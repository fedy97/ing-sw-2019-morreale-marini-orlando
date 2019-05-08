package it.polimi.se2019.network.message.to_client;

import it.polimi.se2019.view.client.RemoteView;

import java.util.List;

public class NewConnectionMessage extends ToClientMessage {

    public NewConnectionMessage(Object payload){
        super(payload);
    }
    //the payload is the username String
    @Override
    public void performAction(RemoteView remoteView) {
        //todo for simo, implement updatePlayersOnWaitingList on CLI view
        remoteView.updatePlayersOnWaitingList((List<String>) payload);
    }

}
