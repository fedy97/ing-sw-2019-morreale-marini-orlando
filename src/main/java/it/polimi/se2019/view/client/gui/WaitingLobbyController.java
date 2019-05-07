package it.polimi.se2019.view.client.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class WaitingLobbyController extends Observable implements Observer {

    private List<String> playerNames = new ArrayList<>();
    private List<Label> players;

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

    public WaitingLobbyController() {
        players = new ArrayList<>();
    }

    public void initialize() {

    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
