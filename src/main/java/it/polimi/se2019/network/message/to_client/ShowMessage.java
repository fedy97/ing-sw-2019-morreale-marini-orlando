package it.polimi.se2019.network.message.to_client;

import it.polimi.se2019.view.client.RemoteView;

public class ShowMessage extends ToClientMessage {

    public ShowMessage(Object payload) {
        super(payload);
    }

    /**
     * Tell the remote view (CLI or GUI) to show the possible platform destination
     */
    public void performAction(RemoteView actor) {
        actor.showMessage((String) payload);
    }
}
