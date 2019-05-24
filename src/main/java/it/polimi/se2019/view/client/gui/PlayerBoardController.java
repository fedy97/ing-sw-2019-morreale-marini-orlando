package it.polimi.se2019.view.client.gui;

import it.polimi.se2019.model.CardRep;
import it.polimi.se2019.model.LightGameVersion;
import it.polimi.se2019.utils.HandyFunctions;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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

    private GUI gui;
    private List<String> charsInGame;
    private String myChar;
    private LightGameVersion lightGameVersion;
    private List<ImageView> weaponsImages;
    private List<ImageView> powerupsImages;
    private String currPlayerDisplay;

    public void initialize() {
        weaponsImages = new ArrayList<>();
        powerupsImages = new ArrayList<>();
        weaponsImages.add(wImage1);
        weaponsImages.add(wImage2);
        weaponsImages.add(wImage3);
        powerupsImages.add(pImage1);
        powerupsImages.add(pImage2);
        powerupsImages.add(pImage3);
    }

    void setRightAssets(String playerToDisplay) {
        this.currPlayerDisplay = playerToDisplay;
        for (ImageView powerUp : powerupsImages)
            powerUp.setImage(new Image("/assets/powerups/AD_powerups_IT_02.jpg"));
        for (ImageView weapon : weaponsImages)
            weapon.setImage(new Image("/assets/weapons/back.png"));
        boardImage.setImage(new Image("/assets/boards/" + playerToDisplay.toLowerCase() + ".png"));
        Map<String, List<CardRep>> charPowerupsReps = lightGameVersion.getPlayerPowerups();
        List<CardRep> myPowerupsReps = charPowerupsReps.get(playerToDisplay);
        if (myChar.equals(playerToDisplay)) {
            for (CardRep myPowerup : myPowerupsReps)
                powerupsImages.get(myPowerupsReps.indexOf(myPowerup)).setImage(new Image(myPowerup.getPath()));
        }
        Map<String,List<CardRep>> charWeapons = lightGameVersion.getPlayerWeapons();
        List<CardRep> myWeaponsReps = charWeapons.get(playerToDisplay);
        for (CardRep myWeapon : myWeaponsReps)
            weaponsImages.get(myWeaponsReps.indexOf(myWeapon)).setImage(new Image(myWeapon.getPath()));



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
        //setRightAssets(currPlayerDisplay);
    }

}