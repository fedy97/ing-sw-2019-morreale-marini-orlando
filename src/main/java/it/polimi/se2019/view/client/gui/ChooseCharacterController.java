package it.polimi.se2019.view.client.gui;

import it.polimi.se2019.utils.HandyFunctions;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.HashMap;
import java.util.Map;

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
    @FXML
    private Label labelcharacter;

    private ToggleGroup connectionType = new ToggleGroup();

    private GUI gui;
    private Map<String, ToggleButton> buttons;
    private DropShadow shadow = new DropShadow();
    private Font normale;

    /**
     * here I need the reference to GUI in order to notify it for the click of a button
     *
     * @param gui
     */
    protected void passGUI(GUI gui) {
        this.gui = gui;
    }

    public void initialize() {
        Platform.runLater(() -> {
            normale = javafx.scene.text.Font.loadFont(
                    getClass().getResource("/font.ttf").toExternalForm(),
                    20
            );
            connectionType.getToggles().addAll(violetbutton, bluebutton, yellowbutton, greenbutton, greybutton);
            buttons = new HashMap<>();
            buttons.put("DOZER", greybutton);
            buttons.put("DISTRUCTOR", yellowbutton);
            buttons.put("SPROG", greenbutton);
            buttons.put("BANSHEE", bluebutton);
            buttons.put("VIOLET", violetbutton);
            HandyFunctions.enlightenToggleButton(greybutton);
            HandyFunctions.enlightenToggleButton(greenbutton);
            HandyFunctions.enlightenToggleButton(yellowbutton);
            HandyFunctions.enlightenToggleButton(violetbutton);
            HandyFunctions.enlightenToggleButton(bluebutton);
            labelcharacter.setFont(normale);
            timer.setFont(normale);
        });
    }

    public void chooseBlue() {
        gui.sendCharacterChosenByPlayer("BANSHEE");
        setEffectToButton(bluebutton);
        disableButtons();
    }

    public void chooseGreen() {
        gui.sendCharacterChosenByPlayer("SPROG");
        setEffectToButton(greenbutton);
        disableButtons();
    }

    public void chooseGrey() {
        gui.sendCharacterChosenByPlayer("DOZER");
        setEffectToButton(greybutton);
        disableButtons();
    }

    public void chooseYellow() {
        gui.sendCharacterChosenByPlayer("DISTRUCTOR");
        setEffectToButton(yellowbutton);
        disableButtons();
    }

    public void chooseViolet() {
        gui.sendCharacterChosenByPlayer("VIOLET");
        setEffectToButton(violetbutton);
        disableButtons();
    }

    private void disableButtons() {
        HandyFunctions.darkenToggleButton(violetbutton);
        HandyFunctions.darkenToggleButton(greenbutton);
        HandyFunctions.darkenToggleButton(greybutton);
        HandyFunctions.darkenToggleButton(yellowbutton);
        HandyFunctions.darkenToggleButton(bluebutton);
    }

    public void updateTimer(int count) {
        timer.setText("Game will start in: " + count);
    }

    public void updateCharacters(String c) {
        HandyFunctions.darkenToggleButton(buttons.get(c));
    }

    private void setEffectToButton(ToggleButton toggleButton) {
        shadow.setColor(new Color(1, 1, 0, 1));
        toggleButton.setEffect(shadow);
    }
}
