package it.polimi.se2019.network.message.to_client;


import it.polimi.se2019.view.client.RemoteView;

import java.util.ArrayList;

public class ShowUsableWeaponsMessage extends ToClientMessage {

    public ShowUsableWeaponsMessage(Object payload) {
        super(payload);
    }

    /**
     * @param remoteView on which actions have to be performed
     */
    public void performAction(RemoteView remoteView) {
        remoteView.showUsableWeapons((ArrayList<String>) payload);
    }
}
