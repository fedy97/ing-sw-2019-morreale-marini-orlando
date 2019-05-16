package it.polimi.se2019.view.client.gui;

import it.polimi.se2019.network.message.to_server.SendCharacterChosenMessage;
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
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.logging.Level;

public class GUI extends RemoteView {

    private WaitingLobbyController waitingLobbyController;
    private ChooseMapController chooseMapController;
    private ChooseCharacterController chooseCharacterController;
    private GameBoardController gameBoardController;
    private ChoosePowerupController choosePowerupController;

    private Scene sceneWaitingLobby;
    private Scene sceneChooseMap;
    private Scene sceneChooseCharacter;
    private Scene sceneGameBoard;
    private Scene sceneChoosePowerup;
    private Stage stage;
    private Stage secondStage;

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
        stage.setResizable(false);
        stage.show();
    }

    @Override
    public void showChoosePowerup(String im1, String im2) {
        initChoosePowerup(im1, im2);
        Platform.runLater(
                () -> {
                    choosePowerupController.passGUI(this);
                    secondStage.setScene(sceneChoosePowerup);
                    secondStage.setResizable(false);
                    secondStage.show();
                });
    }

    @Override
    public void showChooseMap() {
        Platform.runLater(
                () -> {
                    chooseMapController.passGUI(this);
                    stage.setScene(sceneChooseMap);
                    stage.setResizable(false);
                    stage.show();
                });
    }

    @Override
    public void showChooseCharacter(String config) {
        Platform.runLater(
                () -> {
                    initGameBoard(config);
                    chooseCharacterController.passGUI(this);
                    stage.setScene(sceneChooseCharacter);
                    stage.setResizable(false);
                    stage.show();
                });
    }

    @Override
    public void showGameBoard() {
        Platform.runLater(
                () -> {
                    gameBoardController.passGUI(this);
                    stage.setScene(sceneGameBoard);
                    stage.setResizable(false);
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

    private void initGameBoard(String config) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gameBoard" + config + ".fxml"));
        try {
            Parent root = loader.load();
            stage.setTitle("Adrenaline");
            sceneGameBoard = new Scene(root);
            gameBoardController = loader.getController();
        } catch (IOException e) {
            HandyFunctions.LOGGER.log(Level.SEVERE, "error initializing game board");
        }
    }

    private void initChoosePowerup(String im1path, String im2path) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/choosePowerup.fxml"));
        try {
            Parent root = loader.load();
            secondStage = new Stage();
            secondStage.setTitle("Choose Powerup");
            sceneChoosePowerup = new Scene(root);
            choosePowerupController = loader.getController();
            choosePowerupController.im1path = im1path;
            choosePowerupController.im2path = im2path;

        } catch (IOException e) {
            HandyFunctions.LOGGER.log(Level.SEVERE, "error initializing choose power up");
        }

    }

    /**
     * @param users to display on the view(GUI)
     */
    @Override
    public void updatePlayersOnWaitingList(List<String> users) {
        Platform.runLater(
                () ->
                        waitingLobbyController.updateLoggedPlayers(users));
    }

    @Override
    public void updateTimerLobby(int count) {
        Platform.runLater(
                () -> waitingLobbyController.updateTimer(count));
    }

    @Override
    public void updateTimerMap(int count) {
        Platform.runLater(
                () ->
                        chooseMapController.updateTimer(count));
    }

    @Override
    public void updateTimerCharacter(int count) {
        Platform.runLater(
                () ->
                        chooseCharacterController.updateTimer(count));
    }

    protected void sendMapChosenByPlayer(int config) {
        SendMapChosenMessage message = new SendMapChosenMessage(config);
        message.setSender(userName);
        viewSetChanged();
        notifyObservers(message);
    }

    protected void sendCharacterChosenByPlayer(String characterEnuminString) {
        SendCharacterChosenMessage message = new SendCharacterChosenMessage(characterEnuminString);
        message.setSender(userName);
        viewSetChanged();
        notifyObservers(message);
    }

    @Override
    public void updateVotesMapChosen(Map<Integer, Integer> map) {
        Platform.runLater(
                () ->
                        chooseMapController.updateVotes(map));
    }

    @Override
    public void updateVotesCharacterChosen(String c) {
        Platform.runLater(
                () ->
                        chooseCharacterController.updateCharacters(c));
    }

    @Override
    public void startGame() {
        //useless
    }

    @Override
    public void setCommunicationType() {
        //useless
    }

    @Override
    public void startConnection() {
        //useless
    }

    @Override
    public void setUserName() {
        //useless
    }

    @Override
    public void waitGameStart() {
        //useless
    }

    @Override
    public void lightWeapons(List<String> weapons) {
        //useless
    }

    @Override
    public void lightPlatforms(List<String> platforms) {
        //useless
    }


    @Override
    public void setState(State newState) {
        //useless
    }

    @Override
    public void update(Observable obs, Object obj) {
        /*
        we use this in RemoteView, here we got specific update methods
         */
    }

}

