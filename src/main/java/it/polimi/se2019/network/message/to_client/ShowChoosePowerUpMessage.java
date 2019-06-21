package it.polimi.se2019.network.message.to_client;

import it.polimi.se2019.model.rep.CardRep;
import it.polimi.se2019.view.client.RemoteView;

import java.util.ArrayList;
import java.util.List;

public class ShowChoosePowerUpMessage extends ToClientMessage {

    /**
     * @param payload list of clients' username
     */
    public ShowChoosePowerUpMessage(Object payload){
        super(payload);
    }

    @Override
    public void performAction(RemoteView remoteView) {
        List<CardRep> cardReps = (ArrayList) payload;
        remoteView.showChoosePowerup(cardReps.get(0), cardReps.get(1));
    }

}
