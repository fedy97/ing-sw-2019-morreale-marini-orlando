package it.polimi.se2019.network.message.to_client;

import it.polimi.se2019.view.client.RemoteView;

import java.util.List;

public class SetRandomCharacterMessage extends ToClientMessage {
    private String username;

    /**
     * @param payload list of clients' username
     */
    public SetRandomCharacterMessage(Object payload, String username) {
        super(payload);
        this.username = username;
    }

    @Override
    public void performAction(RemoteView remoteView) {
        String charReceived = (String) payload;
        if (username.equals(remoteView.getUserName()))
            remoteView.setRandomChar(charReceived);

    }

}
