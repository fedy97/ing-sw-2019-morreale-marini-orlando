package it.polimi.se2019.view.client.gui;

import it.polimi.se2019.model.CardRep;
import it.polimi.se2019.model.LightGameVersion;
import it.polimi.se2019.utils.HandyFunctions;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayerBoardController {

    @FXML
    private ImageView boardImage;
    @FXML
    private ImageView wImage1;
    @FXML
    private ImageView wImage2;
    @FXML
    private ImageView wImage3;
    @FXML
    private ImageView pImage1;
    @FXML
    private ImageView pImage2;
    @FXML
    private ImageView pImage3;
    @FXML
    private ImageView yellowAmmo1;
    @FXML
    private ImageView yellowAmmo2;
    @FXML
    private ImageView blueAmmo1;
    @FXML
    private ImageView blueAmmo2;
    @FXML
    private ImageView redAmmo1;
    @FXML
    private ImageView redAmmo2;
    @FXML
    private ImageView redAmmo3;
    @FXML
    private ImageView yellowAmmo3;
    @FXML
    private ImageView blueAmmo3;
    @FXML
    private Button infoW1;
    @FXML
    private Button infoW2;
    @FXML
    private Button infoW3;
    @FXML
    private Button infoP1;
    @FXML
    private Button infoP2;
    @FXML
    private Button infoP3;

    private GUI gui;
    private List<String> charsInGame;
    private String myChar;
    private LightGameVersion lightGameVersion;
    private List<ImageView> weaponsImages;
    private List<ImageView> powerupsImages;
    private List<ImageView> yellowAmmosImages;
    private List<ImageView> blueAmmosImages;
    private List<ImageView> redAmmosImages;
    private List<Button> infoWeaponsButtons;
    private List<Button> infoPowerupsButtons;
    private String currPlayerDisplay;

    public void initialize() {
        infoWeaponsButtons = new ArrayList<>();
        infoPowerupsButtons = new ArrayList<>();
        weaponsImages = new ArrayList<>();
        powerupsImages = new ArrayList<>();
        yellowAmmosImages = new ArrayList<>();
        blueAmmosImages = new ArrayList<>();
        redAmmosImages = new ArrayList<>();
        yellowAmmosImages.add(yellowAmmo1);
        yellowAmmosImages.add(yellowAmmo2);
        yellowAmmosImages.add(yellowAmmo3);
        blueAmmosImages.add(blueAmmo1);
        blueAmmosImages.add(blueAmmo2);
        blueAmmosImages.add(blueAmmo3);
        redAmmosImages.add(redAmmo1);
        redAmmosImages.add(redAmmo2);
        redAmmosImages.add(redAmmo3);
        weaponsImages.add(wImage1);
        weaponsImages.add(wImage2);
        weaponsImages.add(wImage3);
        powerupsImages.add(pImage1);
        powerupsImages.add(pImage2);
        powerupsImages.add(pImage3);
        infoWeaponsButtons.add(infoW1);
        infoWeaponsButtons.add(infoW2);
        infoWeaponsButtons.add(infoW3);
        infoPowerupsButtons.add(infoP1);
        infoPowerupsButtons.add(infoP2);
        infoPowerupsButtons.add(infoP3);
    }

    void setRightAssets(String playerToDisplay) {
        this.currPlayerDisplay = playerToDisplay;
        //set right board
        setRightBoard();
        //update powerups
        updatePlayerPowerUps();
        //update weapons
        updatePlayerWeapons();
        //update ammos
        updatePlayerAmmos();
    }

    private void updatePlayerAmmos() {
        Map<String, Integer> colorQuantity = lightGameVersion.getPlayerBoardRep().get(currPlayerDisplay).getColorQtyAmmos();
        int red = colorQuantity.get("RED");
        int blue = colorQuantity.get("BLUE");
        int yellow = colorQuantity.get("YELLOW");
        for (int i = 0; i < 3; i++) {
            redAmmosImages.get(i).setVisible(true);
            if (i >= red)
                redAmmosImages.get(i).setVisible(false);
        }
        for (int i = 0; i < 3; i++) {
            blueAmmosImages.get(i).setVisible(true);
            if (i >= blue)
                blueAmmosImages.get(i).setVisible(false);
        }
        for (int i = 0; i < 3; i++) {
            yellowAmmosImages.get(i).setVisible(true);
            if (i >= yellow)
                yellowAmmosImages.get(i).setVisible(false);
        }
    }

    private void updatePlayerWeapons() {
        Map<String, List<CardRep>> charWeapons = lightGameVersion.getPlayerWeapons();
        List<CardRep> myWeaponsReps = charWeapons.get(currPlayerDisplay);
        for (CardRep myWeapon : myWeaponsReps) {
            weaponsImages.get(myWeaponsReps.indexOf(myWeapon)).setImage(new Image(myWeapon.getPath()));
            HandyFunctions.enlightenButton(infoWeaponsButtons.get(myWeaponsReps.indexOf(myWeapon)));
        }
        for (int j = myWeaponsReps.size(); j < 3; j++)
            HandyFunctions.darkenButton(infoWeaponsButtons.get(j));
    }

    private void updatePlayerPowerUps() {
        Map<String, List<CardRep>> charPowerupsReps = lightGameVersion.getPlayerPowerups();
        List<CardRep> myPowerupsReps = charPowerupsReps.get(currPlayerDisplay);
        if (myChar.equals(currPlayerDisplay)) {
            for (CardRep myPowerup : myPowerupsReps) {
                powerupsImages.get(myPowerupsReps.indexOf(myPowerup)).setImage(new Image(myPowerup.getPath()));
                HandyFunctions.enlightenButton(infoPowerupsButtons.get(myPowerupsReps.indexOf(myPowerup)));
            }
        } else
            for (CardRep myPowerup : myPowerupsReps)
                HandyFunctions.darkenButton(infoPowerupsButtons.get(myPowerupsReps.indexOf(myPowerup)));

        for (int j = myPowerupsReps.size(); j < 3; j++)
            HandyFunctions.darkenButton(infoPowerupsButtons.get(j));
    }

    private void setRightBoard() {
        for (ImageView powerUp : powerupsImages)
            powerUp.setImage(new Image("/assets/powerups/AD_powerups_IT_02.jpg"));
        for (ImageView weapon : weaponsImages)
            weapon.setImage(new Image("/assets/weapons/back.png"));
        boardImage.setImage(new Image("/assets/boards/" + currPlayerDisplay.toLowerCase() + ".png"));
    }

    void passGUI(GUI gui) {
        this.gui = gui;
        myChar = gui.getCharInString();
    }

    void setCharsInGame(List<String> charsInGame) {
        this.charsInGame = charsInGame;
    }

    void updateAll(LightGameVersion lightGameVersion) {
        this.lightGameVersion = lightGameVersion;
        if (currPlayerDisplay != null)
            setRightAssets(currPlayerDisplay);
    }

    public void setCurrPlayerDisplay(String currPlayerDisplay) {
        this.currPlayerDisplay = currPlayerDisplay;
    }

    public void infoW1Click() {
        CardRep cardRep = lightGameVersion.getPlayerWeapons().get(currPlayerDisplay).get(0);
        showInstruction(cardRep);
    }

    public void infoW2Click() {
        CardRep cardRep = lightGameVersion.getPlayerWeapons().get(currPlayerDisplay).get(1);
        showInstruction(cardRep);
    }

    public void infoW3Click() {
        CardRep cardRep = lightGameVersion.getPlayerWeapons().get(currPlayerDisplay).get(2);
        showInstruction(cardRep);
    }

    public void infoP1Click() {
        CardRep cardRep = lightGameVersion.getPlayerPowerups().get(currPlayerDisplay).get(0);
        showInstruction(cardRep);
    }

    public void infoP2Click() {
        CardRep cardRep = lightGameVersion.getPlayerPowerups().get(currPlayerDisplay).get(1);
        showInstruction(cardRep);
    }

    public void infoP3Click() {
        CardRep cardRep = lightGameVersion.getPlayerPowerups().get(currPlayerDisplay).get(2);
        showInstruction(cardRep);
    }

    private void showInstruction(CardRep cardRep) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Info");
        alert.setHeaderText(cardRep.getTitle().toUpperCase());
        alert.setContentText(cardRep.getDescription());
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.showAndWait();
    }
}
