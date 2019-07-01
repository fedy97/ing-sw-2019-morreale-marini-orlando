package it.polimi.se2019.network.message.toclient;

import it.polimi.se2019.view.client.RemoteView;

import java.util.ArrayList;

public class ShowUsableEffectsMessage extends ToClientMessage {

    public ShowUsableEffectsMessage(Object payload) {
        super(payload);
    }

    /**
     * Tell the remote view (CLI or GUI) to show the possible platform destination
     */
    public void performAction(RemoteView actor) {
        actor.enlightenEffects((ArrayList<Integer>) payload);
    }
}
