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

public class StatsBoardController {

    private GUI gui;
    @FXML
    private ImageView sprogimageboard;
    @FXML
    private ImageView distructorimageboard;
    @FXML
    private ImageView bansheeimageboard;
    @FXML
    private ImageView violetimageboard;
    @FXML
    private ImageView dozerimageboard;
    @FXML
    private ImageView weapon1;
    @FXML
    private ImageView weapon2;
    @FXML
    private ImageView weapon3;
    @FXML
    private ImageView powerup1;
    @FXML
    private ImageView powerup2;
    @FXML
    private ImageView powerup3;

    private List<String> charsInGame;
    private LightGameVersion lightGameVersion;
    private Map<String, ImageView> playerImageBoard;
    private String myChar;
    private List<ImageView> weaponsImages;
    private List<ImageView> powerupsImages;

    protected void passGUI(GUI gui) {
        this.gui = gui;
        myChar = gui.getCharInString();
    }

    public void initialize() {
        Platform.runLater(
                () -> {
                    playerImageBoard = new HashMap<>();
                    playerImageBoard.put("SPROG", sprogimageboard);
                    playerImageBoard.put("BANSHEE", bansheeimageboard);
                    playerImageBoard.put("DISTRUCTOR", distructorimageboard);
                    playerImageBoard.put("VIOLET", violetimageboard);
                    playerImageBoard.put("DOZER", dozerimageboard);
                    weaponsImages = new ArrayList<>();
                    powerupsImages = new ArrayList<>();
                    weaponsImages.add(weapon1);
                    weaponsImages.add(weapon2);
                    weaponsImages.add(weapon3);
                    powerupsImages.add(powerup1);
                    powerupsImages.add(powerup2);
                    powerupsImages.add(powerup3);

                    for (String charToDisplay : charsInGame)
                        playerImageBoard.get(charToDisplay).setVisible(true);
                });

    }

    public void setCharsInGame(List<String> charsInGame) {
        this.charsInGame = charsInGame;
    }

    protected void updateAll(LightGameVersion lightGameVersion) {
        this.lightGameVersion = lightGameVersion;
        Map<String, List<CardRep>> charPowerupsReps = lightGameVersion.getPlayerPowerups();
        List<CardRep> myPowerupsReps = charPowerupsReps.get(myChar);
        for (CardRep myPowerup : myPowerupsReps)
            powerupsImages.get(myPowerupsReps.indexOf(myPowerup)).setImage(new Image(myPowerup.getPath()));

        //TODO
    }

}
