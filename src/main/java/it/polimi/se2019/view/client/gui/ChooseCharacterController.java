package it.polimi.se2019.view.client.gui;

import javafx.fxml.FXML;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;

import java.util.Observable;
import java.util.Observer;

public class ChooseCharacterController implements Observer {

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

    private ToggleGroup connectionType = new ToggleGroup();

    @Override
    public void update(Observable o, Object arg) {

    }

    public void initialize() {
        connectionType.getToggles().addAll(violetbutton, bluebutton, yellowbutton, greenbutton, greybutton);
    }

    public void chooseBlue(){}
    public void chooseGreen(){}
    public void chooseGrey(){}
    public void chooseYellow(){}
    public void chooseViolet(){}

}
