package it.polimi.se2019.view.client.gui;

import it.polimi.se2019.model.CardRep;
import it.polimi.se2019.model.LightGameVersion;
import it.polimi.se2019.utils.HandyFunctions;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.List;

public class UseWeaponController {

    @FXML
    private Button weapon1;
    @FXML
    private Button weapon2;
    @FXML
    private Button weapon3;
    @FXML
    private ImageView weapon1image;
    @FXML
    private ImageView weapon2image;
    @FXML
    private ImageView weapon3image;

    private LightGameVersion lightGameVersion;
    private GUI gui;
    private List<ImageView> weaponsImages;
    private List<Button> weaponsButtons;

    public void initialize() {
        weaponsImages = new ArrayList<>();
        weaponsButtons = new ArrayList<>();
        weaponsImages.add(weapon1image);
        weaponsImages.add(weapon2image);
        weaponsImages.add(weapon3image);
        weaponsButtons.add(weapon1);
        weaponsButtons.add(weapon2);
        weaponsButtons.add(weapon3);
    }

    protected void passGUI(GUI gui) {
        this.gui = gui;
    }

    protected void updateMyWeapons(LightGameVersion lightGameVersion) {
        this.lightGameVersion = lightGameVersion;
        List<CardRep> myWeaponsReps = lightGameVersion.getPlayerWeapons().get(gui.getCharInString());
        for (CardRep myWeapon : myWeaponsReps) {
            weaponsImages.get(myWeaponsReps.indexOf(myWeapon)).setImage(new Image(myWeapon.getPath()));
            HandyFunctions.enlightenButton(weaponsButtons.get(myWeaponsReps.indexOf(myWeapon)));
        }
        for (int j = myWeaponsReps.size(); j < 3; j++) {
            weaponsImages.get(j).setImage(new Image("/assets/weapons/back.png"));
            HandyFunctions.darkenButton(weaponsButtons.get(j));
        }
    }

    public void weapon1Click() {
        int hash = lightGameVersion.getPlayerWeapons().get(gui.getCharInString()).get(0).getId();
        //TODO
    }

    public void weapon2Click() {
        int hash = lightGameVersion.getPlayerWeapons().get(gui.getCharInString()).get(1).getId();
        //TODO
    }

    public void weapon3Click() {
        int hash = lightGameVersion.getPlayerWeapons().get(gui.getCharInString()).get(2).getId();
        //TODO
    }

}
