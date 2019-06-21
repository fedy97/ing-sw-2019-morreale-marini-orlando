package it.polimi.se2019.network.message.to_client;

import it.polimi.se2019.model.rep.LightGameVersion;
import it.polimi.se2019.view.client.RemoteView;

import java.util.List;

public class ShowReconnectedGameBoard extends ToClientMessage {
    LightGameVersion lightGameVersion;
    List<String> arrChars;
    String myChar;
    /**
     * @param payload list of clients' username
     */
    public ShowReconnectedGameBoard(Object payload, LightGameVersion lightGameVersion, List<String> arrChars, String myChar){
        super(payload);
        this.lightGameVersion = lightGameVersion;
        this.arrChars = arrChars;
        this.myChar = myChar;
    }

    @Override
    public void performAction(RemoteView remoteView) {
        int config = (int) payload;
        remoteView.showReconnectedGameBoard(config, lightGameVersion, arrChars, myChar);
    }

}
