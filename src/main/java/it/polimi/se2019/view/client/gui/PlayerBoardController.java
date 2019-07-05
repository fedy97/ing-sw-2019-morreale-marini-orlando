package it.polimi.se2019.view.client.gui;

import it.polimi.se2019.model.rep.BoardRep;
import it.polimi.se2019.model.rep.CardRep;
import it.polimi.se2019.model.rep.LightGameVersion;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PlayerBoardController {

    @FXML
    private Label scorelabel;
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
    private ImageView yellowAmmo11;
    @FXML
    private ImageView yellowAmmo21;
    @FXML
    private ImageView blueAmmo11;
    @FXML
    private ImageView blueAmmo21;
    @FXML
    private ImageView redAmmo11;
    @FXML
    private ImageView redAmmo21;
    @FXML
    private ImageView redAmmo31;
    @FXML
    private ImageView yellowAmmo31;
    @FXML
    private ImageView blueAmmo31;
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
    @FXML
    private ImageView damage1;
    @FXML
    private ImageView damage2;
    @FXML
    private ImageView damage3;
    @FXML
    private ImageView damage4;
    @FXML
    private ImageView damage5;
    @FXML
    private ImageView damage6;
    @FXML
    private ImageView damage7;
    @FXML
    private ImageView damage8;
    @FXML
    private ImageView damage9;
    @FXML
    private ImageView damage10;
    @FXML
    private ImageView damage11;
    @FXML
    private ImageView damage12;
    @FXML
    private ImageView life1;
    @FXML
    private ImageView life2;
    @FXML
    private ImageView life3;
    @FXML
    private ImageView life4;
    @FXML
    private ImageView life5;
    @FXML
    private ImageView life6;
    @FXML
    private ImageView life7;
    @FXML
    private ImageView life8;
    @FXML
    private ImageView mark1;
    @FXML
    private ImageView mark2;
    @FXML
    private ImageView mark3;
    @FXML
    private ImageView mark4;
    @FXML
    private ImageView mark5;
    @FXML
    private ImageView mark6;
    @FXML
    private ImageView mark7;
    @FXML
    private ImageView mark8;
    @FXML
    private ImageView mark9;
    @FXML
    private ImageView mark10;
    @FXML
    private ImageView mark11;
    @FXML
    private ImageView mark12;

    private GUI gui;
    private String myChar;
    private LightGameVersion lightGameVersion;
    private List<ImageView> weaponsImages;
    private List<ImageView> powerupsImages;
    private List<ImageView> yellowAmmosImages;
    private List<ImageView> blueAmmosImages;
    private List<ImageView> redAmmosImages;
    private List<ImageView> damagesImages;
    private List<ImageView> livesImages;
    private List<ImageView> marksImages;
    private List<Button> infoWeaponsButtons;
    private List<Button> infoPowerupsButtons;
    private String currPlayerDisplay;

    public void initialize() {
        Platform.runLater(() -> {
            infoWeaponsButtons = new ArrayList<>();
            infoPowerupsButtons = new ArrayList<>();
            weaponsImages = new ArrayList<>();
            powerupsImages = new ArrayList<>();
            yellowAmmosImages = new ArrayList<>();
            blueAmmosImages = new ArrayList<>();
            redAmmosImages = new ArrayList<>();
            damagesImages = new ArrayList<>();
            livesImages = new ArrayList<>();
            marksImages = new ArrayList<>();
            marksImages.add(mark1);
            marksImages.add(mark2);
            marksImages.add(mark3);
            marksImages.add(mark4);
            marksImages.add(mark5);
            marksImages.add(mark6);
            marksImages.add(mark7);
            marksImages.add(mark8);
            marksImages.add(mark9);
            marksImages.add(mark10);
            marksImages.add(mark11);
            marksImages.add(mark12);
            yellowAmmosImages.add(yellowAmmo1);
            yellowAmmosImages.add(yellowAmmo2);
            yellowAmmosImages.add(yellowAmmo3);
            blueAmmosImages.add(blueAmmo1);
            blueAmmosImages.add(blueAmmo2);
            blueAmmosImages.add(blueAmmo3);
            redAmmosImages.add(redAmmo1);
            redAmmosImages.add(redAmmo2);
            redAmmosImages.add(redAmmo3);
            yellowAmmosImages.add(yellowAmmo11);
            yellowAmmosImages.add(yellowAmmo21);
            yellowAmmosImages.add(yellowAmmo31);
            blueAmmosImages.add(blueAmmo11);
            blueAmmosImages.add(blueAmmo21);
            blueAmmosImages.add(blueAmmo31);
            redAmmosImages.add(redAmmo11);
            redAmmosImages.add(redAmmo21);
            redAmmosImages.add(redAmmo31);
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
            damagesImages.add(damage1);
            damagesImages.add(damage2);
            damagesImages.add(damage3);
            damagesImages.add(damage4);
            damagesImages.add(damage5);
            damagesImages.add(damage6);
            damagesImages.add(damage7);
            damagesImages.add(damage8);
            damagesImages.add(damage9);
            damagesImages.add(damage10);
            damagesImages.add(damage11);
            damagesImages.add(damage12);
            livesImages.add(life1);
            livesImages.add(life2);
            livesImages.add(life3);
            livesImages.add(life4);
            livesImages.add(life5);
            livesImages.add(life6);
            livesImages.add(life7);
            livesImages.add(life8);

            scorelabel.setFont(gui.getGrande());
        });


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
        //updates damages
        updateDamages();
        //updates marks
        updateMarks();
        //updates player's score
        updateScore();
        //updates skulls
        updateSkulls();
    }

    private void updateSkulls() {
        BoardRep boardRep = lightGameVersion.getPlayerBoardRep().get(currPlayerDisplay);
        if (!boardRep.isReverted()) {
            for (int i = 0; i < boardRep.getSkullsNum(); i++)
                livesImages.get(i).setVisible(true);
            for (int i = boardRep.getSkullsNum(); i < 8; i++)
                livesImages.get(i).setVisible(false);
        } else {
            for (int i = 0; i < 8; i++)
                livesImages.get(i).setVisible(false);
            for (int i = 0; i < boardRep.getSkullsNum(); i++)
                livesImages.get(i + 1).setVisible(true);
            for (int i = boardRep.getSkullsNum(); i < 7; i++)
                livesImages.get(i + 1).setVisible(false);
        }

    }

    private void updateScore() {
        if (currPlayerDisplay.equals(gui.getCharInString())) {
            BoardRep boardRep = lightGameVersion.getPlayerBoardRep().get(currPlayerDisplay);
            int score = boardRep.getPoints();
            scorelabel.setText("Your score: " + score);
        } else scorelabel.setText("");
    }

    private void updateMarks() {
        BoardRep boardRep = lightGameVersion.getPlayerBoardRep().get(currPlayerDisplay);
        List<String> characterMarks = boardRep.getMarks();
        for (int i = 0; i < characterMarks.size(); i++) {
            marksImages.get(i).setVisible(true);
            marksImages.get(i).setImage(new Image("/assets/boards/1mark" + characterMarks.get(i) + ".png"));
        }
        for (int i = characterMarks.size(); i < 12; i++)
            marksImages.get(i).setVisible(false);
    }

    private void updateDamages() {
        BoardRep boardRep = lightGameVersion.getPlayerBoardRep().get(currPlayerDisplay);
        List<String> charactersDamages = boardRep.getDamages();
        for (int i = 0; i < charactersDamages.size(); i++) {
            damagesImages.get(i).setVisible(true);
            damagesImages.get(i).setImage(new Image("/assets/boards/1mark" + charactersDamages.get(i) + ".png"));
        }
        for (int i = charactersDamages.size(); i < 12; i++)
            damagesImages.get(i).setVisible(false);
    }

    private void updatePlayerAmmos() {
        Map<String, Integer> colorQuantity = lightGameVersion.getPlayerBoardRep().get(currPlayerDisplay).getColorQtyAmmos();
        int red = colorQuantity.get("RED");
        int blue = colorQuantity.get("BLUE");
        int yellow = colorQuantity.get("YELLOW");
        for (int i = 0; i < 6; i++) {
            redAmmosImages.get(i).setVisible(true);
            if (i >= red)
                redAmmosImages.get(i).setVisible(false);
        }
        for (int i = 0; i < 6; i++) {
            blueAmmosImages.get(i).setVisible(true);
            if (i >= blue)
                blueAmmosImages.get(i).setVisible(false);
        }
        for (int i = 0; i < 6; i++) {
            yellowAmmosImages.get(i).setVisible(true);
            if (i >= yellow)
                yellowAmmosImages.get(i).setVisible(false);
        }
    }

    private void updatePlayerWeapons() {
        Map<String, List<CardRep>> charWeapons = lightGameVersion.getPlayerWeapons();
        List<CardRep> myWeaponsReps = charWeapons.get(currPlayerDisplay);
        for (CardRep myWeapon : myWeaponsReps) {
            if (myWeapon.isLoaded())
                weaponsImages.get(myWeaponsReps.indexOf(myWeapon)).setImage(new Image(myWeapon.getPath()));
            LoginController.enlightenButton(infoWeaponsButtons.get(myWeaponsReps.indexOf(myWeapon)));
        }
        for (int j = myWeaponsReps.size(); j < 3; j++)
            LoginController.darkenButton(infoWeaponsButtons.get(j));
    }

    private void updatePlayerPowerUps() {
        Map<String, List<CardRep>> charPowerupsReps = lightGameVersion.getPlayerPowerups();
        List<CardRep> myPowerupsReps = charPowerupsReps.get(currPlayerDisplay);
        if (myChar.equals(currPlayerDisplay)) {
            for (CardRep myPowerup : myPowerupsReps) {
                powerupsImages.get(myPowerupsReps.indexOf(myPowerup)).setImage(new Image(myPowerup.getPath()));
                LoginController.enlightenButton(infoPowerupsButtons.get(myPowerupsReps.indexOf(myPowerup)));
            }
        } else
            for (CardRep myPowerup : myPowerupsReps)
                LoginController.darkenButton(infoPowerupsButtons.get(myPowerupsReps.indexOf(myPowerup)));

        for (int j = myPowerupsReps.size(); j < 3; j++)
            LoginController.darkenButton(infoPowerupsButtons.get(j));
    }

    private void setRightBoard() {
        for (ImageView powerUp : powerupsImages)
            powerUp.setImage(new Image("/assets/powerups/AD_powerups_IT_02.jpg"));
        for (ImageView weapon : weaponsImages)
            weapon.setImage(new Image("/assets/weapons/back.png"));
        BoardRep myBoard = lightGameVersion.getPlayerBoardRep().get(currPlayerDisplay);
        if (!myBoard.isReverted())
            boardImage.setImage(new Image("/assets/boards/" + currPlayerDisplay.toLowerCase() + ".png"));
        else
            boardImage.setImage(new Image("/assets/boards/" + currPlayerDisplay.toLowerCase() + "frenzy" + ".png"));
    }

    void passGUI(GUI gui) {
        this.gui = gui;
        myChar = gui.getCharInString();
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
