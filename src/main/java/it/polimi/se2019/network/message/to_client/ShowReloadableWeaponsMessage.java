package it.polimi.se2019.network.message.to_client;

import it.polimi.se2019.view.client.RemoteView;

import java.util.List;

public class ShowReloadableWeaponsMessage extends ToClientMessage {

    public ShowReloadableWeaponsMessage(Object payload) {
        super(payload);
    }

    @Override
    public void performAction(RemoteView remoteView) {
        remoteView.showReloadableWeapons((List<String>) payload);
    }
}
