package it.polimi.se2019.network.message.to_client;

import it.polimi.se2019.utils.HandyFunctions;
import it.polimi.se2019.view.client.RemoteView;

import java.util.Map;

public class UpdateVotesCharacterChosenMessage extends ToClientMessage {

    public UpdateVotesCharacterChosenMessage(Object payload) { super(payload);}

    @Override
    public void performAction(RemoteView remoteView) {
        remoteView.updateVotesCharacterChosen((String) payload);
        //HandyFunctions.printLineConsole(payload.toString());
    }
}
