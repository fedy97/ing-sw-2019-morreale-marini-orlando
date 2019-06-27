package it.polimi.se2019.network.message.to_client;


import it.polimi.se2019.view.client.RemoteView;

import java.util.Map;

public class ShowScoreboardMessage extends ToClientMessage {

    public ShowScoreboardMessage(Object payload) {
        super(payload);
    }

    /**
     * @param remoteView on which actions have to be performed
     */
    public void performAction(RemoteView remoteView) {
        Map<String, Integer> score = (Map<String, Integer>) payload;
        remoteView.showScoreboard(score);
    }
}
