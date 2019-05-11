package it.polimi.se2019.view.client.gui;

import it.polimi.se2019.network.message.to_server.SendMapChosenMessage;
import it.polimi.se2019.utils.HandyFunctions;
import it.polimi.se2019.view.client.RemoteView;
import it.polimi.se2019.view.State;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.logging.Level;

public class GUI extends RemoteView {

    private WaitingLobbyController waitingLobbyController;
    private ChooseMapController chooseMapController;
    private ChooseCharacterController chooseCharacterController;

    private Scene sceneWaitingLobby;
    private Scene sceneChooseMap;
    private Scene sceneChooseCharacter;
    private Stage stage;

    public GUI(String user, Stage stage) {
        this.stage = stage;
        this.userName = user;
    }

    @Override
    public void start() {
        initWaitingLobby();
        initChooseMap();
        initChooseCharacter();
        showWaitingLobby();
    }

    private void showWaitingLobby() {
        stage.setScene(sceneWaitingLobby);
        stage.show();
    }

    @Override
    public void showChooseMap() {
        Platform.runLater(
                () -> {
                    chooseMapController.passGUI(this);
                    stage.setScene(sceneChooseMap);
                    stage.show();
                });
    }

    @Override
    public void showChooseCharacter() {
        Platform.runLater(
                () -> {
                    chooseCharacterController.passGUI(this);
                    stage.setScene(sceneChooseCharacter);
                    stage.show();
                });
    }

    private void initChooseCharacter() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/chooseCharacter.fxml"));
        try {
            Parent root = loader.load();
            stage.setTitle("Choose Character");
            sceneChooseCharacter = new Scene(root);
            chooseCharacterController = loader.getController();
        } catch (IOException e) {
            HandyFunctions.LOGGER.log(Level.SEVERE, "error initializing choose character");
        }
    }

    private void initWaitingLobby() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/waitingLobby.fxml"));
        try {
            Parent root = loader.load();
            stage.setTitle("Waiting Lobby");
            sceneWaitingLobby = new Scene(root);
            waitingLobbyController = loader.getController();
        } catch (IOException e) {
            HandyFunctions.LOGGER.log(Level.SEVERE, "error initializing waiting lobby");
        }
    }

    private void initChooseMap() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/chooseMap.fxml"));
        try {
            Parent root = loader.load();
            stage.setTitle("Choose Map");
            sceneChooseMap = new Scene(root);
            chooseMapController = loader.getController();
        } catch (IOException e) {
            HandyFunctions.LOGGER.log(Level.SEVERE, "error initializing choose map");
        }
    }

    /**
     * @param users to display on the view(GUI)
     */
    @Override
    public void updatePlayersOnWaitingList(List<String> users) {
        Platform.runLater(
                () -> {
                    waitingLobbyController.updateLoggedPlayers(users);
                });
    }

    @Override
    public void updateTimerLobby(int count) {
        Platform.runLater(
                () -> {
                    waitingLobbyController.updateTimer(count);
                });
    }

    @Override
    public void updateTimerMap(int count) {
        Platform.runLater(
                () -> {
                    chooseMapController.updateTimer(count);
                });
    }

    protected void sendMapChosenByPlayer(int config) {
        SendMapChosenMessage message = new SendMapChosenMessage(config);
        message.setSender(userName);
        viewSetChanged();
        notifyObservers(message);
    }

    @Override
    public void updateVotesMapChosen(Map<Integer, Integer> map) {
        Platform.runLater(
                () -> {
                    chooseMapController.updateVotes(map);
                });
    }

    @Override
    public void startGame() {
        //TODO
    }

    @Override
    public void setCommunicationType() {
        //TODO
    }

    @Override
    public void startConnection() {
        //TODO
    }

    @Override
    public void setUserName() {
        //useless
    }

    @Override
    public void waitGameStart() {
        //TODO
    }

    @Override
    public void lightWeapons(List<String> weapons) {
        //TODO
    }

    @Override
    public void lightPlatforms(List<String> platforms) {
        //TODO
    }


    @Override
    public void setState(State newState) {
        //TODO
    }

    @Override
    public void update(Observable obs, Object obj) {
        /*
        we use this in RemoteView, here we got specific update methods
         */
    }

}

