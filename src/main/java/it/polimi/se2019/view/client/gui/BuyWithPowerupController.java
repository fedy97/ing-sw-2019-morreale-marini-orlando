package it.polimi.se2019.view.client.gui;

import it.polimi.se2019.model.rep.CardRep;
import it.polimi.se2019.model.rep.LightGameVersion;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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

    private GUI gui;
    private List<ImageView> powerupsImages;
    private LightGameVersion lightGameVersion;
    private List<Button> powerupsButtons;

    public void initialize() {
        powerupsImages = new ArrayList<>();
        powerupsButtons = new ArrayList<>();
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

    void updateRightPowerups(LightGameVersion lightGameVersion) {
        this.lightGameVersion = lightGameVersion;

        Map<String, List<CardRep>> charPowerupsReps = lightGameVersion.getPlayerPowerups();
        List<CardRep> myPowerupsReps = charPowerupsReps.get(gui.getCharInString());
        for (CardRep myPowerup : myPowerupsReps) {
            powerupsImages.get(myPowerupsReps.indexOf(myPowerup)).setImage(new Image(myPowerup.getPath()));
            LoginController.enlightenButton(powerupsButtons.get(myPowerupsReps.indexOf(myPowerup)));
        }
        for (int j = myPowerupsReps.size(); j < 3; j++) {
            powerupsImages.get(j).setImage(new Image("/assets/powerups/AD_powerups_IT_02.jpg"));
            LoginController.darkenButton(powerupsButtons.get(j));
        }

    }

    @FXML
    public void toggle1Click() {
        int hash = lightGameVersion.getPlayerPowerups().get(gui.getCharInString()).get(0).getId();
        gui.sendPowerupsToBuyWith(Integer.toString(hash));
    }

    @FXML
    public void toggle2Click() {
        int hash = lightGameVersion.getPlayerPowerups().get(gui.getCharInString()).get(1).getId();
        gui.sendPowerupsToBuyWith(Integer.toString(hash));
    }

    @FXML
    public void toggle3Click() {
        int hash = lightGameVersion.getPlayerPowerups().get(gui.getCharInString()).get(2).getId();
        gui.sendPowerupsToBuyWith(Integer.toString(hash));
    }
}
