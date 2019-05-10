package it.polimi.se2019.network.message.to_client;

import it.polimi.se2019.view.client.RemoteView;

import java.util.Map;

public class UpdateVotesMapChosenMessage extends ToClientMessage {

    public UpdateVotesMapChosenMessage(Object payload) { super(payload);}

    @Override
    public void performAction(RemoteView remoteView) {
        remoteView.updateVotesMapChosen((Map<Integer, Integer>) payload);
    }
}
