package it.polimi.se2019.network.message.toclient;

import it.polimi.se2019.view.client.RemoteView;

import java.util.ArrayList;

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
