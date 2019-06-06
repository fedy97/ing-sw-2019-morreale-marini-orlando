package it.polimi.se2019.view.client.gui;

import it.polimi.se2019.model.CardRep;
import it.polimi.se2019.model.LightGameVersion;
import it.polimi.se2019.utils.HandyFunctions;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BuyWithPowerupController {

    @FXML
    private ImageView powerup1image;
    @FXML
    private ImageView powerup2image;
    @FXML
    private ImageView powerup3image;
    @FXML
    private Button toggle1;
    @FXML
    private Button toggle2;
    @FXML
    private Button toggle3;
    @FXML
    private Button sendbutton;

    private GUI gui;
    private List<ImageView> powerupsImages;
    private LightGameVersion lightGameVersion;
    private List<Button> powerupsButtons;
    private List<String> hashesToSend;

    public void initialize() {
        powerupsImages = new ArrayList<>();
        powerupsButtons = new ArrayList<>();
        hashesToSend = new ArrayList<>();
        powerupsImages.add(powerup1image);
        powerupsImages.add(powerup2image);
        powerupsImages.add(powerup3image);
        powerupsButtons.add(toggle1);
        powerupsButtons.add(toggle2);
        powerupsButtons.add(toggle3);
    }

    protected void passGUI(GUI gui) {
        this.gui = gui;
    }

    protected void updateRightPowerups(LightGameVersion lightGameVersion, List<String> hashPowerups) {
        this.lightGameVersion = lightGameVersion;

        Map<String, List<CardRep>> charPowerupsReps = lightGameVersion.getPlayerPowerups();
        List<CardRep> myPowerupsReps = charPowerupsReps.get(gui.getCharInString());
        for (CardRep myPowerup : myPowerupsReps) {
            powerupsImages.get(myPowerupsReps.indexOf(myPowerup)).setImage(new Image(myPowerup.getPath()));
            HandyFunctions.enlightenButton(powerupsButtons.get(myPowerupsReps.indexOf(myPowerup)));
        }
        for (int j = myPowerupsReps.size(); j < 3; j++) {
            powerupsImages.get(j).setImage(new Image("/assets/powerups/AD_powerups_IT_02.jpg"));
            HandyFunctions.darkenButton(powerupsButtons.get(j));
        }

    }

    @FXML
    public void toggle1Click() {

    }

    @FXML
    public void toggle2Click() {
    }

    @FXML
    public void toggle3Click() {
    }
}
