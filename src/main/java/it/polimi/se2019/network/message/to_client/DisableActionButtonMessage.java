package it.polimi.se2019.network.message.to_client;

import it.polimi.se2019.view.client.RemoteView;

public class DisableActionButtonMessage extends ToClientMessage {

    public DisableActionButtonMessage(Object payload){
        super(payload);
    }

    @Override
    public void performAction(RemoteView actor){
        actor.setValidActions(new boolean[]{false, false, false, true, true});
    }
}
