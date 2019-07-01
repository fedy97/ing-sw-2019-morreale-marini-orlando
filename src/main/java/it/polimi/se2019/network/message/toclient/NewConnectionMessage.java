package it.polimi.se2019.network.message.toclient;

import it.polimi.se2019.view.client.RemoteView;

import java.util.List;

public class NewConnectionMessage extends ToClientMessage {

    /**
     * @param payload list of clients' username
     */
    public NewConnectionMessage(Object payload){
        super(payload);
    }

    @Override
    public void performAction(RemoteView remoteView) {
        remoteView.updatePlayersOnWaitingList((List<String>) payload);
    }

}
