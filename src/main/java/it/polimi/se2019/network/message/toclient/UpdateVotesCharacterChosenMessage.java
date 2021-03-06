package it.polimi.se2019.network.message.toclient;

import it.polimi.se2019.view.client.RemoteView;


public class UpdateVotesCharacterChosenMessage extends ToClientMessage {

    public UpdateVotesCharacterChosenMessage(Object payload) {
        super(payload);
    }

    @Override
    public void performAction(RemoteView remoteView) {
        remoteView.updateVotesCharacterChosen((String) payload);
    }
}
