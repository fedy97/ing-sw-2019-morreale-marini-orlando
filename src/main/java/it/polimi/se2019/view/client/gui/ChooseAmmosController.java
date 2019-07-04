package it.polimi.se2019.view.client.gui;

import it.polimi.se2019.model.rep.BoardRep;
import it.polimi.se2019.model.rep.LightGameVersion;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

public class ChooseAmmosController {
    @FXML
    Button bluebutton;
    @FXML
    Button redbutton;
    @FXML
    Button yellowbutton;
    @FXML
    ImageView blueimage;
    @FXML
    ImageView yellowimage;
    @FXML
    ImageView redimage;

    private GUI gui;

    protected void passGUI(GUI gui) {
        this.gui = gui;
    }

    void updateRightAmmos(LightGameVersion lightGameVersion) {
        BoardRep mine = lightGameVersion.getPlayerBoardRep().get(gui.getCharInString());
        if (mine.getColorQtyAmmos().get("BLUE") > 0) {
            LoginController.enlightenButton(bluebutton);
            blueimage.setVisible(true);
        }
        if (mine.getColorQtyAmmos().get("RED") > 0) {
            LoginController.enlightenButton(redbutton);
            redimage.setVisible(true);
        }
        if (mine.getColorQtyAmmos().get("YELLOW") > 0) {
            LoginController.enlightenButton(yellowbutton);
            yellowimage.setVisible(true);
        }

    }

    public void blueclick() {
        gui.sendAmmo("BLUE");
    }

    public void redclick() {
        gui.sendAmmo("RED");
    }

    public void yellowclick() {
        gui.sendAmmo("YELLOW");
    }

}
