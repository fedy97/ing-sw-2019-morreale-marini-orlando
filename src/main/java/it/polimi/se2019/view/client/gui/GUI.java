package it.polimi.se2019.view.client.gui;

import it.polimi.se2019.model.rep.AmmoRep;
import it.polimi.se2019.model.rep.CardRep;
import it.polimi.se2019.model.rep.LightGameVersion;
import it.polimi.se2019.network.message.toserver.*;
import it.polimi.se2019.utils.CustomLogger;
import it.polimi.se2019.utils.TimerTurn;
import it.polimi.se2019.view.State;
import it.polimi.se2019.view.client.RemoteView;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.logging.Level;

import static it.polimi.se2019.utils.HandyFunctions.LOGGER;

public class GUI extends RemoteView {

    private Font normale;
    private Font grande;
    private transient WaitingLobbyController waitingLobbyController;
    private transient ChooseMapController chooseMapController;
    private transient ChooseCharacterController chooseCharacterController;
    private transient GameBoardController gameBoardController;
    private transient ChoosePowerupController choosePowerupController;
    private transient PlayerBoardController playerBoardController;
    private transient SwitchWeaponController switchWeaponController;
    private transient UsePowerupController usePowerupController;
    private transient UseWeaponController useWeaponController;
    private transient BuyWithPowerupController buyWithPowerupController;
    private transient ChooseTargetsController chooseTargetsController;
    private transient ReloadWeaponsController reloadWeaponsController;
    private transient ChooseAmmosController chooseAmmosController;
    private transient ScoreBoardController scoreBoardController;
    private transient LoginController loginController;

    private Scene sceneLogin;
    private Scene sceneWaitingLobby;
    private Scene sceneChooseMap;
    private Scene sceneChooseCharacter;
    private Scene sceneGameBoard;
    private Scene sceneChoosePowerup;
    private Scene scenePlayerBoard;
    private Scene sceneSwitchWeapon;
    private Scene sceneUsePowerup;
    private Scene sceneUseWeapon;
    private Scene sceneBuyWithPowerups;
    private Scene sceneChooseTargets;
    private Scene sceneReloadWeapons;
    private Scene sceneChooseAmmos;
    private Scene sceneScoreboard;

    private Stage stage;
    private Stage secondStage;
    private Stage playerBoardStage;
    private Stage switchWeaponStage;
    private Stage usePowerupStage;
    private Stage useWeaponStage;
    private Stage buyWithPowerupsStage;
    private Stage chooseTargetsStage;
    private Stage reloadWeaponsStage;
    private Stage chooseAmmosStage;
    private Stage scoreBoardStage;

    private String charInString;
    private List<String> charsInGame;
    private String config;
    private LightGameVersion lightGameVersion;
    private transient TimerTurn timerTurn;

    public GUI(LoginController loginController, String user, Stage stage, Scene login, Font normale, Font grande) {
        this.loginController = loginController;
        this.normale = normale;
        this.grande = grande;
        this.stage = stage;
        this.userName = user;
        sceneLogin = login;
    }

    @Override
    public void start() {
        initWaitingLobby();
        initChooseMap();
        initChooseCharacter();
        initChoosePowerup();
        showWaitingLobby();
    }

    private void showWaitingLobby() {
        stage.setTitle("Waiting Lobby");
        stage.setScene(sceneWaitingLobby);
        stage.setResizable(false);
        stage.show();
    }

    @Override
    public void showChoosePowerup(List<CardRep> cards) {
        choosePowerupController.passGUI(this);
        Platform.runLater(
                () -> {
                    choosePowerupController.updateRightPowerups(cards);
                    secondStage.setScene(sceneChoosePowerup);
                    secondStage.setResizable(false);
                    secondStage.show();
                });
    }

    @Override
    public void showChooseMap() {
        Platform.runLater(
                () -> {
                    stage.setTitle("Choose Map");
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
                    stage.setTitle("Choose Character");
                    this.config = config;
                    chooseCharacterController.passGUI(this);
                    stage.setScene(sceneChooseCharacter);
                    stage.setResizable(false);
                    stage.show();
                });
    }

