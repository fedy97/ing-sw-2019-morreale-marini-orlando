package it.polimi.se2019.view.client.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;

import java.util.ArrayList;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

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
    private ArrayList<Label> voteLabels;

    private ToggleGroup connectionType = new ToggleGroup();

    public void initialize() {
        voteLabels = new ArrayList<>();
        voteLabels.add(map1vote);
        voteLabels.add(map2vote);
        voteLabels.add(map3vote);
        voteLabels.add(map4vote);
        connectionType.getToggles().addAll(map1button, map2button, map3button, map4button);
    }

    /**
     * here I need the reference to GUI in order to notify it for the click of a button
     *
     * @param gui
     */
    protected void passGUI(GUI gui) {
        this.gui = gui;
    }

    public void voteForMap1() {
        gui.sendMapChosenByPlayer(1);
        disableButtons();
    }

    public void voteForMap2() {
        gui.sendMapChosenByPlayer(2);
        disableButtons();
    }

    public void voteForMap3() {
        gui.sendMapChosenByPlayer(3);
        disableButtons();
    }

    public void voteForMap4() {
        gui.sendMapChosenByPlayer(4);
        disableButtons();
    }

    private void disableButtons() {
        map1button.setDisable(true);
        map2button.setDisable(true);
        map3button.setDisable(true);
        map4button.setDisable(true);
    }

    public void updateVotes(Map<Integer, Integer> map) {
        int n = 1;
        for (Label l : voteLabels) {
            l.setText(map.get(n).toString());
            n++;
        }
    }

    public void updateTimer(int count) {
        timer.setText(Integer.toString(count));
    }
}
