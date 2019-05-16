package it.polimi.se2019.view.client.gui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class ChoosePowerupController {
    @FXML
    Button powerup1;
    @FXML
    Button powerup2;
    @FXML
    ImageView im1;
    @FXML
    ImageView im2;

    private GUI gui;
    protected String im1path, im2path;

    protected void passGUI(GUI gui) {
        this.gui = gui;
    }

    public void initialize() {
        Platform.runLater(
                () -> {
                    im1.setPreserveRatio(true);
                    im2.setPreserveRatio(true);
                    im1.setImage(new Image(im1path));
                    im2.setImage(new Image(im2path));
                    //TODO show 2 powerups
                });
    }

    public void chooseFirstPowerup() {
    }

    public void chooseSecondPowerup() {
    }
}
