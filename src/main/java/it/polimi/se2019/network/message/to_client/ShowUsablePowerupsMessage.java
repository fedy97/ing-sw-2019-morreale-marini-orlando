package it.polimi.se2019.network.message.to_client;

import it.polimi.se2019.model.CardRep;
import it.polimi.se2019.view.client.RemoteView;

import java.util.ArrayList;
import java.util.List;

public class ShowUsablePowerupsMessage extends ToClientMessage {

    /**
     * @param payload list of clients' username
     */
    public ShowUsablePowerupsMessage(Object payload){
        super(payload);
    }

    @Override
    public void performAction(RemoteView remoteView) {
        remoteView.showUsablePowerups((ArrayList<String>) payload);
    }

}
