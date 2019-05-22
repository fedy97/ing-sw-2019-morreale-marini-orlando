package it.polimi.se2019.view.client.gui;

import it.polimi.se2019.model.AmmoRep;
import it.polimi.se2019.model.CardRep;
import it.polimi.se2019.model.LightGameVersion;
import it.polimi.se2019.network.message.to_server.*;
import it.polimi.se2019.utils.HandyFunctions;
import it.polimi.se2019.view.client.RemoteView;
import it.polimi.se2019.view.State;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.ArrayList;
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
    private StatsBoardController statsBoardController;

    private Scene sceneWaitingLobby;
    private Scene sceneChooseMap;
    private Scene sceneChooseCharacter;
    private Scene sceneGameBoard;
    private Scene sceneChoosePowerup;
    private Scene sceneStatsBoard;
    private Stage stage;
    private Stage secondStage;
    private Stage statsStage;
    private String charInString;
    private String config;

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
    public void showChoosePowerup(CardRep p1, CardRep p2) {
        Platform.runLater(
                () -> {
                    initChoosePowerup(p1, p2);
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
                    this.config = config;
                    chooseCharacterController.passGUI(this);
                    stage.setScene(sceneChooseCharacter);
                    stage.setResizable(false);
                    stage.show();
                });
    }

    @Override
    public void showGameBoard(List<AmmoRep> ammoReps,Map<String,List<CardRep>> posWeapons, List<String> arrChars) {
        Platform.runLater(
                () -> {
                    initGameBoard(config,ammoReps,posWeapons);
                    initStatsBoard(arrChars);
                    gameBoardController.passGUI(this);
                    statsBoardController.passGUI(this);
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

    private void initGameBoard(String config, List<AmmoRep> ammoReps,Map<String,List<CardRep>> posWeapons) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gameBoard.fxml"));
        try {
            Parent root = loader.load();
            stage.setTitle("Adrenaline");
            sceneGameBoard = new Scene(root);
            gameBoardController = loader.getController();
            gameBoardController.setConfig(config);
            gameBoardController.setAmmoReps(ammoReps);
            gameBoardController.setPosWeaponsReps(posWeapons);
        } catch (IOException e) {
            HandyFunctions.LOGGER.log(Level.SEVERE, "error initializing game board");
        }
    }

    protected void initStatsBoard(List<String> arrChars){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/statsBoard.fxml"));
        try {
            Parent root = loader.load();
            statsStage = new Stage();
            statsStage.setTitle("Stats");
            sceneStatsBoard = new Scene(root);
            statsBoardController = loader.getController();
            statsBoardController.setCharsInGame(arrChars);
        } catch (IOException e) {
            HandyFunctions.LOGGER.log(Level.SEVERE, "error initializing stats board");
        }
    }

    private void initChoosePowerup(CardRep p1, CardRep p2) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/choosePowerup.fxml"));
        try {
            Parent root = loader.load();
            secondStage = new Stage();
            secondStage.setTitle("Choose Powerup");
            //secondStage.initStyle(StageStyle.UNDECORATED);
            secondStage.initOwner(stage);
            secondStage.initModality(Modality.APPLICATION_MODAL);
            sceneChoosePowerup = new Scene(root);
            choosePowerupController = loader.getController();
            choosePowerupController.im1rep = p1;
            choosePowerupController.im2rep = p2;

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

    @Override
    public void updateAll(LightGameVersion lightGameVersion) {
        Platform.runLater(
                () -> {
                    gameBoardController.updateAll(lightGameVersion);
                    statsBoardController.updateAll(lightGameVersion);
                });
    }

    protected void sendMapChosenByPlayer(int config) {
        SendMapChosenMessage message = new SendMapChosenMessage(config);
        message.setSender(userName);
        viewSetChanged();
        notifyObservers(message);
    }

    protected void sendCharacterChosenByPlayer(String characterEnuminString) {
        charInString = characterEnuminString;
        SendCharacterChosenMessage message = new SendCharacterChosenMessage(characterEnuminString);
        message.setSender(userName);
        viewSetChanged();
        notifyObservers(message);
    }

    protected void sendPowerupChosen(int hashCodeChosen, int hashCodeGarbage) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(hashCodeChosen);
        arrayList.add(hashCodeGarbage);
        SendInitPowerUpMessage message = new SendInitPowerUpMessage(arrayList);
        message.setSender(userName);
        viewSetChanged();
        notifyObservers(message);
    }
    protected void sendPlatformChosen(String pos){
        MoveCurrPlayerMessage message = new MoveCurrPlayerMessage(pos);
        message.setSender(userName);
        viewSetChanged();
        notifyObservers(message);
        gameBoardController.enableActionButtons();
    }

    protected void iWantToDoSomething(String action){
        gameBoardController.disableActionButtons();
        PerformActionMessage message = new PerformActionMessage(action);
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
        Platform.runLater(
                () -> gameBoardController.enlightenPlatforms(platforms));
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

    @Override
    public void setRandomChar(String randomChar) {
        charInString = randomChar;
    }

    public String getCharInString() {
        return charInString;
    }

    public Stage getStatsStage() {
        return statsStage;
    }

    public Scene getSceneStatsBoard() {
        return sceneStatsBoard;
    }
}

