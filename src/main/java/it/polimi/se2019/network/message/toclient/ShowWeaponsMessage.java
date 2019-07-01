package it.polimi.se2019.network.message.toclient;


import it.polimi.se2019.view.client.RemoteView;

import java.util.ArrayList;

public class ShowWeaponsMessage extends ToClientMessage {

    public ShowWeaponsMessage(Object payload) {
        super(payload);
    }

    /**
     * @param remoteView on which actions have to be performed
     */
    public void performAction(RemoteView remoteView) {
        remoteView.lightWeapons((ArrayList<String>) payload);
    }
}
