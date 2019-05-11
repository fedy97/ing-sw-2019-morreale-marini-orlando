package it.polimi.se2019.view.client.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;

import java.util.Observable;
import java.util.Observer;

public class ChooseCharacterController {

    @FXML
    private ToggleButton violetbutton;
    @FXML
    private ToggleButton bluebutton;
    @FXML
    private ToggleButton yellowbutton;
    @FXML
    private ToggleButton greenbutton;
    @FXML
    private ToggleButton greybutton;
    @FXML
    private Label timer;

    private ToggleGroup connectionType = new ToggleGroup();

    private GUI gui;

    /**
     * here I need the reference to GUI in order to notify it for the click of a button
     *
     * @param gui
     */
    protected void passGUI(GUI gui) {
        this.gui = gui;
    }

    public void initialize() {
        connectionType.getToggles().addAll(violetbutton, bluebutton, yellowbutton, greenbutton, greybutton);
    }

    public void chooseBlue(){}
    public void chooseGreen(){}
    public void chooseGrey(){}
    public void chooseYellow(){}
    public void chooseViolet(){}

    public void updateTimer(int count) {
        timer.setText(Integer.toString(count));
    }
}
