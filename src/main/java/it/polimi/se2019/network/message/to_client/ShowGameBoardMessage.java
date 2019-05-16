package it.polimi.se2019.network.message.to_client;

import it.polimi.se2019.view.client.RemoteView;
import javafx.application.Platform;

import java.util.ArrayList;

public class ShowGameBoardMessage extends ToClientMessage {

    public ShowGameBoardMessage(Object payload) {
        super(payload);
    }

    @Override
    public void performAction(RemoteView remoteView) {
        remoteView.showGameBoard();
        ArrayList<String> rec = (ArrayList<String>) payload;
        if (rec.get(0).equals(remoteView.getUserName())) {
            Platform.runLater(
                    () -> {
                        remoteView.showChoosePowerup(rec.get(1), rec.get(2));
                    });
        }

    }
}