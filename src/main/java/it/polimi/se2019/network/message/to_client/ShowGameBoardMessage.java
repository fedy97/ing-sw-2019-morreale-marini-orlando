package it.polimi.se2019.network.message.to_client;

import it.polimi.se2019.model.AmmoRep;
import it.polimi.se2019.model.CardRep;
import it.polimi.se2019.view.client.RemoteView;
import javafx.application.Platform;

import java.util.List;

public class ShowGameBoardMessage extends ToClientMessage {
    private List<AmmoRep> ammoReps;
    private List<CardRep> cardReps;
    public ShowGameBoardMessage(Object payload, List<AmmoRep> ammoReps, List<CardRep> cardReps) {
        super(payload);
        this.ammoReps = ammoReps;
        this.cardReps = cardReps;
    }

    @Override
    public void performAction(RemoteView remoteView) {
        remoteView.showGameBoard(ammoReps);
        String rec = (String) payload;
        if (rec.equals(remoteView.getUserName())) {
            Platform.runLater(() -> remoteView.showChoosePowerup(cardReps.get(0), cardReps.get(1)));
        }

    }
}