    @Override
    public void showGameBoard(List<AmmoRep> ammoReps, Map<String, List<CardRep>> posWeapons, List<String> arrChars) {
        Platform.runLater(
                () -> {
                    initGameBoard(config, ammoReps, posWeapons);
                    this.charsInGame = arrChars;
                    initPlayerBoard();
                    initSwitchWeapon();
                    initUsePowerup();
                    initUseWeapon();
                    initBuyWithPowerup();
                    initChooseTargets();
                    initReloadWeapons();
                    initChooseAmmos();
                    initScoreboard();
                    scoreBoardController.passGUI(this);
                    chooseAmmosController.passGUI(this);
                    reloadWeaponsController.passGUI(this);
                    chooseTargetsController.passGUI(this);
                    buyWithPowerupController.passGUI(this);
                    switchWeaponController.passGUI(this);
                    usePowerupController.passGUI(this);
                    useWeaponController.passGUI(this);
                    gameBoardController.passGUI(this);
                    playerBoardController.passGUI(this);
                    stage.setScene(sceneGameBoard);
                    stage.setResizable(false);
                    stage.show();
                });
    }

    private void initUseWeapon() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/useWeapons.fxml"));
        try {
            Parent root = loader.load();
            useWeaponStage = new Stage();
            useWeaponStage.setTitle("Choose a weapon to use");
            sceneUseWeapon = new Scene(root);
            useWeaponController = loader.getController();
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "error initializing use weapon");
        }
    }

    private void initUsePowerup() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/usePowerups.fxml"));
        try {
            Parent root = loader.load();
            usePowerupStage = new Stage();
            usePowerupStage.setTitle("Choose a powerup to use");
            usePowerupStage.initOwner(stage);
            usePowerupStage.initModality(Modality.APPLICATION_MODAL);
            sceneUsePowerup = new Scene(root);
            usePowerupController = loader.getController();
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "error initializing use powerup");
        }
    }

    private void initChooseTargets() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/chooseTargets.fxml"));
        try {
            Parent root = loader.load();
            chooseTargetsStage = new Stage();
            chooseTargetsStage.setTitle("Choose some targets");
            sceneChooseTargets = new Scene(root);
            chooseTargetsController = loader.getController();
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "error initializing choose targets");
        }
    }

    private void initScoreboard() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/scoreBoard.fxml"));
        try {
            Parent root = loader.load();
            scoreBoardStage = new Stage();
            scoreBoardStage.setTitle("Final Scores");
            sceneScoreboard = new Scene(root);
            scoreBoardController = loader.getController();
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "error initializing score board");
        }
    }

    private void initChooseAmmos() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/chooseAmmos.fxml"));
        try {
            Parent root = loader.load();
            chooseAmmosStage = new Stage();
            chooseAmmosStage.setTitle("Choose one ammo to pay with");
            sceneChooseAmmos = new Scene(root);
            chooseAmmosController = loader.getController();
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "error initializing choose ammos");
        }
    }

    private void initBuyWithPowerup() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/buyWithPowerups.fxml"));
        try {
            Parent root = loader.load();
            buyWithPowerupsStage = new Stage();
            buyWithPowerupsStage.setTitle("Choose powerups to buy with");
            buyWithPowerupsStage.initOwner(stage);
            buyWithPowerupsStage.initModality(Modality.APPLICATION_MODAL);
            sceneBuyWithPowerups = new Scene(root);
            buyWithPowerupController = loader.getController();
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "error initializing buy with powerups");
        }
    }

    private void initSwitchWeapon() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/switchWeapon.fxml"));
        try {
            Parent root = loader.load();
            switchWeaponStage = new Stage();
            switchWeaponStage.setTitle("Choose a weapon to switch");
            switchWeaponStage.initOwner(stage);
            switchWeaponStage.initModality(Modality.APPLICATION_MODAL);
            sceneSwitchWeapon = new Scene(root);
            switchWeaponController = loader.getController();
            switchWeaponStage.setOnCloseRequest(event -> sendWeaponToSwitch("null"));
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "error initializing switch weapon");
        }
    }

    private void initReloadWeapons() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/reloadWeapons.fxml"));
        try {
            Parent root = loader.load();
            reloadWeaponsStage = new Stage();
            reloadWeaponsStage.setTitle("Choose a weapon to reload");
            reloadWeaponsStage.initOwner(stage);
            reloadWeaponsStage.initModality(Modality.APPLICATION_MODAL);
            sceneReloadWeapons = new Scene(root);
            reloadWeaponsController = loader.getController();
            reloadWeaponsStage.setOnCloseRequest(event -> sendWeaponToReload("null"));
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "error initializing reload weapon");
        }
    }

    private void initPlayerBoard() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/playerBoard.fxml"));
        try {
            Parent root = loader.load();
            playerBoardStage = new Stage();
            playerBoardStage.setTitle("Player Board");
            playerBoardStage.initOwner(stage);
            playerBoardStage.initModality(Modality.APPLICATION_MODAL);
            scenePlayerBoard = new Scene(root);
            playerBoardController = loader.getController();
            playerBoardStage.setOnCloseRequest(event -> playerBoardController.setCurrPlayerDisplay(null));
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "error initializing player board");
        }
    }

    private void initChooseCharacter() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/chooseCharacter.fxml"));
        try {
            Parent root = loader.load();
            stage.setTitle("Choose Character");
            sceneChooseCharacter = new Scene(root);
            chooseCharacterController = loader.getController();
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "error initializing choose character");
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
            LOGGER.log(Level.SEVERE, "error initializing waiting lobby");
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
            LOGGER.log(Level.SEVERE, "error initializing choose map");
        }
    }

    private void initGameBoardReconnected(String config) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gameBoard.fxml"));
        try {
            Parent root = loader.load();
            stage.setTitle("Adrenaline");
            sceneGameBoard = new Scene(root);
            gameBoardController = loader.getController();
            this.config = config;
            gameBoardController.setConfig(config);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "error initializing reconnected game board");
        }
    }

    private void initGameBoard(String config, List<AmmoRep> ammoReps, Map<String, List<CardRep>> posWeapons) {
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
            LOGGER.log(Level.SEVERE, "error initializing game board");
        }
    }

    private void initChoosePowerup() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/choosePowerup.fxml"));
        try {
            Parent root = loader.load();
            secondStage = new Stage();
            secondStage.setTitle("Choose Powerup");
            secondStage.initOwner(stage);
            secondStage.initModality(Modality.APPLICATION_MODAL);
            sceneChoosePowerup = new Scene(root);
            choosePowerupController = loader.getController();
            secondStage.setOnCloseRequest(event -> Platform.runLater(
                    () -> secondStage.show()
            ));

        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "error initializing choose power up");
            CustomLogger.logException(this.getClass().getName(), e);
        }

    }

    /**
     * @param users to display on the view(GUI)
     */
    @Override
    public void updatePlayersOnWaitingList(List<String> users) {
        Platform.runLater(
                () -> waitingLobbyController.updateLoggedPlayers(users));
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
                    this.lightGameVersion = lightGameVersion;
                    gameBoardController.updateAll(lightGameVersion);
                    playerBoardController.updateAll(lightGameVersion);
                });
    }

    protected void sendMapChosenByPlayer(int config) {
        SendMapChosenMessage message = new SendMapChosenMessage(config);
        notifyController(message);
    }

    protected void sendCharacterChosenByPlayer(String characterEnuminString) {
        charInString = characterEnuminString;
        SendCharacterChosenMessage message = new SendCharacterChosenMessage(characterEnuminString);
        notifyController(message);
    }

    protected void sendInitPowerupChosen(int hashCodeChosen, int hashCodeGarbage) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(hashCodeChosen);
        arrayList.add(hashCodeGarbage);
        SendInitPowerUpMessage message = new SendInitPowerUpMessage(arrayList);
        notifyController(message);
    }

    protected void sendPlatformChosen(String pos) {
        MoveCurrPlayerMessage message = new MoveCurrPlayerMessage(pos);
        notifyController(message);
    }

    protected void sendWeaponGrabbed(String hashWeapon) {
        ChosenWeaponMessage message = new ChosenWeaponMessage(hashWeapon);
        notifyController(message);
    }

    protected void sendWeaponToSwitch(String hashWeapon) {
        DiscardWeaponMessage message = new DiscardWeaponMessage(hashWeapon);
        notifyController(message);
        switchWeaponStage.close();
    }

    protected void sendPowerupsToBuyWith(String hash) {
        BuyWithPowerupsMessage message = new BuyWithPowerupsMessage(hash);
        notifyController(message);
        buyWithPowerupsStage.close();
    }

    protected void sendTargets(List<String> targets) {
        SendTargetsMessage message = new SendTargetsMessage(targets);
        notifyController(message);
        chooseTargetsStage.close();
    }

    protected void sendEndMyTurn() {
        EndTurnMessage message = new EndTurnMessage(null);
        notifyController(message);
    }

    protected void sendAmmo(String ammo) {
        ChosenAmmoMessage message = new ChosenAmmoMessage(ammo);
        notifyController(message);
        chooseAmmosStage.close();
    }

    protected void sendBinaryAnswer(boolean answer) {
        ResponseToBinaryOption message = new ResponseToBinaryOption(answer);
        notifyController(message);
    }

    protected void sendPowerupToDiscardThenSpawn(int hash) {
        List<Integer> hashes = new ArrayList<>();
        hashes.add(hash);
        hashes.add(hash);
        SendInitPowerUpMessage message = new SendInitPowerUpMessage(hashes);
        notifyController(message);
    }

    protected void iWantToDoSomething(String action) {
        PerformActionMessage message = new PerformActionMessage(action);
        notifyController(message);
    }

    protected void usePowerup(String hashPowerup) {
        usePowerupStage.close();
        ActivateCardMessage message = new ActivateCardMessage(hashPowerup);
        notifyController(message);
    }

    protected void useWeapon(String hashWeapon) {
        ActivateCardMessage message = new ActivateCardMessage(hashWeapon);
        notifyController(message);
    }

    protected void sendEffectChosen(int effect) {
        ChosenEffectMessage message = new ChosenEffectMessage(effect);
        notifyController(message);
        useWeaponStage.close();
    }

    protected void sendWeaponToReload(String hash) {
        ReloadWeaponsMessage message = new ReloadWeaponsMessage(hash);
        notifyController(message);
        reloadWeaponsStage.close();
    }

    @Override
    public void enlightenEffects(List<Integer> effects) {
        Platform.runLater(
                () ->
                        useWeaponController.enlightenRightEffects(effects));
    }

    @Override
    public void showReconnectedGameBoard(int config, LightGameVersion lightGameVersion, List<String> charsInGame, String myChar) {
        Platform.runLater(
                () -> {
                    this.charsInGame = charsInGame;
                    this.charInString = myChar;
                    initGameBoardReconnected(Integer.toString(config));
                    initChoosePowerup();
                    initPlayerBoard();
                    initSwitchWeapon();
                    initUsePowerup();
                    initUseWeapon();
                    initBuyWithPowerup();
                    initChooseTargets();
                    initReloadWeapons();
                    initChooseAmmos();
                    initScoreboard();
                    scoreBoardController.passGUI(this);
                    choosePowerupController.passGUI(this);
                    chooseAmmosController.passGUI(this);
                    reloadWeaponsController.passGUI(this);
                    chooseTargetsController.passGUI(this);
                    buyWithPowerupController.passGUI(this);
                    switchWeaponController.passGUI(this);
                    usePowerupController.passGUI(this);
                    useWeaponController.passGUI(this);
                    gameBoardController.passGUI(this);
                    playerBoardController.passGUI(this);
                    gameBoardController.setReconnected(true);
                    gameBoardController.setLightGameVersion(lightGameVersion);
                    updateAll(lightGameVersion);
                    stage.setScene(sceneGameBoard);
                    stage.setResizable(false);
                    stage.show();
                });

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
        Platform.runLater(
                () -> gameBoardController.enlightenWeapons(weapons));
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
    /*
        if the player has 0 actions left, can only reload
     */

    @Override
    public void setValidActions(boolean[] actives) {
        Platform.runLater(() -> gameBoardController.setActiveButtons(actives));
    }

    @Override
    public void showMessage(String msg) {
        Platform.runLater(() -> gameBoardController.showMessage(msg));
    }

    @Override
    public void switchWeapon() {
        Platform.runLater(
                () -> {
                    switchWeaponController.updateMyWeapons(lightGameVersion);
                    switchWeaponStage.setScene(sceneSwitchWeapon);
                    switchWeaponStage.setResizable(false);
                    switchWeaponStage.show();
                });
    }

    @Override
    public void showReloadableWeapons(List<String> weapons) {
        Platform.runLater(
                () -> {
                    reloadWeaponsController.updateMyWeapons(lightGameVersion, weapons);
                    reloadWeaponsStage.setScene(sceneReloadWeapons);
                    reloadWeaponsStage.setResizable(false);
                    reloadWeaponsStage.show();
                });
    }

    @Override
    public void showUsableWeapons(List<String> weapons) {
        Platform.runLater(
                () -> {
                    getUseWeaponStage().setScene(getSceneUseWeapon());
                    getUseWeaponStage().setResizable(false);
                    getUseWeaponController().updateMyWeapons(lightGameVersion, weapons);
                    getUseWeaponStage().show();
                });
    }

    @Override
    public void showUsablePowerups(List<String> powerups) {
        Platform.runLater(
                () -> {
                    getUsePowerupStage().setScene(getSceneUsePowerup());
                    getUsePowerupStage().setResizable(false);
                    getUsePowerupController().updateMyPowerups(lightGameVersion, powerups);
                    getUsePowerupStage().show();
                });
    }

    @Override
    public void showTargets(List<String> targets) {
        Platform.runLater(
                () -> {
                    chooseTargetsController.enlightenRightTargets(targets);
                    chooseTargetsStage.setScene(sceneChooseTargets);
                    chooseTargetsStage.setResizable(false);
                    chooseTargetsStage.show();
                });
    }

    @Override
    public void showBinaryOption(String message) {
        Platform.runLater(() -> gameBoardController.showBinaryMessage(message));
    }

    @Override
    public void startTimerTurn(int count, String currToDisconnect) {
        try {
            if (timerTurn != null) timerTurn.interrupt();
        } catch (Exception ex) {
            CustomLogger.logException(this.getClass().getName(), ex);
        }
        timerTurn = new TimerTurn(count, this, currToDisconnect);
        timerTurn.start();
    }

    @Override
    public void updateTimerTurn(int seconds, String curr) {
        if (seconds == 0 && curr.equals(getUserName())) {
            timerTurn.interrupt();
            System.exit(0);
        }
        Platform.runLater(() ->
                gameBoardController.updateTurnTimer(seconds));
    }

    @Override
    public void resetTimer() {
        Platform.runLater(() ->
                waitingLobbyController.resetTimer());
    }

    @Override
    public void showAmmoToDiscard() {
        Platform.runLater(
                () -> {
                    chooseAmmosStage.setScene(sceneChooseAmmos);
                    chooseAmmosStage.setResizable(false);
                    chooseAmmosController.updateRightAmmos(lightGameVersion);
                    chooseAmmosStage.show();
                });
    }

    @Override
    public void showUsernameAlreadyInUse(String user) {
        Platform.runLater(() -> {
            loginController.notifyAlreadyInUse(user);
            stage.setScene(sceneLogin);
            stage.show();
        });
    }

    @Override
    public void showScoreboard(Map<String, Integer> scoreboard) {
        Platform.runLater(
                () -> {
                    scoreBoardStage.setScene(sceneScoreboard);
                    scoreBoardStage.setResizable(false);
                    scoreBoardController.showScores(scoreboard);
                    scoreBoardStage.show();
                    stage.close();
                });
    }

    public String getCharInString() {
        return charInString;
    }

    public Scene getScenePlayerBoard() {
        return scenePlayerBoard;
    }

    public Stage getPlayerBoardStage() {
        return playerBoardStage;
    }

    public PlayerBoardController getPlayerBoardController() {
        return playerBoardController;
    }

    public List<String> getCharsInGame() {
        return charsInGame;
    }

    public Scene getSceneUsePowerup() {
        return sceneUsePowerup;
    }

    public Stage getUsePowerupStage() {
        return usePowerupStage;
    }

    public UsePowerupController getUsePowerupController() {
        return usePowerupController;
    }

    public UseWeaponController getUseWeaponController() {
        return useWeaponController;
    }

    public Scene getSceneUseWeapon() {
        return sceneUseWeapon;
    }

    public Stage getUseWeaponStage() {
        return useWeaponStage;
    }

    public Scene getSceneBuyWithPowerups() {
        return sceneBuyWithPowerups;
    }

    public Stage getBuyWithPowerupsStage() {
        return buyWithPowerupsStage;
    }

    public BuyWithPowerupController getBuyWithPowerupController() {
        return buyWithPowerupController;
    }

    public Font getNormale() {
        return normale;
    }

    public Font getGrande() {
        return grande;
    }

    @Override
    public void showActionMenu() {
        // FOR CLI.
    }

}

