package it.polimi.se2019.network.message.to_client;

import it.polimi.se2019.model.AmmoRep;
import it.polimi.se2019.model.CardRep;
import it.polimi.se2019.view.client.RemoteView;
import javafx.application.Platform;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ShowGameBoardMessage extends ToClientMessage {
    private List<AmmoRep> ammoReps;
    private List<CardRep> cardReps;
    private Map<String,List<CardRep>> posWeaponsReps;
    private List<String> arrChars;
    public ShowGameBoardMessage(Object payload, List<AmmoRep> ammoReps, List<CardRep> cardReps, Map<String,List<CardRep>> posWeaponsReps,List<String> arrChars) {
        super(payload);
        this.ammoReps = ammoReps;
        this.cardReps = cardReps;
        this.posWeaponsReps = posWeaponsReps;
        this.arrChars = arrChars;
    }

    @Override
    public void performAction(RemoteView remoteView) {
        remoteView.showGameBoard(ammoReps, posWeaponsReps, arrChars);
        String rec = (String) payload;
        if (rec.equals(remoteView.getUserName())) {
            remoteView.showChoosePowerup(cardReps.get(0), cardReps.get(1));
        }

    }
}