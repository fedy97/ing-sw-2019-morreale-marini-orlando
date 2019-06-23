package it.polimi.se2019.view.client.gui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.List;

public class WaitingLobbyController {

    private List<Label> playerLabels = new ArrayList<>();

    @FXML
    private Label player1;
    @FXML
    private Label player2;
    @FXML
    private Label player3;
    @FXML
    private Label player4;
    @FXML
    private Label player5;
    @FXML
    private Label playerdisconnected;
    @FXML
    private Label labelwaiting;
    @FXML
    private ProgressIndicator progress;
    private float timerInt;
    private Font normale;

    public void initialize() {
        Platform.runLater(() -> {
            playerLabels.add(player1);
            playerLabels.add(player2);
            playerLabels.add(player3);
            playerLabels.add(player4);
            playerLabels.add(player5);
            normale = javafx.scene.text.Font.loadFont(
                    getClass().getResource("/font.ttf").toExternalForm(),
                    21
            );
            labelwaiting.setFont(normale);
        });


    }

    public void updateLoggedPlayers(List<String> users) {
        int n = 0;
        for (String user : users) {
            playerLabels.get(n).setText(user + " joined the game");
            n++;
        }
        for (int i = users.size(); i < playerLabels.size(); i++)
            playerLabels.get(i).setText("");

    }

    public void updateTimer(int count) {
        if (progress.getProgress() == -1) {
            timerInt = count;
            progress.setProgress(1 / timerInt);
        } else progress.setProgress(progress.getProgress() + (1 / timerInt));
    }

    protected void resetTimer() {
        progress.setProgress(-1);
    }


}
