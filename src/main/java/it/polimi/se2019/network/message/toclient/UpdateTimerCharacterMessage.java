package it.polimi.se2019.network.message.toclient;

import it.polimi.se2019.view.client.RemoteView;

public class UpdateTimerCharacterMessage extends ToClientMessage {

    public UpdateTimerCharacterMessage(Object payload) {super(payload);}

    @Override
    public void performAction(RemoteView remoteView) {
        remoteView.updateTimerCharacter((int) payload);
    }
}
