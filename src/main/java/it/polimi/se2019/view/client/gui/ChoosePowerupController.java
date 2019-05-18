package it.polimi.se2019.view.client.gui;

import it.polimi.se2019.model.CardRep;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

public class ChoosePowerupController {
    @FXML
    Button powerup1;
    @FXML
    Button powerup2;
    @FXML
    ImageView im1;
    @FXML
    ImageView im2;
    @FXML
    Button info1button;
    @FXML
    Button info2button;

    private GUI gui;
    protected CardRep im1rep, im2rep;

    protected void passGUI(GUI gui) {
        this.gui = gui;
    }

    public void initialize() {
        Platform.runLater(
                () -> {
                    im1.setImage(new Image(im1rep.getPath()));
                    im2.setImage(new Image(im2rep.getPath()));
                });
    }

    public void chooseFirstPowerup() {
        Stage stage = (Stage) info1button.getScene().getWindow();
        gui.sendPowerupChosen(im1rep.getId(), im2rep.getId());
        stage.close();

    }

    public void chooseSecondPowerup() {
        Stage stage = (Stage) info1button.getScene().getWindow();
        gui.sendPowerupChosen(im2rep.getId(), im1rep.getId());
        stage.close();
    }
    public void info1click(){
        showInstruction(im1rep);
    }
    public void info2click(){
        showInstruction(im2rep);
    }
    private void showInstruction(CardRep cardRep){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Power Up");
        alert.setHeaderText(cardRep.getTitle().toUpperCase());
        alert.setContentText(cardRep.getDescription());
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.showAndWait();
    }
}
