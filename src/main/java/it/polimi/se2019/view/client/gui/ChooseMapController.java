package it.polimi.se2019.view.client.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;

import java.util.Observable;
import java.util.Observer;

public class ChooseMapController {
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

    private ToggleGroup connectionType = new ToggleGroup();

    public void initialize() {
        connectionType.getToggles().addAll(map1button,map2button,map3button,map4button);
    }

    public void voteForMap1(){}
    public void voteForMap2(){}
    public void voteForMap3(){}
    public void voteForMap4(){}

}
