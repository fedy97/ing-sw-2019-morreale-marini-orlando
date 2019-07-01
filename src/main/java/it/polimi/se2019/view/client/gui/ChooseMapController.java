package it.polimi.se2019.view.client.gui;

import it.polimi.se2019.utils.HandyFunctions;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.Map;

public class ChooseMapController {

    private GUI gui;
    @FXML
    private Label map1vote;
    @FXML
    private Label map2vote;
    @FXML
    private Label map3vote;
    @FXML
    private Label map4vote;
    @FXML
    private ToggleButton map1button;
    @FXML
    private ToggleButton map2button;
    @FXML
    private ToggleButton map3button;
    @FXML
    private ToggleButton map4button;
    @FXML
    private Label timer;
    @FXML
    private Label labelmap;
    @FXML
    private ImageView bgImg;

    private ArrayList<Label> voteLabels;

    private ToggleGroup connectionType = new ToggleGroup();
    private DropShadow shadow = new DropShadow();
    private Font normale;
    private Font grande;

    public void initialize() {
        Platform.runLater(() -> {
            normale = javafx.scene.text.Font.loadFont(
                    getClass().getResource("/font.ttf").toExternalForm(),
                    10
            );
            grande = javafx.scene.text.Font.loadFont(
                    getClass().getResource("/font.ttf").toExternalForm(),
                    20
            );
            voteLabels = new ArrayList<>();
            voteLabels.add(map1vote);
            voteLabels.add(map2vote);
            voteLabels.add(map3vote);
            voteLabels.add(map4vote);
            bgImg.setImage(new Image("/assets/backgrounds/bgchoose.jpg"));
            connectionType.getToggles().addAll(map1button, map2button, map3button, map4button);
            HandyFunctions.enlightenToggleButton(map1button);
            HandyFunctions.enlightenToggleButton(map2button);
            HandyFunctions.enlightenToggleButton(map3button);
            HandyFunctions.enlightenToggleButton(map4button);
            for (Label label : voteLabels)
                label.setFont(normale);
            timer.setFont(normale);
            labelmap.setFont(grande);

        });

    }

    /**
     * here I need the reference to GUI in order to notify it for the click of a button
     *
     * @param gui of the user
     */
    protected void passGUI(GUI gui) {
        this.gui = gui;
    }

    public void voteForMap1() {
        gui.sendMapChosenByPlayer(1);
        setEffectToButton(map1button);
        disableButtons();
    }

    public void voteForMap2() {
        gui.sendMapChosenByPlayer(2);
        setEffectToButton(map2button);
        disableButtons();
    }

    public void voteForMap3() {
        gui.sendMapChosenByPlayer(3);
        setEffectToButton(map3button);
        disableButtons();
    }

    public void voteForMap4() {
        gui.sendMapChosenByPlayer(4);
        setEffectToButton(map4button);
        disableButtons();
    }

    private void disableButtons() {
        HandyFunctions.darkenToggleButton(map1button);
        HandyFunctions.darkenToggleButton(map2button);
        HandyFunctions.darkenToggleButton(map3button);
        HandyFunctions.darkenToggleButton(map4button);
    }

    void updateVotes(Map<Integer, Integer> map) {
        int n = 1;
        for (Label l : voteLabels) {
            l.setText("Votes: " + map.get(n).toString());
            n++;
        }
    }

    void updateTimer(int count) {
        timer.setText("Timer: " + count);
    }

    private void setEffectToButton(ToggleButton toggleButton) {
        shadow.setColor(new Color(1, 1, 0, 1));
        toggleButton.setEffect(shadow);
    }
}
