package it.polimi.se2019.view.client.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChooseTargetsController {

    @FXML
    private ToggleButton violetbutton;
    @FXML
    private ToggleButton bansheebutton;
    @FXML
    private ToggleButton distructorbutton;
    @FXML
    private ToggleButton sprogbutton;
    @FXML
    private ToggleButton dozerbutton;
    @FXML
    private Button firebutton;

    private GUI gui;
    private Map<String, ToggleButton> buttons;
    private List<String> targets;

    /**
     * here I need the reference to GUI in order to notify it for the click of a button
     *
     * @param gui of the user
     */
    protected void passGUI(GUI gui) {
        this.gui = gui;
    }

    public void initialize() {
        targets = new ArrayList<>();
        buttons = new HashMap<>();
        buttons.put("DOZER", dozerbutton);
        buttons.put("DISTRUCTOR", distructorbutton);
        buttons.put("SPROG", sprogbutton);
        buttons.put("BANSHEE", bansheebutton);
        buttons.put("VIOLET", violetbutton);

    }

    protected void enlightenRightTargets(List<String> targetsToEnlighten) {
        for (String target : targetsToEnlighten)
            LoginController.enlightenToggleButton(buttons.get(target));
        for (Map.Entry<String, ToggleButton> entry : buttons.entrySet()) {
            if (!targetsToEnlighten.contains(entry.getKey()))
                LoginController.darkenToggleButton(entry.getValue());
            targets.remove(entry.getKey());
        }
        checkFire();
    }

    public void fireClick() {
        gui.sendTargets(targets);
    }

    public void chooseBanshee() {
        if (bansheebutton.isSelected()) {
            targets.add("BANSHEE");
            LoginController.forceLightToggleButton(bansheebutton);
        } else {
            targets.remove("BANSHEE");
            LoginController.enlightenToggleButton(bansheebutton);
        }
        checkFire();
    }

    public void chooseSprog() {
        if (sprogbutton.isSelected()) {
            targets.add("SPROG");
            LoginController.forceLightToggleButton(sprogbutton);
        } else {
            targets.remove("SPROG");
            LoginController.enlightenToggleButton(sprogbutton);
        }
        checkFire();
    }

    public void chooseDozer() {
        if (dozerbutton.isSelected()) {
            targets.add("DOZER");
            LoginController.forceLightToggleButton(dozerbutton);
        } else {
            targets.remove("DOZER");
            LoginController.enlightenToggleButton(dozerbutton);
        }
        checkFire();
    }

    public void chooseDistructor() {
        if (distructorbutton.isSelected()) {
            targets.add("DISTRUCTOR");
            LoginController.forceLightToggleButton(distructorbutton);
        } else {
            targets.remove("DISTRUCTOR");
            LoginController.enlightenToggleButton(distructorbutton);
        }
        checkFire();
    }

    public void chooseViolet() {
        if (violetbutton.isSelected()) {
            targets.add("VIOLET");
            LoginController.forceLightToggleButton(violetbutton);
        } else {
            targets.remove("VIOLET");
            LoginController.enlightenToggleButton(violetbutton);
        }
        checkFire();
    }

    private void checkFire() {
        if (!targets.isEmpty())
            LoginController.enlightenButton(firebutton);
        else
            LoginController.darkenButton(firebutton);
    }

}
