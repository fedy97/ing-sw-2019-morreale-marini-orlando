package it.polimi.se2019.view.client.gui;

import it.polimi.se2019.model.rep.LightGameVersion;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.List;

public class ScoreBoardController {

    @FXML
    Label player1;
    @FXML
    Label player2;
    @FXML
    Label player3;
    @FXML
    Label player4;
    @FXML
    Label player5;


    private LightGameVersion lightGameVersion;
    private GUI gui;
    private List<Label> labels;

    /**
     * here I need the reference to GUI in order to notify it for the click of a button
     *
     * @param gui
     */
    protected void passGUI(GUI gui) {
        this.gui = gui;
    }

    public void initialize() {
        labels = new ArrayList<>();
        labels.add(player1);
        labels.add(player2);
        labels.add(player3);
        labels.add(player4);
        labels.add(player5);
        Platform.runLater(() -> {
            for (Label label : labels)
                label.setFont(gui.normale);

        });

    }


}
