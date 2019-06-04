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
    private List<ToggleButton> powerupsButtons;

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

    protected void updateRightPowerups(LightGameVersion lightGameVersion, List<String> hashPowerups) {


    }
    @FXML
    public void sendClick(){

    }
}
