package it.polimi.se2019.view.client.gui;

import it.polimi.se2019.utils.HandyFunctions;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

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
    private Label timer;

    public void initialize() {
        playerLabels.add(player1);
        playerLabels.add(player2);
        playerLabels.add(player3);
        playerLabels.add(player4);
        playerLabels.add(player5);
        timer.setText("");
    }

    public void updateLoggedPlayers(List<String> users) {
        int n = 0;
        for (String user : users) {
            playerLabels.get(n).setText(user + " joined the game");
            n++;
        }

    }

    public void updateTimer(int count) {
        timer.setText(Integer.toString(count));
    }


}
