package it.polimi.se2019.network.message.to_client;

import it.polimi.se2019.view.client.RemoteView;

import java.util.List;

public class ShowPlatformMessageForOther extends ToClientMessage {

    public ShowPlatformMessageForOther(Object payload){
        super(payload);
    }

    public void performAction(RemoteView view){
        view.lightPlatforms((List<String>) payload);
        //view.showMessage("Chose where do you want to move the selected character");
    }

}
