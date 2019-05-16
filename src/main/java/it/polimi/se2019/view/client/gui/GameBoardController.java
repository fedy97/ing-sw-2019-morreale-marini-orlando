package it.polimi.se2019.view.client.gui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GameBoardController {

    private GUI gui;
    private String config;
    @FXML
    ImageView mapImage;
    @FXML
    Button zerozero;
    @FXML
    Button zeroone;
    @FXML
    Button zerotwo;
    @FXML
    Button zerothree;
    @FXML
    Button onezero;
    @FXML
    Button oneone;
    @FXML
    Button onetwo;
    @FXML
    Button onethree;
    @FXML
    Button twozero;
    @FXML
    Button twoone;
    @FXML
    Button twotwo;
    @FXML
    Button twothree;

    protected void passGUI(GUI gui) {
        this.gui = gui;
    }

    public void initialize() {
        Platform.runLater(
                () ->
                        mapImage.setImage(new Image("/assets/map/" + config + ".jpg")));
    }

    public void setConfig(String config) {
        this.config = config;
    }

    public void zerozeroClick() {
    }

    public void zerooneClick() {
    }

    public void zerotwoClick() {
    }

    public void zerothreeClick() {
    }

    public void onezeroClick() {
    }

    public void oneoneClick() {
    }

    public void onetwoClick() {
    }

    public void onethreeClick() {
    }

    public void twozeroClick() {
    }

    public void twooneClick() {
    }

    public void twotwoClick() {
    }

    public void twothreeClick() {
    }

    public void dozerClick() {
    }

    public void bansheeClick() {
    }

    public void sprogClick() {
    }

    public void distructorClick() {
    }

    public void violetClick() {
    }

}
