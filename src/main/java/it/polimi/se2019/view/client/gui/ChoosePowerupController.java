package it.polimi.se2019.view.client.gui;

import it.polimi.se2019.model.rep.CardRep;
import it.polimi.se2019.utils.HandyFunctions;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

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
    ImageView im3;
    @FXML
    Button powerup3;
    @FXML
    Button info1button;
    @FXML
    Button info2button;
    @FXML
    Button info3button;

    private GUI gui;
    private List<CardRep> cardReps;
    private List<ImageView> imageViews;
    private List<Button> buttons;
    private List<Button> infoButtons;

    protected void passGUI(GUI gui) {
        this.gui = gui;
    }

    public void initialize() {
        Platform.runLater(
                () -> {
                    infoButtons = new ArrayList<>();
                    imageViews = new ArrayList<>();
                    buttons = new ArrayList<>();
                    infoButtons.add(info1button);
                    infoButtons.add(info2button);
                    infoButtons.add(info3button);
                    imageViews.add(im1);
                    imageViews.add(im2);
                    imageViews.add(im3);
                    buttons.add(powerup1);
                    buttons.add(powerup2);
                    buttons.add(powerup3);
                    HandyFunctions.enlightenButton(info1button);
                    HandyFunctions.enlightenButton(info2button);
                    HandyFunctions.enlightenButton(powerup1);
                    HandyFunctions.enlightenButton(powerup2);
                });
    }

    protected void updateRightPowerups(List<CardRep> cards) {
        this.cardReps = cards;
        for (CardRep cardRep : cards) {
            imageViews.get(cards.indexOf(cardRep)).setImage(new Image(cardRep.getPath()));
            HandyFunctions.enlightenButton(buttons.get(cards.indexOf(cardRep)));
            HandyFunctions.enlightenButton(infoButtons.get(cards.indexOf(cardRep)));
        }
        for (int i = cardReps.size(); i < 3; i++) {
            imageViews.get(i).setImage(new Image("/assets/powerups/AD_powerups_IT_02.jpg"));
            HandyFunctions.darkenButton(buttons.get(i));
            HandyFunctions.darkenButton(infoButtons.get(i));
        }

    }


    public void chooseFirstPowerup() {
        Stage stage = (Stage) info1button.getScene().getWindow();
        if (cardReps.size() == 2) gui.sendInitPowerupChosen(cardReps.get(0).getId(), cardReps.get(1).getId());
        else gui.sendPowerupToDiscardThenSpawn(cardReps.get(0).getId());
        stage.close();

    }

    public void chooseSecondPowerup() {
        Stage stage = (Stage) info1button.getScene().getWindow();
        if (cardReps.size() == 2) gui.sendInitPowerupChosen(cardReps.get(1).getId(), cardReps.get(0).getId());
        else gui.sendPowerupToDiscardThenSpawn(cardReps.get(1).getId());
        stage.close();
    }

    public void chooseThirdPowerup() {
        Stage stage = (Stage) info1button.getScene().getWindow();
        gui.sendPowerupToDiscardThenSpawn(cardReps.get(2).getId());
        stage.close();
    }


    public void info1click() {
        showInstruction(cardReps.get(0));
    }

    public void info2click() {
        showInstruction(cardReps.get(1));
    }

    public void info3click() {
        showInstruction(cardReps.get(2));
    }

    private void showInstruction(CardRep cardRep) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Power Up");
        alert.setHeaderText(cardRep.getTitle().toUpperCase());
        alert.setContentText(cardRep.getDescription());
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.showAndWait();
    }
}
