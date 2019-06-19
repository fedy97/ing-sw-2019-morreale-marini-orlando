package it.polimi.se2019.view.client.gui;

import it.polimi.se2019.model.CardRep;
import it.polimi.se2019.model.LightGameVersion;
import it.polimi.se2019.utils.HandyFunctions;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UsePowerupController {

    @FXML
    private ImageView powerup1image;
    @FXML
    private ImageView powerup2image;
    @FXML
    private ImageView powerup3image;
    @FXML
    private Button powerup1button;
    @FXML
    private Button powerup2button;
    @FXML
    private Button powerup3button;

    private GUI gui;
    private LightGameVersion lightGameVersion;
    private List<ImageView> powerupsImages;
    private List<Button> powerupsButtons;

    public void initialize() {
        powerupsImages = new ArrayList<>();
        powerupsButtons = new ArrayList<>();
        powerupsImages.add(powerup1image);
        powerupsImages.add(powerup2image);
        powerupsImages.add(powerup3image);
        powerupsButtons.add(powerup1button);
        powerupsButtons.add(powerup2button);
        powerupsButtons.add(powerup3button);
    }

    protected void passGUI(GUI gui) {
        this.gui = gui;
    }

    protected void updateMyPowerups(LightGameVersion lightGameVersion, List<String> hashes) {
        this.lightGameVersion = lightGameVersion;
        Map<String, List<CardRep>> charPowerupsReps = lightGameVersion.getPlayerPowerups();
        List<CardRep> myPowerupsReps = charPowerupsReps.get(gui.getCharInString());
        for (CardRep myPowerup : myPowerupsReps) {
            if (hashes.contains(Integer.toString(myPowerup.getId())))
                HandyFunctions.enlightenButton(powerupsButtons.get(myPowerupsReps.indexOf(myPowerup)));
            else
                HandyFunctions.darkenButton(powerupsButtons.get(myPowerupsReps.indexOf(myPowerup)));
            powerupsImages.get(myPowerupsReps.indexOf(myPowerup)).setImage(new Image(myPowerup.getPath()));
        }
        for (int j = myPowerupsReps.size(); j < 3; j++) {
            powerupsImages.get(j).setImage(new Image("/assets/powerups/AD_powerups_IT_02.jpg"));
            HandyFunctions.darkenButton(powerupsButtons.get(j));
        }
    }

    public void powerup1Click() {
        int hash = lightGameVersion.getPlayerPowerups().get(gui.getCharInString()).get(0).getId();
        gui.usePowerup(Integer.toString(hash));
    }

    public void powerup2Click() {
        int hash = lightGameVersion.getPlayerPowerups().get(gui.getCharInString()).get(1).getId();
        gui.usePowerup(Integer.toString(hash));
    }

    public void powerup3Click() {
        int hash = lightGameVersion.getPlayerPowerups().get(gui.getCharInString()).get(2).getId();
        gui.usePowerup(Integer.toString(hash));
    }
}
