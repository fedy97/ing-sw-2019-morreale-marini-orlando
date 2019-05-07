package it.polimi.se2019.view.client.gui;

import it.polimi.se2019.utils.HandyFunctions;
import it.polimi.se2019.view.client.RemoteView;
import it.polimi.se2019.view.State;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Observable;
import java.util.logging.Level;

/**
 * @author Simone Orlando
 */
public class GUI extends RemoteView {

    private WaitingLobbyController waitingLobbyController;
    private ChooseMapController chooseMapController;
    private ChooseCharacterController chooseCharacterController;

    private Scene sceneWaitingLobby;
    private Scene sceneChooseMap;
    private Scene sceneChooseCharacter;

    private Stage stage;

    public GUI(Stage stage) {
        this.stage = stage;
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

    private void initChooseCharacter() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/chooseCharacter.fxml"));
        try {
            Parent root = loader.load();
            stage.setTitle("Choose Character");
            sceneChooseCharacter = new Scene(root);
            chooseCharacterController = loader.getController();
        } catch (IOException e) {
            HandyFunctions.LOGGER.log(Level.SEVERE,"error initializing choose character");
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
            HandyFunctions.LOGGER.log(Level.SEVERE,"error initializing waiting lobby");
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
            HandyFunctions.LOGGER.log(Level.SEVERE,"error initializing choose map");
        }
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
        //TODO
    }

    @Override
    public void waitGameStart() {
        //TODO
    }

    @Override
    public void menageTurn() {
        //TODO
    }

    @Override
    public void lightWeapons(List<String> weapons) {
        //TODO
    }


    @Override
    public void setState(State newState) {
        //TODO
    }

    @Override
    public void update(Observable obs, Object obj) {
        //TODO
    }

}

