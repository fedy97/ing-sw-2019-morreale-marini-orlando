package it.polimi.se2019.view.client.gui;

import it.polimi.se2019.utils.HandyFunctions;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;

import java.util.*;

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
    private Map<String, ToggleButton> buttons;
    private DropShadow shadow = new DropShadow();
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
        buttons=new HashMap<>();
        buttons.put("DOZER",greybutton);
        buttons.put("DISTRUCTOR", yellowbutton);
        buttons.put("SPROG",greenbutton);
        buttons.put("BANSHEE", bluebutton);
        buttons.put("VIOLET", violetbutton);
    }

    public void chooseBlue(){
        gui.sendCharacterChosenByPlayer("BANSHEE");
        setEffectToButton(bluebutton);
        disableButtons();
    }
    public void chooseGreen(){
        gui.sendCharacterChosenByPlayer("SPROG");
        setEffectToButton(greenbutton);
        disableButtons();
    }
    public void chooseGrey(){
        gui.sendCharacterChosenByPlayer("DOZER");
        setEffectToButton(greybutton);
        disableButtons();
    }
    public void chooseYellow(){
        gui.sendCharacterChosenByPlayer("DISTRUCTOR");
        setEffectToButton(yellowbutton);
        disableButtons();
    }
    public void chooseViolet(){
        gui.sendCharacterChosenByPlayer("VIOLET");
        setEffectToButton(violetbutton);
        disableButtons();
    }

    private void disableButtons() {
        violetbutton.setDisable(true);
        bluebutton.setDisable(true);
        yellowbutton.setDisable(true);
        greenbutton.setDisable(true);
        greybutton.setDisable(true);
    }
    public void updateTimer(int count) {
        timer.setText(Integer.toString(count));
    }

    public void updateCharacters(String c) {
        buttons.get(c).setDisable(true);
    }

    private void setEffectToButton(ToggleButton toggleButton){
        shadow.setColor(new Color(0, 0,0.7,1));
        toggleButton.setEffect(shadow);
    }
}
