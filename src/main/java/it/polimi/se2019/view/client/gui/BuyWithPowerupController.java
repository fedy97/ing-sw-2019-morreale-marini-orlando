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
    private ToggleButton toggle1;
    @FXML
    private ToggleButton toggle2;
    @FXML
    private ToggleButton toggle3;
    @FXML
    private Button sendbutton;

    private GUI gui;
    private List<ImageView> powerupsImages;
    private LightGameVersion lightGameVersion;
    private List<ToggleButton> powerupsButtons;
    private Map<String, Boolean> hashes;
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

    protected void updateRightPowerups(LightGameVersion lightGameVersion, Map<String, Boolean> hashPowerups) {
        this.lightGameVersion = lightGameVersion;
        this.hashes = hashPowerups;
        for (ToggleButton toggleButton : powerupsButtons)
            powerupsImages.get(powerupsButtons.indexOf(toggleButton)).setImage(new Image(""));
        List<CardRep> allMyPowerups = lightGameVersion.getPlayerPowerups().get(gui.getCharInString());
        int n = 0;
        for (String hash : hashPowerups.keySet()) {
            for (CardRep cardRep : allMyPowerups) {
                if (cardRep.getId() == Integer.parseInt(hash)) {
                    powerupsImages.get(n).setImage(new Image(cardRep.getPath()));
                    powerupsButtons.get(n).setDisable(false);
                    n++;
                    break;
                }
            }
        }

    }

    @FXML
    public void sendClick() {
        //gui.sendPowerupsToBuyWith(hashesToSend);
    }

    @FXML
    public void toggle1Click() {
        if (toggle1.isSelected()) {
            HandyFunctions.enlightenToggleButton(toggle1);
            //hashesToSend.add(hashes.g);
        } else {
            HandyFunctions.darkenToggleButton(toggle1);
            hashesToSend.remove(hashes.get(0));
        }
    }

    @FXML
    public void toggle2Click() {
    }

    @FXML
    public void toggle3Click() {
    }
}
